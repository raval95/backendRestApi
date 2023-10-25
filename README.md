# Rest_Api

usługa w oparciu o Spring, maven

baza danych Mongo Db została uruchomiona lokalnie na mongodb://localhost:27017

do komunikacji i testowania wykorzystano Postman:

    GET http://localhost:8080/users/{login} 

pobiera informacje o użytkowniku z api.github

zapisuje informacje o ilości wywołań API dla każdego loginu

zwraca JSONObject:

{

"id": "...",

"login": "...",

"name": "...",

"type": "...",

"avatarUrl": „”,

"createdAt": "..."

"calculations": "..."

}
