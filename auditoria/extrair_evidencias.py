"""Desmonta classes selecionadas para conferir os achados nos binários originais."""
import hashlib
import json
from pathlib import Path
import subprocess
import zipfile

ROOT = Path(__file__).resolve().parent.parent
OUT = ROOT / 'auditoria' / 'evidencias'
JAVAP = Path(r'C:\Program Files\Java\jdk-21\bin\javap.exe')
SELECTION = {
    'entityculling-1.6.4-mc1.7.10.jar': [
        'dev/tr7zw/entityculling/EntityCullingMod',
        'dev/tr7zw/entityculling/EntityCullingModBase'],
    'npcgecko-1.0.jar': [
        'com/goodbird/npcgecko/mixin/impl/MixinGeckoAddon',
        'com/goodbird/npcgecko/mixin/impl/MixinGeckoAddonClient',
        'com/goodbird/npcgecko/network/CPacketSyncManualAnim'],
    'CustomNPC-Plus-1.11.1.jar': [
        'kamkeel/npcs/addon/GeckoAddon', 'noppes/npcs/Server'],
    'DBCAdditions-1.74.jar': [
        'com/tobiasmjc/dbcadditions/mixin/late/packet/MixinDBCPacketHandler',
        'com/tobiasmjc/dbcadditions/mixin/late/MixinJRMCorePacHanS',
        'com/tobiasmjc/dbcadditions/mixin/late/MixinJRMCoreComTickH',
        'com/tobiasmjc/dbcadditions/data/DBCAPlayer',
        'com/tobiasmjc/dbcadditions/packets/DBUPacketLearnSkill$Handler',
        'com/tobiasmjc/dbcadditions/packets/DBUPacketUpgradeSkill$Handler',
        'com/tobiasmjc/dbcadditions/packets/DBUPacketSelectForm$Handler',
        'com/tobiasmjc/dbcadditions/DBCAConfig',
        'com/tobiasmjc/dbcadditions/utils/DataUtils',
        'com/tobiasmjc/dbcadditions/data/forms/FormItem'],
    'JRMCore-v1.3.51.jar': [
        'JinRyuu/JRMCore/JRMCoreComTickH',
        'JinRyuu/JRMCore/p/DBC/DBCPacketHandlerServer'],
}
OUT.mkdir(parents=True, exist_ok=True)
results = []
for jar_name, names in SELECTION.items():
    with zipfile.ZipFile(ROOT / jar_name) as archive:
        for name in names:
            raw = archive.read(name + '.class')
            destination = OUT / Path(jar_name).stem
            class_file = destination / 'classes' / (name + '.class')
            class_file.parent.mkdir(parents=True, exist_ok=True)
            class_file.write_bytes(raw)
            text_file = destination / (name.replace('/', '.') + '.bytecode.txt')
            with text_file.open('w', encoding='utf-8') as stream:
                subprocess.run([str(JAVAP), '-J-Xmx256m', '-p', '-c', '-v', str(class_file)],
                               stdout=stream, stderr=subprocess.STDOUT, check=True)
            results.append({'jar': jar_name, 'classe': name, 'sha256_classe': hashlib.sha256(raw).hexdigest(),
                            'bytecode': str(text_file.relative_to(OUT.parent)).replace('\\', '/')})
(OUT / 'indice.json').write_text(json.dumps(results, indent=2) + '\n', encoding='utf-8')
print(f'{len(results)} classes conferiveis em auditoria/evidencias.')
