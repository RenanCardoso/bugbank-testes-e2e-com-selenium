# Testando aplicação BugBank com Selenium WebDriver na linguagem Java

Olá, seja muito bem-vindo(a)!

Fiz este projeto de exemplo para demonstração de testes automatizados escritos com [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/) utilizando a aplicação [BugBank](https://bugbank.netlify.app) que foi desenvolvida e é mantida por [Jhonatas Matos](https://github.com/jhonatasmatos).

## Ferramentas utilizadas:
- [Gradle](https://gradle.org "Gradle")
- [Java](https://www.java.com/pt_BR/ "Java")
- [JUnit](https://junit.org/junit4/ "JUnit")
- [Selenium](https://www.seleniumhq.org/ "Selenium")
- [ChromeDriver](https://chromedriver.chromium.org/downloads "ChromeDriver")
- [PageFactory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory "PageFactory")
- [PageObject (pattern)](https://martinfowler.com/bliki/PageObject.html "PageObject")
- [Fluent Interface (pattern)](https://www.lambdatest.com/blog/fluent-interface-design-pattern/ "Fluent Interface")
## Pré-requisitos

Para baixar e rodar este projeto, você precisará das seguintes tecnologias instaladas em seu computador:

- [git](https://git-scm.com/downloads) (usei a versão `2.47.0` enquanto escrevia este documento)
- [Java JDK](https://www.oracle.com/java/technologies/downloads/#java17) (usei a versão `17.0.12` enquanto escrevia este documento)
- npm (usei a versão `10.8.2` enquanto escrevia este documento)
- [IntelliJ IDEA](https://git-scm.com/downloads) (usei a versão `IntelliJ IDEA 2024.2.3 (Community Edition)` enquanto escrevia este documento)

**Obs:** Algumas versões do Linux já vem com o Java instalado. Entretanto, isso não é regra padrão para as distros linux.

Para verificarmos se o Java já está instalado na nossa maquina, devemos digitar o seguinte comando no terminal: 
`java --version`

## Instalação

Após clonar o projeto, abra o arquivo build.gradle no IntelliJ IDEA e clique em LifeCycle > Package para instalar as dependências de desenvolvimento.

No seu Google Chrome vá na opção Ajuda e Sobre o Google Chrome, lá vai informar a versão do seu Chrome, o meu por exemplo está na Versão 129.0.6668.100 (Versão oficial) 64 bits.

Sabendo isso, vá agora no site do chromedriver https://chromedriver.chromium.org/downloads e realize o download da versão do chromedriver igual a do seu. Irá vir um arquivo .rar, você extrai e terá um executável.

**Obs:** Em caso de dúvidas, consulte a [documentação](https://www.selenium.dev/documentation/webdriver/getting_started/) oficial do Selenium WebDriver.

## Executando os testes

Para executar os testes, basta clicar com o botão direito sobre a classe, e selecionar "Run ". 

___

Feito com ☕ e ❤️ por [Renan](https://github.com/RenanCardoso).