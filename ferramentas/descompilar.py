"""Recupera fontes Java dos JARs da pasta principal sem executar os mods."""

import argparse
from concurrent.futures import ThreadPoolExecutor, as_completed
import hashlib
import json
import os
from pathlib import Path
import shutil
import subprocess
import time
import zipfile


ROOT = Path(__file__).resolve().parent.parent
CFR = ROOT / "ferramentas" / "cfr-0.152.jar"


def sha256(path):
    with path.open("rb") as stream:
        return hashlib.file_digest(stream, "sha256").hexdigest()


def decompile(jar, output_root, java, classpath):
    output = output_root / jar.stem
    sources = output / "java"
    sources.mkdir(parents=True, exist_ok=True)
    original_hash = sha256(jar)
    with zipfile.ZipFile(jar) as archive:
        entries = archive.namelist()
        class_count = sum(name.endswith(".class") for name in entries)
        embedded_sources = [name for name in entries if name.endswith(".java")]
        for name in embedded_sources:
            target = (output / "fontes_embutidas" / name).resolve()
            if not target.is_relative_to(output.resolve()):
                raise ValueError(f"Caminho inesperado no JAR: {name}")
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_bytes(archive.read(name))

    command = [
        java, "-Xmx1536m", "-jar", str(CFR), str(jar),
        "--outputdir", str(sources),
        "--extraclasspath", classpath,
        "--outputencoding", "UTF-8",
        "--caseinsensitivefs", "true",
        "--silent", "true",
    ]
    started = time.monotonic()
    print(f"Iniciando: {jar.name} ({class_count} classes)", flush=True)
    with (output / "descompilacao.log").open("w", encoding="utf-8") as log:
        try:
            process = subprocess.run(
                command, cwd=ROOT, stdout=log, stderr=subprocess.STDOUT,
                timeout=900, check=False,
            )
            exit_code = process.returncode
        except subprocess.TimeoutExpired:
            log.write("\nTempo limite de 900 segundos excedido.\n")
            exit_code = -1

    java_files = list(sources.rglob("*.java"))
    failed_methods = []
    for source in java_files:
        content = source.read_text(encoding="utf-8", errors="replace")
        if "This method has failed to decompile" in content:
            failed_methods.append(str(source.relative_to(output_root)).replace("\\", "/"))
    result = {
        "mod": jar.name,
        "classes_no_jar": class_count,
        "arquivos_java_recuperados": len(java_files),
        "fontes_java_embutidas": len(embedded_sources),
        "codigo_saida_cfr": exit_code,
        "segundos": round(time.monotonic() - started, 1),
        "sha256_original": original_hash,
        "jar_preservado": sha256(jar) == original_hash,
        "arquivos_com_metodos_nao_recuperados": failed_methods,
    }
    (output / "relatorio.json").write_text(
        json.dumps(result, indent=2, ensure_ascii=False) + "\n", encoding="utf-8"
    )
    print(
        f"Concluido: {jar.name}: {len(java_files)} arquivos Java, "
        f"{len(failed_methods)} arquivos com metodos nao recuperados, saida {exit_code}",
        flush=True,
    )
    return result


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--mod", help="Nome exato de um JAR; por padrao processa todos.")
    parser.add_argument("--saida", default="codigo_mods", help="Nova pasta de destino.")
    args = parser.parse_args()
    output_root = (ROOT / args.saida).resolve()
    if not output_root.is_relative_to(ROOT):
        parser.error("A pasta de destino deve ficar dentro da pasta de trabalho.")
    jars = sorted(ROOT.glob("*.jar"))
    selected = [jar for jar in jars if not args.mod or jar.name == args.mod]
    if not selected:
        parser.error("Nenhum JAR encontrado para essa selecao.")
    java = shutil.which("java")
    if not java or not CFR.is_file():
        parser.error("Java e ferramentas/cfr-0.152.jar precisam estar disponiveis.")
    for jar in selected:
        destination = output_root / jar.stem
        if destination.exists() and any(destination.iterdir()):
            parser.error(f"Destino ja possui arquivos: {destination}. Use --saida com uma nova pasta.")
    output_root.mkdir(parents=True, exist_ok=True)
    classpath = os.pathsep.join(str(jar) for jar in jars)
    results = []
    with ThreadPoolExecutor(max_workers=2) as pool:
        jobs = [pool.submit(decompile, jar, output_root, java, classpath) for jar in selected]
        for job in as_completed(jobs):
            results.append(job.result())
    results.sort(key=lambda result: result["mod"].lower())
    (output_root / "inventario.json").write_text(
        json.dumps(results, indent=2, ensure_ascii=False) + "\n", encoding="utf-8"
    )
    print(f"Total: {sum(r['arquivos_java_recuperados'] for r in results)} arquivos Java.")
    if any(r["codigo_saida_cfr"] != 0 or not r["jar_preservado"] for r in results):
        raise SystemExit(1)


if __name__ == "__main__":
    main()
