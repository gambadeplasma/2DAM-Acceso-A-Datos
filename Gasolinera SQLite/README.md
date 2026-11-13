# 2DAM-Acceso-A-Datos

Versión JDK 21 

Utilizando IntelliJ IDEA, ejecutar el Main.java.

Los ficheros se guardan en la carpeta /datos, en formato .json (se puede cambiar a .csv fácilmente)


Commit de la primera versión: f0a572a715ce19893737e47be977ea9c93d46d55


Se disponen 5 clases gestor: un gestor para los objetos cliente, uno para los objetos pago, uno para texto y una para cada tipo de fichero (.csv y .json)
Los gestores de cliente y pago llaman al gestor de texto para pedir los datos por consola y verificar que coinciden el tipo que se pide con el introducido.


La clase menú (menu.java) llama a los métodos de los gestores de cliente, pago y ficheros .json, simplificando así la carga del main (solo hace falta crear un objeto menu en main y llamar al método que inicia el programa).


Hay también una clase de migración de archivos .csv a .json, para conservar los datos.
