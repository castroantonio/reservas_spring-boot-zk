# *Java* com *Spring Boot* e *ZK*


## Introdução

*Spring Boot* é uma ferramenta que traz vantagens para o mundo *Java*. O início rápido é uma destas vantagens, em poucos minutos é possível definir o conjunto de ferramentas que se deseja usar em um projeto. Outra vantagem é a pouca necessidade de configuração, se você segue uma convenção não precisa configurar.

Mas nem tudo são flores, o *Spring Boot* originalmente não fornece a opção de uso do framework [ZK](https://www.zkoss.org/). Este trabalho explora esta lacuna, mostrando um projeto *Java* com *Spring Boot* e *ZK*. O projeto consta do código fonte, o qual pode servir como referência; na execução do projeto as telas fornecem dicas e informações; por fim, este documento, um passo a passo para a criação de um projeto do início.

Na próxima seção temos o passo a passo para a criação de projetos *Java* com *Spring Boot* e *ZK*. Logo após vamos além do projeto básico, utilizando o padrão *View Model* e serviços. Depois falamos do projeto existente neste repositório, um projeto de reserva de quartos. Por fim, temos as referências.


## Criando um projeto usando Java com Spring Boot e ZK (passo a passo)

- Se houver suporte na sua IDE, então, inicie um projeto *Spring Boot*, caso contrário, faça o download e descompacte a partir do [spring initializr](https://start.spring.io/). **Não é necessária dependência alguma**. Contudo, o projeto deste repositório adiciona: *Spring Web* e *Spring Boot DevTools*.

- No arquivo *pom.xml*, adicione o repositório maven do *ZK*:
  
```
  ...
  <repositories>
    <repository>
      <id>ZK CE</id>
	  <name>ZK CE Repository</name>
	  <url>https://mavensync.zkoss.org/maven2</url>
    </repository>
  </repositories>
  ...
```

- Ainda no arquivo *pom.xml*, adicione a dependência *zkspringboot-starter*, na última versão disponível no repositório, verifique qual é [aqui](http://mavensync.zkoss.org/maven2/org/zkoss/zkspringboot/zkspringboot-starter/):

```
  ...
  <properties>
    ...
	<zkspringboot.version>2.5.3</zkspringboot.version>  ## O valor desta tag é usado em ${zkspringboot.version}
	...
  </properties>
  ...
  <dependencies>
    ...
  <dependency>
	  <groupId>org.zkoss.zkspringboot</groupId>
	  <artifactId>zkspringboot-starter</artifactId>
	  <type>pom</type>
     <version>${zkspringboot.version}</version>  ## Aqui é usado o valor definido em <properties>
    </dependency>
    ...
  <dependencies>
```

- Na pasta *src/main/resources/web/*, crie um arquivo zul, que servirá de página inicial (o subdiretório *web* não existia, eu criei por boa prática). Neste exemplo criamos o *principal.zul*.

- No arquivo *src/main/resources/application.properties* defina a página inicial da aplicação, neste exemplo, o arquivo *principal.zul*:

```
zk.homepage=principal
```

- Tudo pronto! Já dá pra testar. Se sua IDE tem suporte ao Spring Boot, abra *src/main/java* no pacote principal da sua aplicação existe um arquivo *NOME_APLICACAOApplication.java*, abra ele. Clique com o botão direito, no menu selecione *Run As - Spring Boot App*. Se sua IDE não tem suporte ao *Spring Boot*, vai via Maven mesmo:

```
mvnw spring-boot:run
```

- No seu navegador acesse: *http://localhost:8080/*

Pronto, já temos um projeto *Java* com *Spring Boot* e *ZK* rodando. O próximo tópico aborada o projeto existente neste repositório.


## Indo além

É possível fazer mais que uma página simples. Esta seção mostra como utilizar o padrão View Model com o ZK no Spring Boot, fazendo uso de serviços.

De pois de criar cada página *.zul* é necessário criar o referente View Model, uma classe *Java*. A ligação entre ambos é feita através do atributo *viewModel*, que deve ser adicionado na página *.zul* conforme o exemplo:


```
<zk xmlns:n="native">
	<window title="Java com Spring Boot e ZK" border="normal"
	    viewModel="@id('vm') @init('com.castroantonio.reservas.controller.ReservarViewModel')">
	...
	...
	...
	</window>
</zk>
```

No *View Model* é comum o uso de serviços. As classes referentes a tais serviços devem ser anotadas com *@Service* (uma anotação do Spring). Para fazer injeção de dependência na *View Model* é necessário adicionar a dependência do **ZK Plus Utilities** no *pom.xml*. Conforme o exemplo:

```
  <dependencies>
    ...
	<dependency>
	    <groupId>org.zkoss.zk</groupId>
	    <artifactId>zkplus</artifactId>
	    <version>9.6.0.1</version>
	</dependency>
    ...
  <dependencies>
```

Com isso não é necessário instanciar o serviço, basta anotá-lo com *@WireVariable*, desta forma:

```
	@WireVariable  // faz injeção de dependencia do servico para o View Model
	private ServicoReserva servicoReserva;  // a classe deve estar anotada com @Service
```

Dessa forma temos todo o necessário para o desenvolvimento de um projeto *Java* com *Spring Boot* e *ZK*, o resto é o uso comum das ferramentas. O projeto contido neste repositório tem alguns exemplos dos seguintes tópicos no código fonte do projeto:

 - página dinâmica que usa parâmetros fornecidos pelo usuário para buscar dados em um serviço;
 - passagem de parâmetros para a View Model no clique do botão;
 - passagem de parâmetros de uma página para a outra (através do *View Model*);
 


## O Projeto - Reserva de Quartos

Com o principal intuito de ser simples e didático, este projeto traz o domínio de reserva de quartos. Não usa banco de dados, alguns dados são mocados (os referentes aos quartos), outros permanecem em memória apenas durante a execução do projeto (reservas e clientes).


#### Minimundo

Um cliente deseja reservar um quarto, para isso informa o período de estadia. Deve-se verificar se há quartos disponíveis para o período desejado e mostrar as opções ao cliente. O cliente deve selecionar uma das opções disponíveis, assim a reserva é efetuada.


## Referências

   - [Create and Run Your First ZK Application with Spring Boot](https://www.zkoss.org/wiki/ZK_Installation_Guide/Quick_Start/Create_and_Run_Your_First_ZK_Application_with_Spring_Boot)
   - [Digital Innovation One](https://digitalinnovation.one)