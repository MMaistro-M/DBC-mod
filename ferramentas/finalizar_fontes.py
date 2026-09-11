"""Integra a recuperação pelo Vineflower e confere as fontes contra os JARs."""

import hashlib
import json
from pathlib import Path
import re
import shutil
import subprocess
import zipfile


ROOT = Path(__file__).resolve().parent.parent
OUTPUT = ROOT / "codigo_mods"
MARKERS = ("This method has failed to decompile", "Couldn't be decompiled")


def has_failed_method(path):
    content = path.read_text(encoding="utf-8", errors="replace")
    return any(marker in content for marker in MARKERS)


for name in ("JRMCore-v1.3.51", "Armourers-Workshop-1.7.10-0.48.5"):
    mod_dir = OUTPUT / name
    alternatives = list((mod_dir / "java_vineflower").rglob("*.java"))
    if not alternatives:
        raise RuntimeError(f"Recuperacao ausente: {name}")
    integrated = []
    for alternative in alternatives:
        relative = alternative.relative_to(mod_dir / "java_vineflower")
        target = mod_dir / "java" / relative
        if target.exists():
            if not has_failed_method(target) or has_failed_method(alternative):
                continue
            backup = mod_dir / "java_cfr_parcial" / relative
            backup.parent.mkdir(parents=True, exist_ok=True)
            if not backup.exists():
                shutil.copy2(target, backup)
        target.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(alternative, target)
        integrated.append(relative.as_posix())
    report_path = mod_dir / "relatorio.json"
    report = json.loads(report_path.read_text(encoding="utf-8"))
    report.setdefault("arquivos_java_cfr", report["arquivos_java_recuperados"])
    previous = report.get("recuperacao_vineflower", {}).get("arquivos_integrados", [])
    report["recuperacao_vineflower"] = {
        "versao": "1.12.0",
        "arquivos_integrados": sorted(set(previous + integrated)),
        "fontes_alternativas": "java_vineflower",
    }
    report_path.write_text(json.dumps(report, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")


# Mantém uma representação do bytecode da classe cujo método de interface
# não foi reconstruído em Java, permitindo inspecioná-lo posteriormente.
bytecode_path = OUTPUT / "JRMCore-v1.3.51" / "JRMCoreGuiScreen.bytecode.txt"
javap = Path(r"C:\Program Files\Java\jdk-21\bin\javap.exe")
class_input = ROOT / "ferramentas" / "bytecode" / "JRMCoreGuiScreen.class"
class_input.parent.mkdir(parents=True, exist_ok=True)
with zipfile.ZipFile(ROOT / "JRMCore-v1.3.51.jar") as archive:
    class_input.write_bytes(archive.read("JinRyuu/JRMCore/JRMCoreGuiScreen.class"))
with bytecode_path.open("w", encoding="utf-8") as stream:
    subprocess.run(
        [str(javap), "-c", "-p", str(class_input)],
        stdout=stream, stderr=subprocess.STDOUT, check=True,
    )

reports = []
for jar in sorted(ROOT.glob("*.jar")):
    mod_dir = OUTPUT / jar.stem
    report_path = mod_dir / "relatorio.json"
    report = json.loads(report_path.read_text(encoding="utf-8"))
    with jar.open("rb") as stream:
        current_hash = hashlib.file_digest(stream, "sha256").hexdigest()
    assert current_hash == report["sha256_original"], f"JAR alterado: {jar.name}"
    with zipfile.ZipFile(jar) as archive:
        primary = [name for name in archive.namelist() if name.endswith(".class") and "$" not in name]
    missing = [name for name in primary if not (mod_dir / "java" / (name[:-6] + ".java")).is_file()]
    assert not missing, f"Classes principais sem fonte em {jar.name}: {missing}"
    sources = list((mod_dir / "java").rglob("*.java"))
    failures = [source.relative_to(OUTPUT).as_posix() for source in sources if has_failed_method(source)]
    report.update({
        "arquivos_java_recuperados": len(sources),
        "classes_principais": len(primary),
        "classes_principais_sem_fonte": missing,
        "jar_preservado": True,
        "arquivos_com_metodos_nao_recuperados": failures,
    })
    report_path.write_text(json.dumps(report, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    reports.append(report)

(OUTPUT / "inventario.json").write_text(json.dumps(reports, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
verification = {
    "mods": len(reports),
    "arquivos_java": sum(report["arquivos_java_recuperados"] for report in reports),
    "fontes_embutidas": sum(report["fontes_java_embutidas"] for report in reports),
    "todos_jars_preservados": all(report["jar_preservado"] for report in reports),
    "todas_classes_principais_com_arquivo_java": all(not report["classes_principais_sem_fonte"] for report in reports),
    "arquivos_com_metodos_nao_recuperados": [path for report in reports for path in report["arquivos_com_metodos_nao_recuperados"]],
    "links_locais_do_guia_validos": False,
    "compilacao_testada": False,
}
(OUTPUT / "verificacao.json").write_text(json.dumps(verification, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
guide = OUTPUT / "LEIA-ME.md"
for link in re.findall(r"\]\(([^)]+)\)", guide.read_text(encoding="utf-8")):
    if not link.startswith(("http://", "https://")):
        assert (guide.parent / link).exists(), f"Link local quebrado: {link}"
verification["links_locais_do_guia_validos"] = True
(OUTPUT / "verificacao.json").write_text(json.dumps(verification, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
print(json.dumps(verification, indent=2, ensure_ascii=False))
