## Back-end

Para a criação do Back-end foi a escolhida a *Opção 2* onde foram aplicadas as tecnologias:

* Aplicação pura Java EE
* RESTful API utilizando Jersey
* MySql
* Maven para controle de dependências

## Front-end

* AngularJS 1.6.1
* Ui-Router 0.4.0
* Bootstrap 3.3.7
* Ui-Bootstrap 2.4.0

## Ambiente de desenvolvimento

* Intellij
* JDK 8
* Jetty 9.3.15

## Arquitetura 

A estrutura do sistema foi dividida em packages por responsabilidades. 
* A Api é responsável por escutar as requisições HTTP e encaminhar a requisição para o Controller responsável.
* O Controller é responsável pelas regras de negocio e comunicação com os Daos.
* O Dao é responsável por realizar a comunicação com o banco de dados.
* Todas as informações são retornadas do Controller em forma de VO (Value Object), a ideia por tras disso é não expor a camada de persistência na camada de apresentação e para não precisar criar propriedades transients.
* Não foi utilizado o Bower e nem o NPM a fim de facilitar a instalação do ambiente para o projeto.

## Configuração do projeto

* Configurar um servidor Jetty
* Criar um novo schema 'testeContabilizei'
* Alterar as configurações de conexão do banco no arquivo persistence.xml (resources/META-INF/)
* Executar o build
* Executar a aplicação no Jetty

## Usando a aplicação

A aplicação pode ser acessada em http://localhost:8080. Existe um menu superior com o nome das funcionalidades, ao selecionar uma funcionalidade, a tela correspondente é exibida.

Fluxo simples da aplicação

1. Cadastrar cliente
2. Cadastrar notas fiscais para o cliente
3. Calcular impostos
5. Listar os impostos calculados
6. Marcar os impostos como pago

## Bonus

* Melhorar a interface do úsuario
* Transformar os métodos que realizam os cálculos dos impostos em assíncronos, para não impactar no desempenho do sistema