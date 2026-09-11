# Código dos mods

Cada pasta contém o código Java recuperado do JAR com o mesmo nome. Abra a subpasta `java` para navegar pelos pacotes e arquivos `.java`.

## Por onde começar

| Mod | Arquivo ou pasta útil |
| --- | --- |
| Dragon Block C | [DBCConfig.java](DragonBlockC-v1.4.85/java/JinRyuu/DragonBC/common/DBCConfig.java) — opções de configuração |
| JRMCore | [JRMCoreConfig.java](JRMCore-v1.3.51/java/JinRyuu/JRMCore/JRMCoreConfig.java) — configurações do núcleo |
| JRMCore: raças, formas e habilidades | [Configurações de DBC](JRMCore-v1.3.51/java/JinRyuu/JRMCore/server/config/dbc/) |
| DBC Additions | [DBCAConfig.java](DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/DBCAConfig.java) — configurações do complemento |
| CustomNPC+ | [CustomNpcs.java](CustomNPC-Plus-1.11.1/java/noppes/npcs/CustomNpcs.java) — classe principal; [config](CustomNPC-Plus-1.11.1/java/noppes/npcs/config/) — configurações |
| Outros mods | Entre na pasta com o nome do mod e abra `java` |

No VS Code, use `Ctrl + P` para procurar um arquivo pelo nome e `Ctrl + Shift + F` para pesquisar no código. Por exemplo, procure `DBCConfig`, `FormMastery` ou o nome de uma habilidade. Podemos ler e editar esses arquivos nesta conversa.

## O que foi recuperado

- Os arquivos `.java` foram reconstruídos a partir dos arquivos compilados `.class` usando [CFR 0.152](https://www.benf.org/other/cfr/), com recuperação complementar pelo [Vineflower 1.12.0](https://github.com/Vineflower/vineflower/releases/tag/1.12.0) no JRMCore e no Armourer's Workshop.
- Quando um JAR já continha fontes `.java`, elas foram copiadas para `fontes_embutidas`, separadas do resultado da descompilação.
- Classes internas podem aparecer dentro do arquivo da classe principal. Por isso, a quantidade de arquivos `.java` pode ser menor que a quantidade de arquivos `.class`.
- Os recursos, como texturas e sons, continuam nos JARs originais; esta pasta concentra o código Java.
- Cada mod possui `relatorio.json`, `descompilacao.log` e, quando gerado pelo CFR, `java/summary.txt`. O arquivo `inventario.json` reúne os resultados e os hashes SHA-256 dos JARs.
- A pasta `java` concentra os arquivos para consulta. Nos dois mods com recuperação complementar, `java_vineflower` preserva a saída alternativa. No Armourer's Workshop, `java_cfr_parcial` guarda a versão anterior do arquivo recuperado.

## Limitação identificada

O método `func_73863_a(int, int, float)` de [JRMCoreGuiScreen.java](JRMCore-v1.3.51/java/JinRyuu/JRMCore/JRMCoreGuiScreen.java), responsável por desenhar a interface, não foi reconstruído em Java porque excedeu o limite de memória do descompilador. O arquivo marca esse trecho com `Couldn't be decompiled`. O [bytecode da classe](JRMCore-v1.3.51/JRMCoreGuiScreen.bytecode.txt) foi preservado em texto para permitir investigação posterior.

O arquivo [verificacao.json](verificacao.json) registra a cobertura das classes principais, os arquivos com métodos não recuperados e a conferência de integridade dos JARs. Essa conferência não substitui a compilação e o teste no jogo.

## Editar e colocar a alteração no jogo

Você pode consultar e editar o código recuperado. Salvar um `.java` nesta pasta não altera o mod carregado pelo Minecraft: para aplicar mudanças no código, será necessário preparar a compilação compatível com Forge 1.7.10, reunir as dependências, corrigir os trechos que a descompilação não reconstruiu corretamente e gerar um novo `.jar` para testar.

Este resultado não é o projeto original do autor e não inclui automaticamente arquivos de build, comentários originais ou todos os nomes de variáveis. Referências como `func_...` e `field_...` podem continuar com os nomes usados no mod distribuído. O relatório identifica arquivos com a marca explícita de falha em um método; a ausência dessa marca não garante que o arquivo compile sem ajustes.

Alguns arquivos começam com `Could not load the following classes`: o CFR não recebeu todas as bibliotecas de Minecraft e Forge usadas pelo mod. Esses avisos e os avisos de estruturação em `summary.txt` devem ser revisados ao preparar uma compilação.

Os JARs originais foram usados apenas como entrada. O processo executa o descompilador, sem iniciar Minecraft nem carregar os mods para jogar.

## Repetir a extração

Na pasta `mods_mine`, com Python e Java disponíveis:

```powershell
python .\ferramentas\descompilar.py --mod 'DragonBlockC-v1.4.85.jar' --saida 'nova_extracao'
```

Sem `--mod`, o script processa os 16 JARs da pasta principal. Use um novo destino para cada extração: o script recusa pastas de mod que já contenham arquivos para preservar eventuais edições.

Esse comando usa o CFR. Para o JRMCore foi necessário complementar o resultado com o Vineflower; os detalhes estão em `JRMCore-v1.3.51/relatorio.json` e `vineflower.log`.

O CFR foi obtido do site oficial; o MD5 foi conferido com o valor publicado pelo autor: `8a85ada8cec494121246805a5562b82b`.

O Vineflower foi obtido da versão oficial no GitHub, com SHA-256 conferido: `1dfcfe974395734fa467ce620661c7623d05ba83670de0529b1fbd63ff548b9d`.
