# 2DAM-Acceso-A-Datos

JDK 21
Utilizando IntelliJ IDEA, ejecutar el Main.java.
Los ficheros se guardan en la carpeta /datos, en formato .csv

He optado por hacer 4 clases gestor: un gestor para los objetos cliente, uno para los objetos pago, uno para texto y uno para los ficheros .csv
Los gestores de cliente y pago llaman al gestor de texto para pedir los datos por consola y verificar que coinciden el tipo que se pide con el introducido.
La clase menú (menu.java) llama a los métodos de los gestores de cliente, pago y ficheros .csv, simplificando así la carga del main (solo hace falta crear un objeto menu en main y llamar al método que inicia el programa).
También he creado un Enumerado para clasificar los tipos de combustible.

Si se desease cambiar el tipo de fichero, por ejemplo a .json, solo habría que adaptar los métodos de la clase de gestión de archivos o crear una nueva clase de gestión de ficheros .json implementando la interfaz.
