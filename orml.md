##Evidências — Atividade CRUD com ORM (ORMLite/SQLite)
Este documento comprova a execução bem-sucedida das operações CRUD na classe modelo Pokemon utilizando o ORMLite.

CREATE — Inserção de Dados
Os registros foram criados no banco de dados através do método create() do DAO.

![Print Create](caminho/para/sua/imagem_create.png)

RETRIEVE — Leitura de Dados
Os dados foram lidos do banco utilizando os métodos queryForAll() e queryForId().

![Print Retrieve](caminho/para/sua/imagem_retrieve.png)

UPDATE — Atualização de Dados
O nível e o tipo do Pokemon foram alterados através do método update() do DAO.

![Print Update](caminho/para/sua/imagem_update.png)

DELETE — Exclusão de Dados
Um registro foi removido do banco de dados utilizando o método deleteById().

![Print Delete](caminho/para/sua/imagem_delete.png)

Conclusão
As quatro operações CRUD foram executadas com sucesso através do mapeamento objeto-relacional (ORM) com ORMLite e SQLite. O código demonstrou a persistência e manipulação dos dados sem a necessidade de escrever queries SQL manualmente.
