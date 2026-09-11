# Revisao do pacote de mods

## Leitura do erro enviado

O servidor aceitou o jogador e concluiu o login. Um segundo depois o socket foi fechado pelo outro lado (`Connection reset by peer`). Portanto, esse trecho nao e um erro fatal do servidor: ele so registra que o cliente encerrou a conexao. A causa raiz estara no `logs/latest.log` ou em `crash-reports` **do cliente** de DRAKxz, no trecho imediatamente anterior ao horario 02:48:16.

## Problemas encontrados e situacao

| Prioridade | Problema | Situacao |
| --- | --- | --- |
| Alta | `npcgecko` declara os alvos de mixin como `kamkeel.addon.*`, mas o CustomNPC Plus instalado fornece `kamkeel.npcs.addon.*`. O mixin pode falhar ao carregar. | Corrigido no fonte descompilado do NPC Gecko. |
| Alta | CustomNPC Plus traz Mixin 0.7.11 embutido e Unimixins traz Mixin 0.8.7. Sao 482 classes `org.spongepowered` diferentes com o mesmo nome. A ordem de carregamento pode quebrar mixins. | Requer trocar por versoes compativeis dos JARs; nao e seguro apagar classes ou JARs sem testar a combinacao exata do servidor. |
| Alta | Entity Culling cria `KeyBinding`, classe exclusiva de cliente, durante a inicializacao. | Remover `entityculling-1.6.4-mc1.7.10.jar` somente da pasta `mods` do servidor dedicado. Mantenha-o nos clientes, se desejado. |
| Alta | O cliente podia enviar um ID de forma sem validacao no servidor. | Corrigido no fonte de DBC Additions. |
| Alta | Quando uma forma exigia varias habilidades, bastava possuir a primeira. | Corrigido no fonte de DBC Additions. |
| Alta | Custos extras de raciais, inclusive Majin 6--9, tinham valor padrao zero; o custo de Mind dos Namekians nem era aplicado. | Corrigido no fonte com uma progressao de TP/Mind proporcional aos custos ja existentes. Valores antigos de 0 ou 1 passam a usar a progressao recomendada. |
| Media | Pacotes de aprender/aprimorar/remover skill aceitavam IDs invalidos e podiam gerar `NullPointerException`. | Corrigido no fonte de DBC Additions. |
| Media | NBT antigo ou corrompido de habilidades/formas podia gerar erro de conversao ou `NullPointerException` quando o jogador abria menus ou alterava skills. | Corrigido no fonte: entradas invalidas sao ignoradas e forma ausente recebe dados de maestria neutros. |
| Media | Uma linha invalida em `Custom Skills` encerrava a leitura de todas as linhas seguintes; valores nao numericos interrompiam o carregamento. | Corrigido no fonte: a linha invalida e ignorada. |
| Media | Dados de fusao ausentes ou fora de sincronia podiam gerar `NullPointerException`/indice invalido. | Corrigido no fonte de DBC Additions. |
| Media | Pacote de absorsao confiava no nome e no alvo enviados pelo cliente. | Corrigido no fonte: o servidor usa o remetente real e valida criatura e distancia. |
| Media | O pacote de desejos aceita IDs enviados pelo cliente e merece revisao com o fluxo real de NPC/dimensoes. | Ainda depende dos arquivos de configuracao e do fluxo de jogo; nao foi alterado para evitar bloquear desejos legitimos. |
| Alta | O provedor da Arena Universal (dimensao 100) retornava `null` ao carregar chunk. Isso pode produzir vazio e `NullPointerException` ao entrar. | Corrigido no fonte para gerar e retornar o chunk solicitado. |
| Alta | A Arena Universal examinava mais de quatro milhoes de posicoes com calculos de ponto flutuante durante a primeira geracao. Isso pode travar o thread do servidor e provocar timeout. | Corrigido no fonte com geracao direta apenas da casca da esfera. |
| Media | Os tres provedores deixavam `BlockFalling.fallInstantly` ligado se um evento de geracao lancasse excecao. | Corrigido com `try/finally` em Sacred World, Beerus Planet e Arena Universal. |
| Media | Teleportes por comando ou desejo construíam `Teleporter` com mundo ausente quando a dimensao nao estava disponivel. | Corrigido com validacao do mundo de destino e posicao segura de reserva. |
| Media | DBC Additions registra dimensoes 98, 99 e 100 fixas; pode haver colisao com outro mod. | Precisa conferir o `fml-server-latest.log` e a configuracao do servidor. |

## Regra de formas pagas

Os fontes corrigidos bloqueiam no servidor a transformacao selecionada sem todos os requisitos e aplicam custos progressivos. Isso inclui os niveis extras Majin 6--9.

| Raca / nivel extra | TP | Mind |
| --- | ---: | ---: |
| Saiyajin 8 / 9 | 120000 / 240000 | 30 / 45 |
| Humano 6 / 7 / 8 / 9 | 15000 / 30000 / 40000 / 60000 | 20 / 25 / 30 / 40 |
| Arcosiano 7 / 8 / 9 | 66000 / 99000 / 150000 | 25 / 35 / 50 |
| Majin 6 / 7 / 8 / 9 | 85000 / 145000 / 250000 / 425000 | 25 / 35 / 50 / 70 |
| Namekian 6 / 7 / 8 / 9 | 90000 / 135000 / 205000 / 310000 | 25 / 35 / 50 / 70 |

As formas customizadas tambem usam requisitos de habilidade proporcionais: Potential Unlocked/Orange usa 40000 TP e 25 Mind por nivel; Beast, 75000 TP e 10 Mind; Black, 100000 TP e 50 Mind.

As formas e custos nativos do Dragon Block C/JRMCore usam o arquivo de configuracao real do servidor, que nao esta nesta pasta. Eles tambem devem estar acima de zero nesse arquivo; sem ele nao e possivel confirmar nem alterar os valores que estao em producao.

## Arquivos alterados

- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/DBCAConfig.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/data/forms/FormItem.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/packets/DBUPacketSelectForm.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/packets/DBUPacketLearnSkill.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/packets/DBUPacketUpgradeSkill.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/packets/DBUPacketRemoveSkill.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/packets/DBUPacketAbsorb.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/utils/DataUtils.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/mixin/late/packet/MixinDBCPacketHandler.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/mixin/late/MixinJRMCoreComTickH.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/dimensions/chunkprovider/ChunkProviderUA.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/dimensions/chunkprovider/ChunkProviderKai.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/dimensions/chunkprovider/ChunkProviderBW.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/dimensions/worldgen/UniversalArena.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/dimensions/TeleporterDBUtils.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/command/DimensionCommand.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/packets/DBUPacketWish.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/data/DBCAPlayer.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/data/skills/DBCASkills.java`
- `codigo_mods/DBCAdditions-1.74/java/com/tobiasmjc/dbcadditions/data/forms/DBCAFormMastery.java`
- `codigo_mods/npcgecko-1.0/java/com/goodbird/npcgecko/mixin/impl/MixinGeckoAddon.java`
- `codigo_mods/npcgecko-1.0/java/com/goodbird/npcgecko/mixin/impl/MixinGeckoAddonClient.java`
- `codigo_mods/JRMCore-v1.3.51/java/JinRyuu/JRMCore/server/config/dbc/JGConfigSkillsDBC.java`

Os JARs originais nao foram modificados. Estes sao fontes descompilados e exigem um ambiente Forge 1.7.10 com as dependencias exatas para compilacao e empacotamento antes de poderem ser instalados no servidor.
