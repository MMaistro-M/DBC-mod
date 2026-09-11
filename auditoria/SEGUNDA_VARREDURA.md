# Segunda varredura do pacote

## Escopo e limite

Esta revisão leu os 16 JARs fornecidos e os 8.047 arquivos Java decompilados. Não há nesta pasta uma instalação completa do servidor: faltam `config/`, mundo, `logs/`, `crash-reports/` e `server.properties`. Por isso ela identifica riscos no pacote, mas não pode confirmar a causa de um crash ocorrido em produção sem o trecho `Caused by` do log ou o arquivo de crash.

Os ajustes feitos na primeira revisão estão somente em `codigo_mods/`. O JAR `DBCAdditions-1.74.jar` original continua sem recompilação; portanto, nenhuma alteração de fonte passa a valer no servidor até que seja montado um ambiente Forge 1.7.10 compatível, compilado e substituído o JAR.

## Pastas da raiz

| Pasta | Conteúdo e finalidade |
| --- | --- |
| `codigo_mods/` | Código Java decompilado de cada JAR. Serve para auditoria e futuras correções; não é um projeto compilável pronto. |
| `auditoria/` | Relatórios, inventário dos JARs, lista de classes duplicadas e scripts da análise. |
| `ferramentas/` | Scripts usados para descompilar e finalizar a organização dos fontes. |
| `.git/` | Histórico local do Git. Não deve ser editado manualmente. |

Os JARs na raiz são os binários originais usados para inspecionar o pacote. Estão ignorados pelo Git para não duplicar arquivos grandes e binários no repositório.

## Pastas em `codigo_mods/`

| Pasta | Papel do mod |
| --- | --- |
| `+unimixins-all-1.7.10-0.1.23/` | Plataforma de Mixin, MixingASM, MixinExtras e compatibilidade para mods que alteram classes do jogo. |
| `Armourers-Workshop-1.7.10-0.48.5/` | Armaduras, skins e blocos cosméticos. |
| `Controlling-1.7.10-1.0.0.8/` | Busca e organização de atalhos de teclado no cliente. |
| `CustomNPC-Plus-1.11.1/` | NPCs, scripts, clones, diálogos, animações e dados de mundo. |
| `DBCAdditions-1.74/` | Formas, habilidades, dimensões, teleporte, desejos e integrações do Dragon Block C. |
| `DragonBlockC-v1.4.85/` | Conteúdo principal de Dragon Block C. |
| `entityculling-1.6.4-mc1.7.10/` | Otimização visual de entidades e blocos por oclusão; voltado ao cliente. |
| `geckolib-unofficial-1.7.10-1.0.3/` | Biblioteca de animações GeckoLib usada pelo NPC Gecko. |
| `InventoryTweaks-1.59-dev-152/` | Organização automática de inventário e atalhos; possui proxy seguro para servidor. |
| `JBRA-Client-v1.6.52/` | Renderização adicional do JinRyuu; o mod usa proxies por lado. |
| `JFamilyC-v1.2.18/` | Sistema de família do JinRyuu. |
| `JRMCore-v1.3.51/` | Núcleo de atributos, energia, TP, raças e sistemas compartilhados de JinRyuu. |
| `JYearsC-v1.2.5/` | Sistema de anos/idade ligado ao núcleo JinRyuu. |
| `NBArmors-3.1/` | Armaduras adicionais. |
| `ninjinentities-Unofficial-1.4.4/` | Entidades, NPCs, modelos e conteúdo adicional de Dragon Ball. |
| `npcgecko-1.0/` | Integração entre CustomNPC+ e GeckoLib para modelos animados. |

## Riscos encontrados

### 1. Entity Culling no servidor dedicado — prioridade alta

O JAR `entityculling-1.6.4-mc1.7.10.jar` referencia diretamente `net.minecraft.client.Minecraft`, `KeyBinding`, eventos de renderização e `ClientRegistry`. Embora o construtor tente detectar servidor, o método `onPostInit` não usa essa proteção e registra um atalho de teclado diretamente. Em um servidor dedicado isso pode causar `NoClassDefFoundError`, falha de carregamento ou comportamento dependente da ordem das classes.

**Ação recomendada:** remover esse JAR apenas da pasta `mods` do servidor. Ele pode permanecer nos clientes.

### 2. Conflito de Mixin entre CustomNPC+ e UniMixins — prioridade alta

Há 483 classes com o mesmo nome nos JARs `CustomNPC-Plus-1.11.1.jar` e `+unimixins-all-1.7.10-0.1.23.jar`; a maior parte pertence a `org.spongepowered`. As cópias não são iguais. Os dois tentam fornecer o mecanismo Mixin, e o primeiro a carregar pode definir uma versão incompatível para o outro.

Isto pode produzir erros como `MixinInitialisationError`, `NoSuchMethodError`, `InvalidInjectionException` ou crash durante a inicialização. Não é seguro apagar classes de um dos JARs.

**Ação recomendada:** usar uma combinação declaradamente compatível de CustomNPC+ e UniMixins. O log anterior citava UniMixins `0.3.1`, mas o JAR analisado aqui é `0.1.23`; confirme que o servidor e todos os clientes usam exatamente a mesma coleção e versões.

### 3. Correções ainda não estão dentro dos JARs — prioridade alta

O código em `codigo_mods/DBCAdditions-1.74/` contém as correções de formas, NBT, pacotes e dimensões. O binário `DBCAdditions-1.74.jar` na raiz continua sendo o original. Assim, a dimensão Arena Universal pode continuar retornando chunk nulo e gerando a esfera com custo excessivo se este JAR original for o que está no servidor.

**Ação recomendada:** não substituir nada manualmente ainda. Primeiro montar o projeto Forge 1.7.10 com as dependências exatas, compilar, testar em cópia do mundo e só então trocar o JAR.

### 4. Mundo e configurações ausentes — prioridade alta para investigar o crash real

Não há arquivos de dimensão, configurações de IDs, configurações de spawn, playerdata ou crash reports. Sem eles não dá para verificar colisão dos IDs 98, 99 e 100 do DBC Additions, corrupção de chunk, excesso de entidades ou configuração de formas em produção.

**Ação recomendada:** obter do servidor uma cópia de `config/`, `world/`, `logs/fml-server-latest.log` e o arquivo mais recente de `crash-reports/`. Preserve uma cópia antes de alterar ou regenerar qualquer dimensão.

### 5. Pressão de desempenho por scripts e entidades — prioridade média

CustomNPC+ mantém execução de scripts e Ninjin Entities adiciona muitas entidades. Isso não prova um crash, mas combina com os avisos `Can't keep up` do log anterior. A repetição de `reincarnated!` também deve ser investigada nos dados do jogador e nos scripts de NPC, pois pode estar criando trabalho repetido no thread do servidor.

**Ação recomendada:** verificar scripts ativos, limites de spawn e entidades por chunk na cópia do mundo. Não há evidência suficiente para atribuir isso a um mod específico.

## Pontos que não são causa comprovada

- `Controlling` só registra seu manipulador quando o lado é cliente.
- `Inventory Tweaks` usa proxy separado para servidor e não mostrou referência direta ao cliente no caminho comum.
- `JBRA-Client` tem proxy de servidor, apesar do nome do arquivo; não há evidência nesta revisão de que ele sozinho derrube o servidor.
- O trecho de log enviado anteriormente mostra encerramento limpo com salvamento de todos os mundos, não um crash do servidor. `Connection reset by peer` indica que o cliente fechou a conexão.

## Ordem segura de correção

1. Retirar Entity Culling da pasta de mods do servidor dedicado.
2. Confirmar um conjunto compatível de CustomNPC+ e UniMixins e igualar as versões entre servidor e clientes.
3. Guardar cópia de mundo e configurações; então analisar o crash report verdadeiro.
4. Compilar e testar o DBC Additions corrigido em uma cópia local do servidor antes de instalar em produção.
5. Só então analisar ou regenerar as dimensões afetadas, caso os logs indiquem ID em colisão ou chunk corrompido.
