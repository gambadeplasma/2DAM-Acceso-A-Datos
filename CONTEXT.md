## Descripción

Repositorio de Acceso A Datos con todos sus ejercicios y proyectos.

## Estructura de carpetas
```text
│
├── RA1-EjerciciosBinarios/
│   └── ficherosBinarios/
│
├── RA1-Examen/
│   └── Examen_RA1_Proyecto_Alumno/
│
├── RA1-Gasolinera/
│   ├── datos/
│   │   ├── clientes.csv
│   │   ├── clientes.json
│   │   ├── pagos.csv
│   │   └── pagos.json
│   │
│   ├── src/
│   │   ├── Cliente.java
│   │   ├── Combustible.java
│   │   ├── ComparadorIDCliente.java
│   │   ├── ComparadorIDPagos.java
│   │   ├── GestorArchivoCSV.java
│   │   ├── GestorArchivoJSON.java
│   │   ├── GestorArchivos.java
│   │   ├── GestorCliente.java
│   │   ├── GestorPago.java
│   │   ├── GestorTexto.java
│   │   ├── Main.java
│   │   ├── Menu.java
│   │   ├── MigraCSVToJson.java
│   │   └── Pago.java
│   │
│   ├── .gitignore
│   ├── CAMBIOS.md
│   ├── Gasolinera.iml
│   └── README.md
│
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
│
├── RA2-JDBC/
│   ├── gradle/
│   │   └── wrapper/
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   │
│   ├── sql/
│   │   ├── mysql/
│   │   │   ├── 01-esquema.sql
│   │   │   └── 02-datos.sql
│   │   │
│   │   └── sqlite/
│   │       ├── 01-esquema.sql
│   │       └── 02-datos.sql
│   │
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── es/
│   │   │           └── iesluisbraille/
│   │   │               └── accesodatos/
│   │   │                   └── jardin/
│   │   │                       ├── bloque02/
│   │   │                       │   ├── ErrorDeConexion.java
│   │   │                       │   ├── MetadatosSqlite.java
│   │   │                       │   ├── PrimeraConexionMysql.java
│   │   │                       │   └── PrimeraConexionSqlite.java
│   │   │                       │
│   │   │                       └── bloque03/
│   │   │                           ├── CrearTabla.java
│   │   │                           ├── ErrorSql.java
│   │   │                           └── VariasSentencias.java
│   │   │
│   │   └── test/
│   │       └── java/
│   │           └── es/
│   │               └── iesluisbraille/
│   │                   └── accesodatos/
│   │                       └── jardin/
│   │                           └── bloque02/
│   │                               └── ConexionSqliteTest.java
│   │
│   ├── tools/
│   │   ├── generar_datos.py
│   │   ├── preparar_sqlite.py
│   │   └── validar_sqlite.py
│   │
│   ├── .gitignore
│   ├── botanico.sqlite
│   ├── build.gradle.kts
│   ├── gradlew
│   ├── gradlew.bat
│   ├── README.md
│   └── settings.gradle.kts
│
├── AGENTS.md
└── CONTEXT.md
```

## Convenciones del repositorio

- Codificación: UTF-8
- Versión java: Java 21