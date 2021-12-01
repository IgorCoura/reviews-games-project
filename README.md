# reviews-games-project
Trabalho T3 e T4 da matéria ECM251-Linguagens de Programação I.

Grupo: 404- Not found
Integrantes: 
 Guilherme Cury Galli RA:19.00374-9; 
Igor Eiki RA:19.02466-5; 
Fernando Laiser RA:19.01336-0; 
Igor de Brito Coura RA: 19.02466-5; 

Esse projeto consiste em um back-end, um front-end e sua integração de um site de avaliação de jogos.

# Design 
```
- src/
    |- main/
        |- kotlin/
            |- component: Funções utilizadas no sistemas.
            |- dao/ : Conexões com DB, métodos de DBs, etc 
            |- dto/ : Objeto de Transferência de Dados (DTO -> MODELS)
            |- routes/ : Onde é definido as routas de acesso a API.
            |- interfaces/ : qualquer interface usada globalmente na aplicação
            |- models/ : models usados na aplicação
            |- service/ : Onde são definidas as regras de negócio
            |- Application.kt : Configurações para inicialização da aplicação            
```
### Model relacional
![alt text](https://github.com/IgorCoura/reviews-games-project/blob/master/img/ModeloRelacionalV2.png)

# Routes
* A autentição é feita atráves de Bearer Token.
### Raiz
* http://localhost:8080/
### Games
* POST: "/games" -> Armazena um novo jogo. Necessário permissão de Manager.
  ```
  Entrada esperada -> 
  {
    "name": "text",
    "summary": "text",
    "developer": "text",
    "genre": "text",
    "score": int,
    "img": "text",
    "release": "text",
    "consoles": "text"
  } 
  Saída esperada -> "Game stored correctly"  
  ```  
* POST: "/games/upoload/{id}" -> Armazena a foto do jogo. Necessário permissão de User.
```
  Entrada esperada -> file
  Saída esperada-> "'fileName' is uploaded."
```  
* PUT: "/games" -> Atualizar as informações de um jogo. Necessário permissão de Manager.
  ```
  Entrada esperada -> 
  {
    "id": int,
    "name": "text",
    "summary": "text",
    "developer": "text",
    "genre": "text",
    "score": int,
    "img": "text",
    "release": "text",
    "consoles": "text"
  } 
  Saída esperada -> "Game update correctly"    
  ```  
* GET: "/games" -> Recupera todas os jogos.
   ```
  Saída esperada -> 
  [
  {
        "id": int,
        "name": "text",
        "summary": "text",
        "developer": "text",
        "genre": "text",
        "score": int,
        "img": "text",
        "release": "text",
        "consoles": "text"
    },
  ]
  ```  
  * GET: "/games/{id}" -> Recupera um jogo específico junto com todos os seus reviews.
   ```
  Saída esperada -> 
  [
  {
        "id": int,
        "name": "text",
        "summary": "text",
        "developer": "text",
        "genre": "text",
        "score": int,
        "img": "text",
        "release": "text",
        "consoles": "text"
        "reviews": [
        {
            "id": int,
            "review": "text",
            "score": int,
            "date": "text",
            "user": {
                "id": int,
                "name": "text",
                "email": "text"
            }
        }
    },
  ]
  ``` 
* GET: "/games/img/{id}" -> Recupera a imagem do jogo.
```  
  Saída esperada -> fileName.png
``` 
* DELETE: "/games/{id}" -> Deletar um jogo existente. Necessário permissão de Manager.
 ```
 Saída esperada -> "Game delete correctly" 
  ``` 
### Reviews
* POST: "/review" -> Armazena um novo review. Necessário permissão de User.
 ```
  Entrada esperada -> 
  {
    "gameId": int,
    "review": "text",
    "score": int,
    "date": "text"
  } 
  Saída esperada -> "Review stored correctly"  
  ```  
 * PUT: "/review" -> Atualiza as informações de um review. Necessário permissão de User.
  ```
  Entrada esperada -> 
  {
    "id": int,
    "gameId": int,
    "review": "text",
    "score": int,
    "date": "text"
  } 
  Saída esperada -> "Review update correctly"  
  ``` 
 * GET: "/review/{id}" -> Recupera um review específico. Necessário permissão de User.
  ```
 Saída esperada -> 
  {
    "id": int,
    "gameId": int,
    "userId": int,
    "review": "text",
    "score": int,
    "date": "text"
  } 
  ``` 
* DELETE: "/review/{id}" -> Remove um review existente. Necessário autorização de User.
 ```
 Saída esperada -> "Review delete correctly" 
  ``` 
### User
* POST: "/login" -> Válida o usuário e devolve um token de validação.
 ```
  Entrada esperada -> 
  {
     "email": "text",
     "password": "text",
  }
  Saída esperada ->
  {
     "token": "text"
  }
  ``` 
* POST: "/user" -> Armazena um novo usuário.
 ```
  Entrada esperada -> 
  {
     "name": "text",
     "email": "text",
     "password": "text",
     "img": "text"
  }
  Saída esperada -> "User stored correctly"  
  ``` 
* POST: "/user/upoload" -> Armazena a foto de perfil do usuario. Necessário permissão de User.
```
  Entrada esperada -> file
  Saída esperada-> "'fileName' is uploaded."
```
* PUT: "/user" -> Atualiza as informações do usuário. Necessário permissão de User.
   ```
  Entrada esperada -> 
  {
    
     "name": "text",
     "email": "text",
     "password": "text",
     "img": "text"
  }
  Saída esperada -> "User update correctly"  
  ``` 
* GET: "/user/img/{id}" -> Recupera a imagem de perfil do usuario.
```  
  Saída esperada -> fileName.png
``` 
* GET: "/user" -> Recupera o usuário.
 ```
  Saída esperada -> 
  {
    "id": int
     "name": "text",
     "email": "text",
  }
  ``` 
* GET: "/user/reviews" - Recupera o usuário junto com todos os seus reviews.
 ```
  Saída esperada -> 
  {
    "id": int
     "name": "text",
     "email": "text",
     "reviews": [
        {
            "id": int,
            "review": "text",
            "score": int,
            "date": "text",
            "game": {
                "id": int,
                "name": "text"
            }
        }
    ]
  }
  ``` 
* DELETE: "/user" -> Remove um usuário específico. Necessário permissão de User.
 ```
 Saída esperada -> "User delete correctly" 
  ``` 
* GET: "/user/{id}" -> Recupera um usuário específico. Necessário permissão de Manager.
 ```
  Saída esperada -> 
  {
    "id": int
     "name": "text",
     "email": "text",
  }
```
* GET: "/user/all" -> Recupera todos os usuários. Necessário permissão de Manager.
 ```
  Saída esperada -> 
  [
    {
    "id": int
     "name": "text",
     "email": "text",
    }
  ]
```
* DELETE: "user/{id}" -> Remove um usuário específico. Necessário permissão de Manger.
 ```
 Saída esperada -> "User delete correctly" 
  ``` 
### Manager
* POST: "/manager" -> Válida o gerente e devolve um token de validação.
  ```
  Entrada esperada -> 
  {
     "email": "text",
     "password": "text",
  }
  Saída esperada ->
  {
     "token": "text"
  }
  ``` 

# Telas do FrontEnd
As telas foram implementadas através do Flutter.

<p float="center">
  <img src="https://github.com/Igor-Kubota/TesteFolders/blob/master/img/Tela1.png" width="272" />
  <img src="https://github.com/Igor-Kubota/TesteFolders/blob/master/img/Tela2.png" width="272" />
</p>

As telas abaixo repetem os padrões para os diferentes jogos presentes na HomePage.
<p float="center">
  <img src="https://github.com/Igor-Kubota/TesteFolders/blob/master/img/tela3.png" width="272" />
  <img src="https://github.com/Igor-Kubota/TesteFolders/blob/master/img/tela4.png" width="272" />
  <img src="https://github.com/Igor-Kubota/TesteFolders/blob/master/img/tela5.png" width="272" /> 

</p>


