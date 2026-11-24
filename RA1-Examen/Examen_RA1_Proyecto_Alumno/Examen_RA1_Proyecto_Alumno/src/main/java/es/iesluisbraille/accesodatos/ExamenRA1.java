package es.iesluisbraille.accesodatos;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * RA1 - Acceso a Datos: examen práctico de gestión de ficheros.
 *
 * SOLO se completan los métodos marcados como TODO.
 * No se deben modificar Alumno, ParseadorAlumnos ni las excepciones entregadas.
 * No se evalúa el parseo de CSV ni la lectura de datos por consola.
 */
public class ExamenRA1 {
    private static final Path DIRECTORIO_ENTRADA = Path.of("datos", "entrada");
    private static final Path FICHERO_1 = DIRECTORIO_ENTRADA.resolve("alumnos1.csv");
    private static final Path FICHERO_2 = DIRECTORIO_ENTRADA.resolve("alumnos2.csv");
    private static final Path FICHERO_3 = DIRECTORIO_ENTRADA.resolve("alumnos3.csv");

    public static void main(String[] args) {
        System.out.println("=== EXAMEN RA1: GESTIÓN DE FICHEROS ===");
        System.out.println("Directorio de entrada: " + DIRECTORIO_ENTRADA.toAbsolutePath());

        // Entrada por consola ya implementada; no hay que modificarla.
        Path directorioSalida;
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.print("Introduce la ruta del directorio de salida: ");
            String ruta = teclado.nextLine().trim();
            if (ruta.isEmpty()) {
                System.err.println("La ruta de salida no puede estar vacía.");
                return;
            }

            directorioSalida = Path.of(ruta);
        }
        if (!prepararDirectorioSalida(directorioSalida)) {
            System.err.println("No se puede continuar sin un directorio de salida válido.");
            return;
        }
        System.out.println("[Flujo] Ejercicio 1 finalizado; comienza el procesamiento.");
        System.out.println("[Flujo] Ejercicio 2");
        Alumno alumno1 = ejercicio2LeerYEscribirAlumno(FICHERO_1, directorioSalida);
        System.out.println("[Flujo] Ejercicio 3");
        List<Alumno> alumnos2 = ejercicio3LeerAlumnos(FICHERO_2);
        System.out.println("[Flujo] Ejercicio 4");
        ejercicio4EscribirNumeroBinario(directorioSalida, alumnos2);
        System.out.println("[Flujo] Ejercicio 5");
        List<Alumno> alumnos3 = ejercicio5LeerAlumnosConErrores(FICHERO_3);
        System.out.println("[Flujo] Ejercicio 6");
        ejercicio6FusionarYGenerar(alumnos2, alumnos3, alumno1, directorioSalida);
        System.out.println("[Flujo] Fin de la ejecución.");
    }

    /** Ejercicio 1 (1 p.): comprobar/crear directorio, informar y detectar ruta no válida. */
    private static boolean prepararDirectorioSalida(Path directorio) {
        // TODO: implementar exclusivamente la gestión del directorio de salida.

        try {
            if (Files.exists(directorio)) {
                if (Files.isDirectory(directorio)) {
                    System.out.println("Existe un directorio válido en esta ruta.");
                    return true;
                }
                System.out.println("La ruta existe pero no es un directorio.");
                return false;
            }

            Files.createDirectories(directorio);
            System.out.println("Directorio de salida creado.");
            return true;

        } catch (IOException e) {
            System.out.println("No se ha podido acceder al directorio " + directorio + ": " + e.getMessage());
            return false;
        }
    }

    /** Ejercicio 2 (1 p.): leer el CSV de un alumno y escribir solo su nombre en alumno1.txt. */
    private static Alumno ejercicio2LeerYEscribirAlumno(Path entrada, Path salida) {
        // TODO: leer una línea, utilizar ParseadorAlumnos.parsear(linea),
        // obtener alumno.getNombre() y crear alumno1.txt con un salto de línea.
        // Devuelve el Alumno leído para poder utilizarlo en el ejercicio 6.

        Alumno alumno;

        try (BufferedReader lector = Files.newBufferedReader(entrada, StandardCharsets.UTF_8)) {

            String linea = lector.readLine();
            if (linea == null) {
                System.out.println("El fichero está vacío.");
                return null;
            }

            alumno = ParseadorAlumnos.parsear(linea);

        } catch (TipoDatoIncorrectoException | NumeroCamposIncorrectoException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("No se ha podido acceder al fichero " + entrada + ": " + e.getMessage());
            return null;
        }

        Path ficheroSalida = salida.resolve(FICHERO_1);

        try {
        Files.writeString(ficheroSalida, alumno.getNombre() + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("No se ha podido acceder al fichero " + salida + ": " + e.getMessage());
        }

        return alumno;
    }

    /** Ejercicio 3 (2 p.): procesar 200 líneas y construir una lista. */
    private static List<Alumno> ejercicio3LeerAlumnos(Path entrada) {
        List<Alumno> alumnos = new ArrayList<>();
        // TODO: leer alumnos2.csv, gestionar errores y cerrar los recursos.
        // Añadir los alumnos a la lista y mostrar cuántos se han incorporado.

        try (BufferedReader lector = Files.newBufferedReader(entrada)) {

            String linea;
            int numLinea = 0;

            while ((linea = lector.readLine()) != null) {
                numLinea++;

                try {

                    Alumno alumno = ParseadorAlumnos.parsear(linea);
                    if (alumno == null) {
                        System.out.println("Valor/es inválido/s en la línea/s: " + numLinea);
                    } else if (alumnos.contains(alumno)) {
                        System.out.println("El alumno de la línea " + numLinea + " ya existe en el sistema.");
                    }

                    alumnos.add(alumno);

                } catch (TipoDatoIncorrectoException | NumeroCamposIncorrectoException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No se ha podido acceder al fichero " + entrada + ": " + e.getMessage());
        }
        System.out.println("Se han añadido " + alumnos.size() + " alumnos.");
        return alumnos;
    }

    /** Ejercicio 4 (1 p.): guardar un int de 32 bits, no texto, en numero_alumnos.bin. */
    private static void ejercicio4EscribirNumeroBinario(Path salida, List<Alumno> alumnos2) {
        // TODO: escribir alumnos2.size() en un fichero binario de 32 bits.

        Path ficheroSalida = salida.resolve("numero_alumnos.bin");
        //Files.createFile(ficheroSalida);

        try (DataOutputStream escritor = new DataOutputStream(Files.newOutputStream(ficheroSalida, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE))) {

            escritor.writeInt(alumnos2.size());

        } catch (IOException e) {
            System.out.println("No se ha podido acceder al fichero " + salida + ": " + e.getMessage());
        }
    }

    /** Ejercicio 5 (2 p.): continuar tras errores y descartar IDs repetidos dentro del CSV 3. */
    private static List<Alumno> ejercicio5LeerAlumnosConErrores(Path entrada) {
        List<Alumno> alumnos = new ArrayList<>();
        // TODO: gestionar POR SEPARADO NumeroCamposIncorrectoException,
        // TipoDatoIncorrectoException y el caso parsear(...) == null.
        // Informar de cada línea descartada y de su causa. Detectar duplicados
        // por Alumno.getId() SOLO dentro del fichero 3.

        try (BufferedReader lector = Files.newBufferedReader(entrada, StandardCharsets.UTF_8)) {

            String linea;
            int numLinea = 0;

            while ((linea = lector.readLine()) != null) {

                numLinea++;

                try {
                    Alumno alumno = ParseadorAlumnos.parsear(linea);

                    if (alumno == null) {
                        System.out.println("Alumno con valores inválidos en la línea " + numLinea);
                    } else if(alumnos.contains(alumno)) {
                        System.out.println("El alumno con ID " + alumno.getId() +  " ya existe en el sistema.");
                    }

                    alumnos.add(alumno);

                } catch (TipoDatoIncorrectoException e) {
                    System.out.println("Tipo de dato incorrecto en el alumno de la línea " + numLinea + ": " + e.getMessage());
                } catch (NumeroCamposIncorrectoException e) {
                    System.out.println("Número de campos incorrecto en el alumno de la línea " + numLinea + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("No se ha podido acceder al fichero " + entrada + ": " + e.getMessage());
        }

        System.out.println("Se han añadido " + alumnos.size() + " alumnos.");
        return alumnos;
    }

    /** Ejercicio 6 (3 p.): fusionar, ordenar, CREATE_NEW y segunda escritura. */
    private static void ejercicio6FusionarYGenerar(
            List<Alumno> alumnos2, List<Alumno> alumnos3, Alumno alumno1, Path salida) {
        // TODO: combinar 2 y 3 (sin duplicados por ID) y ordenar con el
        // orden natural de Alumno (Comparable).
        // Primera escritura: alumnos_ordenados.csv con CREATE_NEW.
        // Si el fichero YA existía, NO tocarlo ni hacer la Parte B.
        // SOLO si se creó: incorporar alumno1 si no está repetido, volver
        // a ordenar y SOBREESCRIBIR/TRUNCAR el fichero con la colección completa.
        // Cada línea debe obtenerse con alumno.toCsv().

        List<Alumno> combinadas2y3 = new ArrayList<>();

        for (Alumno alumno : alumnos2) {

            if (!combinadas2y3.contains(alumno)) {
                combinadas2y3.add(alumno);
            }
        }

        for (Alumno alumno : alumnos3) {

            if (!combinadas2y3.contains(alumno)) {
                combinadas2y3.add(alumno);
            }
        }

        Path ficheroSalida =  salida.resolve("alumnos_ordenados.csv");
//        try {
//            Files.createFile(ficheroSalida);
//        } catch (IOException e) {
//            System.out.println("No se ha podido crear el fichero " + ficheroSalida + ": " + e.getMessage());
//        }


        try (BufferedWriter escritor = Files.newBufferedWriter(ficheroSalida, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {

            for (Alumno alumno : combinadas2y3) {

                if (alumno == null) {
                    continue;
                }

                escritor.write(alumno.toCsv());
                escritor.newLine();
            }

        } catch (FileAlreadyExistsException e) {

            System.out.println("El fichero ya existe: " + e.getMessage());
            return;

        } catch (IOException e) {

            System.out.println("No se ha podido acceder al fichero " + salida + ": " + e.getMessage());
            return;
        }

        System.out.println("Parte A completada.");

        combinadas2y3.add(alumno1);

        try (BufferedWriter escritor = Files.newBufferedWriter(ficheroSalida, StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

            for (Alumno alumno : combinadas2y3) {
                escritor.write(alumno.toCsv());
                escritor.newLine();
            }

            System.out.println("Fichero ordenado y actualizado con " + combinadas2y3.size() + " alumnos.");

        } catch (IOException e) {

            System.out.println("No se ha podido acceder al fichero " + ficheroSalida + ": " + e.getMessage());
        }

        System.out.println("Parte B completada.");
    }
}
