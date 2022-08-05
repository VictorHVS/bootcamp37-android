# Introdução

**Projeto do Bootcamp 37**

O objetivo do projeto é criar um aplicativo nativo em Android para para busca e avaliação de cervejarias.

Abaixo ficam links importantes para entender sobre o projeto:

- [VELA School - User Stories](https://docs.google.com/document/d/1ow-IllhqWt6jjztQuQ-tiqwNw2FRYWWrO_M-SQ0cGMw/edit#heading=h.ta0d9nypn100)
- [Bootcamp 37 - Planning Spreadsheet](https://docs.google.com/spreadsheets/d/1SSO_I4-K_IzvBsg4Ol_0DLcF7KqsmUqPrLHiOoAD9zE/edit#gid=0)
- [Artedatos do projeto](https://drive.google.com/drive/folders/1dvMVbjTamFsZi2fzX-z1jw6SI4cgYyNh)
- [VELA School - Boards (Jira)](https://jiracloud.cit.com.br/secure/RapidBoard.jspa?rapidView=36008&projectKey=BSCH&selectedIssue=BSCH-2526)

**Projetos relacionados**

- [API](https://dev.azure.com/vela-school/bootcamp37/_git/backend)
- [Web (React)](https://dev.azure.com/vela-school/bootcamp37/_git/frontend)

## Tecnologia

O projeto do aplicativo é desenvolvido em [Kotlin], utilizando retrofit como biblioteca para requisições http/https e o Android Jetpack para a criação de componentes.

O projeto não conterá persistencia nativa e só funcionara online.

## Inicialização

A recomendação para desenvolvimento é fazer o uso da ferramenta [Android Studio](https://developer.android.com/studio), basta baixar a IDE na ultima versão e abrir o projeto.

Uma vez aberto, você precisa configurar os parâmetros de execução do projeto, abaixo está um exemplo de como a configuração deve ser feita. É importante instalar o ultimo sdk corrente para o Android, como visto na tela abaixo, o Android emulator e o Androis Sdk Platform tools, na aba de SDK tools

![Install Android Studio](./assets/install.png)

---

**Rodando o projeto**

Para rodar o projeto, seja via emulador ou pelo próprio celular ( para isso o celular deve estar em modo de desenvolvedor), deve-se clicar no botão Run App, como mostrado na imagem abaixo. Em seguida o projeto será buildado e passará para o dispositivo escolhido, como  visto [aqui](https://developer.android.com/studio) 

![Create key](./assets/createkey.jpeg)


## Deploy
Para gerar uma versão, basta clicar em Build -> Build bundle/apk -> Build APK e ao final será gerado um .apk dentro da pasta app/build/output/debug. Também é possível gerar um apk assinado para realizar deploy na loja através dos passos abaixo.
- Para gerar uma versão de release, deve-se clicar em Build → Generate Signed Bundle/Apk → Apk e em seguida crie uma chave no botão abaixo de Key Store path. Clique em Next → Finish. O .apk se encontrará na pasta escolhida na tela anterior. Para fazer o deploy em qualquer aparelho Android, basta executar o .apk dentro do próprio aparelho. Para criação de versões na Store da Google, é possível através [desse](https://support.google.com/googleplay/android-developer/answer/9859152?hl=en) tutorial

## Referências

**Tecnologias**

- [Kotlin](https://developer.android.com/kotlin)
- [Retrofit](https://square.github.io/retrofit/)
- [Android guide](https://developer.android.com/guide)

**Ferramentas**

- [Android Studio](https://developer.android.com/studio)

