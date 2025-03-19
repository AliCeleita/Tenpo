# Tenpo Challenge

Este proyecto es una API REST desarrollada con **Spring Boot** que realiza cálculos matemáticos con un porcentaje adicional obtenido de un servicio externo. Por ejemplo, dado un número base, se le aplica un porcentaje obtenido externamente y se devuelve el resultado ajustado. Además, mantiene un historial de llamadas a la API.

## Tecnologías utilizadas

- **Java 21**: Lenguaje de programación utilizado para el desarrollo del backend.
- **Spring Boot**: Framework que facilita la configuración y ejecución de aplicaciones Java.
- **Spring Web**: Construcción de APIs REST.
- **Spring Data JPA**: Manejo de bases de datos con repositorios.
- **PostgreSQL**: Base de datos relacional.
- **Docker y Docker Compose**: Administración del entorno.
- **Swagger**: Documentación de la API.
- **JUnit 5 y Mockito**: Pruebas unitarias.

## Instalación y ejecución

### Prerrequisitos

Asegúrate de tener instalado:

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/) y [Docker Compose](https://docs.docker.com/compose/)

### Pasos para ejecutar el proyecto

#### 1. Clonar el repositorio

```sh
git clone <URL_DEL_REPOSITORIO>
cd tenpo
```

#### 2. Construir la aplicación

```sh
mvn clean install
```

#### 3. Levantar el entorno con Docker

```sh
docker-compose up -d --build
```

Esto iniciará la base de datos PostgreSQL, la aplicación y otros servicios necesarios. Para verificar que todos los servicios están corriendo correctamente, puedes usar los siguientes comandos:

```sh
docker ps
```

También puedes verificar los logs con:

```sh
docker-compose logs -f
```

### Acceder a Adminer

Adminer es una herramienta web para gestionar la base de datos PostgreSQL. Puedes acceder a través de:

[http://localhost:9090](http://localhost:9090)

Usa las siguientes credenciales:

- **Servidor:** postgres_db
- **Usuario:** tenpo
- **Base de datos:** ptTenpo

### Verificar si Redis está corriendo

Para comprobar si Redis está en ejecución, puedes ejecutar:

```sh
docker-compose exec redis redis-cli ping
```

Si Redis está funcionando correctamente, deberías recibir la respuesta:

```sh
PONG
```

## Endpoints principales

| Método | Endpoint     | Descripción                       |
|--------|------------|---------------------------------|
| `POST` | `/calculate` | Realiza un cálculo con porcentaje |
| `GET`  | `/history`   | Obtiene el historial de llamadas  |

## Pruebas unitarias

Ejecuta las pruebas con el siguiente comando:

```sh
mvn test
```

Las pruebas cubren:

- `CalculateServiceImpl`: Verifica el cálculo con porcentaje.
- `ExternalServiceImpl`: Simula respuestas del servicio externo.
- Manejo de excepciones y validaciones.