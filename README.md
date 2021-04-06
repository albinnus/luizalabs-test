## **Teste Luizalabs**

Criação de API para gerenciar a lista de produtos favoritos de um determinado cliente.

## Tecnologias

- SpringBoot
- Lombok
- Mysql (Gerenciamento de usuário e cliente)
- MongoDB (Gerenciamento da Lista de produtos do cliente)
- Redis (Cacheamento e perfomance)

## Ambiente


- Docker: 
    * redis: ``` docker run --name redissrv -p 6379:6379 -d redis```
    * mysql: ````docker run --name mysqlsrv -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345 -d mysql````
    * mongodb: ```docker run --name mongosrv -p 27017:27017 -d mongo```
    
## Configuração da Aplicação
Criar banco de dados no mysql, no caso o nome padrão na aplicação esta test.
```sql
CREATE  database test;
```
No application.yml modificar as conexões conforme configuração do ambiente
- Mysql
    ```yaml
  spring:
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       username: root
       password: 12345
       url: jdbc:mysql://localhost:3306/test
  ```
  modificar conforme configuração do banco de dados 
  
- Redis
```yaml
redis:
    host: localhost
```
Caso esteja usando com login e senha e outro host, adicionar os parâmetros username e password e modificar o parâmetro host na parte de configuração do redis

- MongoDB
```yaml
 data:
    mongodb:
      host: localhost
```
Caso esteja usando com login e senha e outro host, adicionar os parâmetros username e password e modificar o parâmetro host na parte de configuração do mongodb

## Compilação e execução

Entrar na pasta do código e executar mvn (ou caso não tenha instalado usar o mvnw que vem no pacote da aplicação). Nessa etapa é importante ter a variável de ambiente JAVA_HOME setada 
````
mvn clean
mvn install
````
Após compilado entrar na pasta target e executar o.jar
````
java -jar test-0.0.1-SNAPSHOT.jar
````

### Requests e Payloads
Na migração já criada, para fim de testes, foi criado um usuário com as seguintes credenciais:
```
"email":"teste@teste.com",
"password": "123"
```

Só é possível acessar os recursos da api após login, utilizando o Bearer Token gerado por ela.

Login
- Post: /login
- Payload: 
```json
{
  "email": string,
  "password": string
}
```
- Exemplo
````json
{
  "email":"teste@teste.com",
  "password": "123"
}
````

- Retorno

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJleHAiOjE2MTc3MDk5NjgsImlhdCI6MTYxNzcwODE2OH0.ST7CsjWbi1EWEIMx0gIhnK42z2GGSM7f7avwhzKsNu7swVNdRK5sw1sg2gvsANZkOjjyyx35NNu1kyvrJo-dKw"
}
```
Header Auth

Utilizar o padrão Bearer <token> após a autenticação para acessar o restante dos endpoints

Exemplo Bearer:  
````
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJleHAiOjE2MTc3MDc5NTEsImlhdCI6MTYxNzcwNjE1MX0.liUtuhBsARRTJE8A3dA8snAlooAR0UBNrV8upxmdHLiD0j_q7xwmsrF9TNPGvVNN0Idv2ntMe7X-R5Sz3-cOEA
````
Exemplo Header: 
````
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZUB0ZXN0ZS5jb20iLCJleHAiOjE2MTc3MDc5NTEsImlhdCI6MTYxNzcwNjE1MX0.liUtuhBsARRTJE8A3dA8snAlooAR0UBNrV8upxmdHLiD0j_q7xwmsrF9TNPGvVNN0Idv2ntMe7X-R5Sz3-cOEA
````
### Criar Cliente
- POST: /client
- Payload:
```json
{
  "name": string,
  "email": string
}
```
- Exemplo
````json
{
  "name": "teste",
  "email":"teste@teste.com"
}
````

- Retorno

```json
{
  "id": 15,
  "name": "teste",
  "email": "teste@teste.com"
}
```

### Atualizar Cliente
- PUT: /client/{Long idClient}
- Payload:
```URL: /client/15```
```json 
{
  "name": string,
  "email": string
}
```
- Exemplo
````json
{
  "name": "teste",
  "email":"teste34@teste.com"
}
````

- Retorno

```json
{
  "id": 15,
  "name": "teste",
  "email": "teste34@teste.com"
}
```

### Consultar Cliente
- GET: /client/{Long idClient}
- Exemplo
````
URL: /client/15
````

- Retorno

```json
{
  "id": 15,
  "name": "teste",
  "email": "teste34@teste.com"
}
```


### Adicionar Produtos a Lista do Cliente
- POST: /client/{Long clientId}/products
- Payload:
  
```json 
{
 
	"productId": UUID (String)

}
```
- Exemplo
  ```URL: /client/15```
````json
{
  "productId": "6c097dc3-0c93-65fe-d88b-3b53acbf1fd7"
}
````

- Retorno

```json
Status: 200
```

os ids dos produtos adicionados foram consultados pela api fornecida previamente:
```http://challenge-api.luizalabs.com/api/product/1/```

### Consultar Produtos da Lista do Cliente
- GET: /client/{Long userId}/products?page=1

- Exemplo
````
URL:  /client/15/products?page=1
````

- Retorno

```json
{
  "products": [
    {
      "id": "1cc8ece1-895e-5d2a-de69-ad2d7884e722",
      "price": 556.9,
      "image": "http://challenge-api.luizalabs.com/images/1cc8ece1-895e-5d2a-de69-ad2d7884e722.jpg",
      "brand": "pontto lavabo",
      "title": "Assento Sanitário Cristal Translúcido Century",
      "reviewScore": null
    }
  ]
}
```
A consulta está paginada, a configuração default está em 10 itens por página, podendo ser mudada no application yml.

### Remover Produtos a Lista do Cliente
- DELETE: /client/{Long clientId}/products
- Payload:

```json 
{
 
	"productId": UUID (String)

}
```
- Exemplo
  ```URL: /client/15/products```
````json
{
  "productId": "6c097dc3-0c93-65fe-d88b-3b53acbf1fd7"
}
````

- Retorno

```json
Status: 200
```

### Remover  Cliente
- DELETE: /client/{Long clientId}
- Exemplo
  ```URL: /client/15```
- Retorno

```json
Status: 200
```
