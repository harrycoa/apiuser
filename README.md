
# README para el reto tecnico de Nisum

## Métodos Disponibles

## 1. Obtener Detalles de Usuario por ID (`GET /user/{id}`)

**Descripción:**  
Este endpoint permite obtener los detalles de un usuario específico por su identificador único (UUID).

**URL:** `/user/{id}`

**Método:** `GET`

**Parámetros de URL:**
- `id` (UUID): Identificador único del usuario.

**Respuestas:**
- **200 OK:** Si el usuario existe, retorna los detalles del usuario.
- **404 Not Found:** Si no se encuentra un usuario con el ID proporcionado.

**Ejemplo de Respuesta Exitosa:**
```json
{
    "id": "UUID-del-usuario",
    "name": "Nombre del Usuario",
    "email": "correo@ejemplo.com",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ],
    "active": true
}
```

## 2. Crear un Nuevo Usuario (`POST /user`)

## POST /user

Este endpoint se utiliza para crear un nuevo usuario en el sistema.

### Request

**URL:** `/user`

**Método:** `POST`

**Headers:**
- `Content-Type: application/json`

**Body:**
```json
{
  "name": "Nombre del usuario",
  "email": "correo@ejemplo.com",
  "password": "ContraseñaSegura123!",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
```

**Respuestas:**
- **201 OK:** Retorna los detalles del usuario recién creado.
- **400 Bad Request:** Si el correo ya está registrado o si los datos proporcionados no son válidos.

**Ejemplo de Respuesta Exitosa:**
```json
{
    "id": "UUID-del-usuario",
    "created": "2020-08-01T00:00:00.000Z",
    "modified": "2020-08-01T00:00:00.000Z",
    "last_login": "2020-08-01T00:00:00.000Z",
    "token": "JWT-token"
}
```
**Ejemplo de Respuesta Erronea:**

```json
{
    "code": "400",
    "message": "El correo ya fue registrado"
}
```

## 3. Pasos para probar el endpoint
### git clone https://github.com/harrycoa/apiuser.git

#### 1.- Abrir con el editor de su preferencia y Tener instalado java 17
#### 2.- El script data.sql contiene instrucciones sql de insert y las tablas son creadas automaticamente con JPA
#### 3.- Ejecutar el proyecto con el comando mvn spring-boot:run
#### 4.- Una vez ejecutado el api probar con los curl de ejemplo:

#### Curl Post

```json
curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=2328D42CF5CA13D4E3CEC65BE5F508BA' \
--data-raw '{
"name": "Juan Rodriguez2",
"email": "juan45@rodriguez.org",
"password": "@Hunter2",
  "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "+57"
        },
        {
            "number": "2345678",
            "citycode": "2",
            "countrycode": "+58"
        }
  ]
}
'
```
##### Curl Get
```json
curl --location 'http://localhost:8080/user/50da4f40-add8-40b8-8bbd-45225865dedc' \
--header 'Cookie: JSESSIONID=2328D42CF5CA13D4E3CEC65BE5F508BA'
```

