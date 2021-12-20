# monitoring-api

Basic Temperature Monitoring API with 2 basic endpoints:
```
GET http://localhost:8080/monitoring-api/data
```
Fetch list of all temperatures
```
POST http://localhost:8080/monitoring-api/data?temperature=17
```
Creates new record in database

GraphQL playground with query and mutation docs:
```
http://localhost:8080/graphiql
```
Supports GraphQL and Docker

Database: PostgreSQL
