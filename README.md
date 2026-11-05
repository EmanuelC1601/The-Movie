# Cartelera de películas

Aplicación web para consultar películas populares, buscar películas por título y ver sus detalles. Los datos se obtienen de TMDB a través de un backend con Spring Boot.

## Tecnologías

- Angular 21
- Java 17
- Spring Boot 4.1.2
- Bootstrap 5
- API de The Movie Database (TMDB)
- Git y GitHub

## Funcionalidades

- Listado de películas populares.
- Búsqueda de películas por título.
- Detalles con sinopsis, géneros, fecha de estreno, calificación y duración cuando está disponible.
- Paginación.
- Indicadores de carga y mensajes de error o de resultados vacíos.
- Diseño adaptable a escritorio, tablet y móvil.
- Imagen alternativa cuando una película no tiene póster.

## Requisitos previos

- Node.js 22.
- npm.
- Java 17.
- Conexión a Internet.
- Token de acceso de lectura de TMDB.

## Configurar el token de TMDB

El token se configura como una variable de entorno del sistema. No se debe pegar en el código ni subirlo a GitHub.

En Windows:

1. Busca en Inicio **Editar las variables de entorno de tu cuenta**.
2. En **Variables de usuario**, selecciona **Nueva**.
3. Usa `TMDB_ACCESS_TOKEN` como nombre.
4. Pega el **Token de acceso de lectura a la API** de TMDB como valor.
5. Guarda los cambios y reinicia VS Code para que sus terminales detecten la variable.

El backend lee esta variable desde `backend/src/main/resources/application.properties`:

```properties
tmdb.access-token=${TMDB_ACCESS_TOKEN}
```

No se debe usar aquí la llave API V3. El token de lectura se envía desde el backend como Bearer y no se expone en Angular.

## Instalación y ejecución

Clona el repositorio:

```powershell
git clone https://github.com/EmanuelC1601/The-Movie.git
Set-Location '.\The-Movie'
```

Abre dos terminales ubicadas en la raíz del repositorio.

### Iniciar el backend

En la primera terminal:

```powershell
Set-Location '.\backend'
.\mvnw.cmd spring-boot:run
```

El backend se ejecuta en `http://localhost:8080`. Mantén abierta esta terminal.

### Iniciar el frontend

En la segunda terminal:

```powershell
Set-Location '.\frontend'
npm ci
npm start
```

Abre `http://localhost:4200` en el navegador.

## Endpoints del backend

- `GET /api/movies/popular?page=1` — películas populares.
- `GET /api/movies/search?query=batman&page=1` — búsqueda por título.
- `GET /api/movies/{movieId}` — detalles de una película.

## Atribución

Los datos e imágenes de películas son proporcionados por TMDB. Esta aplicación no está respaldada ni certificada por TMDB.

## Autor

EMANUEL