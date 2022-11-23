# Resource Account - Desafio MV

## Baixando Dependecias

Utilize o Maven para sincronizar as dependências do projeto.

## Banco de Dados

Para que não ocorra erros ou diferenças, utilize um banco de dados `MySQL` na `versão 8`.

`Siga o passo a passo do banco de dados do Authorization Server`. Depois, configure o acesso do Spring à esta database no arquivo `application.properties`.

O banco de dados é `compartilhado` entre o Authorization Server e Resource Account, para que os usuários cadastrados no Resource Account estejam disponíveis no Authorization Server para serem autenticados.

O `Authorization Server` irá criar automaticamente as tabelas das aplicações, por isso, execute ele primeiramente, para que as tabelas sejam criadas.

## Rodando o projeto

Execute no o método `main` do arquivo `Application`. O servidor irá ser executado na porta 8082 e será consultado pelo frontend e ResourceServer.

## Funcionalidades

- Criação de usuários.
- Consulta de dados do usuário logado.
- Alteração de email do usuário.
- Delete da conta de usuário.
- Utilização da chave pública do Authorization Server para validar os tokens informados.

## Dúvidas

- Entre em contato para que sejam esclarecidas.