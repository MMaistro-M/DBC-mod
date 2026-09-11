"""Inspeção estática dos JARs: versões, manifestos, mixins e referências binárias.

Não executa classes de mods nem altera JARs ou configurações.
Referências não resolvidas são candidatas à revisão, não diagnósticos de crash.
"""
from collections import Counter, defaultdict
import hashlib
import json
from pathlib import Path
import struct
import zipfile

ROOT = Path(__file__).resolve().parent.parent
OUT = Path(__file__).resolve().parent


class Reader:
    def __init__(self, data):
        self.data, self.pos = data, 0

    def take(self, n):
        result = self.data[self.pos:self.pos + n]
        self.pos += n
        return result

    def u1(self):
        return self.take(1)[0]

    def u2(self):
        return struct.unpack('>H', self.take(2))[0]

    def u4(self):
        return struct.unpack('>I', self.take(4))[0]


def parse_class(data):
    r = Reader(data)
    assert r.u4() == 0xCAFEBABE
    minor, major = r.u2(), r.u2()
    cp = [None] * r.u2()
    i = 1
    while i < len(cp):
        tag = r.u1()
        if tag == 1:
            cp[i] = (tag, r.take(r.u2()).decode('utf-8', errors='replace'))
        elif tag in (3, 4):
            cp[i] = (tag, r.take(4))
        elif tag in (5, 6):
            cp[i] = (tag, r.take(8))
            i += 1
        elif tag in (7, 8, 16, 19, 20):
            cp[i] = (tag, r.u2())
        elif tag in (9, 10, 11, 12, 17, 18):
            cp[i] = (tag, r.u2(), r.u2())
        elif tag == 15:
            cp[i] = (tag, r.u1(), r.u2())
        else:
            raise ValueError(f'Tag inesperada: {tag}')
        i += 1
    utf = lambda index: cp[index][1]
    cls = lambda index: utf(cp[index][1]) if index else None
    access, name, superclass = r.u2(), cls(r.u2()), cls(r.u2())
    interfaces = [cls(r.u2()) for _ in range(r.u2())]

    def attrs(reader):
        result = []
        for _ in range(reader.u2()):
            attr_name = utf(reader.u2())
            result.append((attr_name, reader.take(reader.u4())))
        return result

    fields, methods, locals_by_method = [], [], {}
    for is_method in (False, True):
        for _ in range(r.u2()):
            flags, member_name, desc = r.u2(), utf(r.u2()), utf(r.u2())
            (methods if is_method else fields).append((member_name, desc, flags))
            for attr_name, content in attrs(r):
                if attr_name != 'Code':
                    continue
                code = Reader(content)
                code.u2()
                code.u2()
                code.take(code.u4())
                code.take(code.u2() * 8)
                for code_attr, code_content in attrs(code):
                    if code_attr == 'LocalVariableTable':
                        local = Reader(code_content)
                        names = set()
                        for _ in range(local.u2()):
                            local.u2()
                            local.u2()
                            names.add(utf(local.u2()))
                            local.u2()
                            local.u2()
                        locals_by_method[member_name + desc] = sorted(names)
    refs = []
    for item in cp:
        if item and item[0] in (9, 10, 11):
            nat = cp[item[2]]
            refs.append((item[0], cls(item[1]), utf(nat[1]), utf(nat[2])))
    return dict(name=name, major=major, superclass=superclass, interfaces=interfaces,
                fields=fields, methods=methods, refs=refs, locals=locals_by_method)


def dump(name, value):
    (OUT / name).write_text(json.dumps(value, indent=2, ensure_ascii=False) + '\n', encoding='utf-8')


classes = defaultdict(list)
inventories = []
for jar in sorted(ROOT.glob('*.jar')):
    versions = Counter()
    with zipfile.ZipFile(jar) as archive:
        resources = {}
        for entry in archive.infolist():
            if entry.filename.endswith('.class') and not entry.filename.startswith('META-INF/versions/'):
                raw = archive.read(entry)
                parsed = parse_class(raw)
                parsed.update(jar=jar.name, sha256=hashlib.sha256(raw).hexdigest())
                classes[parsed['name']].append(parsed)
                versions[parsed['major']] += 1
            elif entry.filename == 'META-INF/MANIFEST.MF' or entry.filename == 'mcmod.info' or (
                entry.filename.endswith('.json') and 'mixin' in entry.filename.lower()
                and 'refmap' not in entry.filename.lower()
            ):
                resources[entry.filename] = archive.read(entry).decode('utf-8', errors='replace')
        inventories.append(dict(jar=jar.name, sha256=hashlib.sha256(jar.read_bytes()).hexdigest(),
                                class_major_versions=dict(versions), resources=resources))
dump('inventario_binario.json', inventories)

duplicates = [dict(classe=name, jars=[d['jar'] for d in defs],
                   versoes_binarias_distintas=len({d['sha256'] for d in defs}))
              for name, defs in classes.items() if len(defs) > 1]
dump('classes_duplicadas.json', duplicates)

def resolves(owner, name, desc, field, visited=None):
    visited = set() if visited is None else visited
    if owner in visited:
        return False
    visited.add(owner)
    if owner not in classes:
        return None  # Herança externa não avaliada.
    uncertain = False
    for definition in classes[owner]:
        if any(n == name and d == desc for n, d, f in definition['fields' if field else 'methods']):
            return True
        if name == '<init>':
            continue
        for parent in [definition['superclass']] + definition['interfaces']:
            if not parent:
                continue
            result = resolves(parent, name, desc, field, visited.copy())
            if result is True:
                return True
            uncertain |= result is None
    return None if uncertain else False

prefixes = ('JinRyuu/', 'noppes/', 'kamkeel/', 'com/tobiasmjc/', 'com/goodbird/',
            'hedaox/', 'software/bernie/', 'me/NBArmors/', 'riskyken/')
missing = []
for definitions in classes.values():
    for definition in definitions:
        if not definition['name'].startswith(prefixes):
            continue
        for tag, owner, name, desc in definition['refs']:
            if not owner.startswith(prefixes):
                continue
            result = resolves(owner, name, desc, tag == 9)
            if result is not True:
                missing.append(dict(origem_jar=definition['jar'], origem_classe=definition['name'],
                                    alvo=owner, membro=name, descritor=desc, campo=tag == 9,
                                    classe_alvo_presente=owner in classes,
                                    heranca_externa_pendente=result is None))
dump('referencias_para_revisao.json', missing)

local_tables = {name: definitions[0]['locals'] for name, definitions in classes.items()
                if name.startswith(('JinRyuu/', 'noppes/', 'com/tobiasmjc/')) and definitions[0]['locals']}
dump('variaveis_locais_bytecode.json', local_tables)
summary = dict(jars=len(inventories), classes=len(classes), classes_duplicadas=len(duplicates),
               duplicadas_com_bytes_diferentes=sum(d['versoes_binarias_distintas'] > 1 for d in duplicates),
               referencias_para_revisao=len(missing))
dump('resumo_inspecao.json', summary)
print(json.dumps(summary, indent=2))
