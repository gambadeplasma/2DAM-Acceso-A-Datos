## Premisa

Esta versión únicamente trabaja con JDBC. Su objetivo es conocer y aprender sobre el uso del Driver JDBC.

## Estructura del proyecto

```text
├── RA2-GasolineraSQLite/
│   ├── datos/
│   │   ├── clientes.csv
│   │   ├── clientes.json
│   │   ├── pagos.csv
│   │   └── pagos.json
│   │
│   ├── lib/
│   │   └── sqlite-jdbc-3.54.1.0.jar
│   │
│   ├── src/
│   │   ├── Cliente.java
│   │   ├── Combustible.java
│   │   ├── ComparadorIDCliente.java
│   │   ├── ComparadorIDPagos.java
│   │   ├── ConexionSQLite.java
│   │   ├── GestorArchivoCSV.java
│   │   ├── GestorArchivoJSON.java
│   │   ├── GestorArchivos.java
│   │   ├── GestorCliente.java
│   │   ├── GestorConexion.java
│   │   ├── GestorPago.java
│   │   ├── GestorSQLite.java
│   │   ├── GestorTexto.java
│   │   ├── Main.java
│   │   ├── Menu.java
│   │   ├── MigraCSVToJson.java
│   │   └── Pago.java
│   │
│   ├── .gitignore
│   ├── CONTEXT.md
│   ├── Gasolinera.iml
│   └── gasolinera.sqlite
```

## Convenciones del repositorio

* Java 21
* Uso **exclusivo** de Driver JDBC para gestión de datos
* Uso de clases Gestor para administrar diversos aspectos (GestorConexion, GestorTexto, GestorCliente, GestorPago, etc)


