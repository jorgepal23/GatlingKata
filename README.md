# Test de carga con Gatling

**Comandos:**

- Para ejecutar los test: mvn gatling:test y seleccionar la opcion de la simulacion a escoger: 
  - 0 - DELETE, 1 - GET, 2 - POST, 3 - PUT
- Para instalar dependencias: mvn install
- Para limpiar el proyecto: mvn clean

**Escenarios de ejecucion:**

```
atOnceUsers(15): Esto indica que 15 usuarios virtuales deben comenzar a ejecutar el escenario de inmediato (es decir, todos a la vez).
Esta configuración es útil para simular una carga de usuarios que realizan la misma acción simultáneamente.

```
