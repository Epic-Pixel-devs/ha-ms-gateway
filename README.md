# Spring Cloud Gateway

## Sumário

* [Introdução](#introdução)
    + [Objetivo](#objetivo)
    + [Evolução](#evolução)
* [Instalação](#instalação)
    + [Pré-requisitos](#pré-requisitos)
    + [Configuração da Aplicação](#configuração-da-aplicação)
        - [Perfil `default`](#perfil-default)
        - [Perfil `local`](#perfil-local)
* [Utilização](#utilização)
    + [Compilação](#compilação)
    + [Execução da Aplicação](#execução-da-aplicação)
    + [Contratos de APIs](#contratos-de-apis)
* [Documentação](#documentação)
* [Contribuições](#contribuições)
* [Equipe](#equipe)
* [Licença](#licença)

## Introdução

Serviço de Gateway usando `Spring Cloud Gateway` para fazer gerenciamento de roteamento para
microsserviços
responsável por direcionar o acesso para o respectivo serviço e também fazer a validação de
autenticação
de acesso assim como fazer o encerramento de sessão ou fazer o redirecionando para tela de login

### Objetivo

> TODO: descreva o objetivo e principais funcionalidades contempladas por esse Gateway.

### Evolução

> TODO: descreva as potenciais funcionalidades que ainda serão desenvolvidas nesse Gateway .

## Instalação

Baixe o código-fonte desse repositório usando `git clone` ou de forma manual (.zip).
Para prosseguir com a instação deste projeto é necessário atender a tabela de pré-requisitos com
componentes na versão
especificada ou superior.

### Pré-requisitos

| Componente   | Versão | Descrição                      |
|--------------|--------|--------------------------------|
| OpenJDK      | 17     | Plataforma Java                |
| Apache Maven | 3.6.1  | Build e Gestão de Dependências |
| Spring Cloud | 4.1.1  | Spring Starter Gateway         |

> TODO: inclua as demais dependências e tecnologias do seu projeto (ex: Redis, Oracle, RabbitMQ,
> etc.)

### Configuração da Aplicação

A aplicação foi planejada para ser executada em dois ambientes distintos compartilhando o máximo de
similaridades possíveis.
Para isso foram criados dois perfis (_profiles_). Detalhes das configurações, profiles e sua
utilização estão na próxima seção.

#### Perfil `default`

Por padrão a aplicação está configurada para executar no ambiente de produção. Portanto, o
_profile_ `default`
refere-se as configurações que serão utilizadas durante a operação real do sistema.

#### Perfil `local`

Um segundo _profile_ `local` está definido e sobreescreve os valores de algumas configurações do
_profile_ `default`
para que a aplicação execute corretamente no ambiente local do desenvolvedor.

## Utilização

Abaixo seguem instruções de uso do projeto. Os comandos devem ser executados no terminal, ou
diretamente na IDE de preferência
do desenvolvedor.

### Compilação

Para fazer o download de dependências e compilar o código-fonte da aplicação, navegue até o
diretório raiz do projeto execute:

```bash
mvn clean package
```

### Execução da Aplicação

Para executar a aplicação em modo de desenvolvimento, o _profile_ `local` deve ser ativado
explicitamente:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

A aplicação estará executando no endereço http://localhost:8080/base/v1/

Também é possível simular uma execução do ambiente de produção no seu ambiente local.
Para isso será necessário declarar explicitamente todas as variáveis de ambiente utilizadas pela
aplicação.

Isso pode ser feito diretamente na sua IDE, ou pela linha de comando.

Abaixo um exemplo de como especificar as variáveis na linha de comando:

```bash
VARIAVEL_1=valor1 \
VARIAVEL_2=valor2 \
mvn spring-boot:run
```

### Contratos de APIs

Este projeto utiliza a especificação OpenAPI para documentação e catálogo de APIs.
Depois de executar a aplicação, é possível consultar todas operações fornecidas pelo serviço e
testá-las respectivamente
acessando o endereço http://localhost:8080/swagger-ui.html.

## Documentação

> TODO: adicione o link para o Confluence ou outra fonte de documentação do projeto.

## Contribuições

Para reportar _bugs_, sugerir novas funcionalidades e melhorias entre em contato com alguns dos
membros responsáveis
por este projeto, ou submeta um _Pull Request_ seguindo as práticas e convenções de desenvolvimento
desse projeto.
> TODO: inclua detalhes adicionais de como outras pessoas podem contribuir com esse projeto.

## Equipe

Responsáveis pelo projeto:

| Nome          | Email                          |
|---------------|--------------------------------|
| Márcia Helena | marcia.javadeveloper@gmail.com |
| Roberto Rosa  | roberto.rosa7@gmail.com        |
| Responsável 3 | responsavel3@contato.com       |

> TODO: inclua os nomes e os contatos dos responsáveis pelo projeto.

