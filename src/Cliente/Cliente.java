package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static final int PUERTO = 2180;
    public static final String IP_SERVER = "localhost";

    public static void main(String[] args) throws SocketException {
        System.out.println("--------Peliculario--------");

        InetSocketAddress direccionServer = new InetSocketAddress(IP_SERVER, PUERTO);

        try (Socket socketServer = new Socket(); Scanner sc = new Scanner(System.in)) {
            socketServer.connect(direccionServer);

            PrintWriter salida = new PrintWriter(socketServer.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketServer.getInputStream(), "UTF-8"));

            while (true) {
                mostrarMenu();
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1: {
                        consultarId(salida, entrada, sc);
                        break;
                    }

                    case 2: {
                        consultarTitulo(salida, entrada, sc);
                        break;
                    }

                    case 3: {
                        consultarDirector(salida, entrada, sc);
                        break;
                    }

                    case 4: {
                        agregarPelicula(salida, entrada, sc);
                        break;
                    }

                    case 5: {
                        salir();
                        return;
                    }
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No se puede conectar al servidor en la dirección " + IP_SERVER);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error de entrada/salida");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error -> " + e);
            e.printStackTrace();
        }
    }

    protected static void mostrarMenu() {
        System.out.println("\nMenu de peliculas:");
        System.out.println("1. Consultar pelicula por ID");
        System.out.println("2. Consultar pelicula por titulo");
        System.out.println("3. Consultar peliculas por director");
        System.out.println("4. Agregar pelicula");
        System.out.println("5. Salir de la aplicacion");
        System.out.print("Ingrese el numero de la opcion deseada: ");
    }

    private static void consultarId(PrintWriter salida, BufferedReader entrada, Scanner sc) throws IOException {
        System.out.print("Ingrese el ID de la película: ");
        int id = sc.nextInt();

        salida.println(1); // Indicar al servidor que se realizará una consulta por ID
        salida.println(id);

        String respuesta = entrada.readLine();
        if (!respuesta.isEmpty()) {
            System.out.println("Película encontrada:\n" + respuesta);
        } else {
            System.out.println("No se encontró ninguna película con el ID especificado.");
        }
    }

    private static void consultarTitulo(PrintWriter salida, BufferedReader entrada, Scanner sc) throws IOException {
        System.out.print("Ingrese el título de la película: ");
        String titulo = sc.next();

        salida.println(2); // Indicar al servidor que se realizará una consulta por título
        salida.println(titulo);

        String respuesta = entrada.readLine();
        if (!respuesta.isEmpty()) {
            System.out.println("Película encontrada:\n" + respuesta);
        } else {
            System.out.println("No se encontró ninguna película con el título especificado.");
        }
    }

    private static void consultarDirector(PrintWriter salida, BufferedReader entrada, Scanner sc) throws IOException {
        System.out.print("Ingrese el director de la película: ");
        String director = sc.next();

        salida.println(3); // Indicar al servidor que se realizará una consulta por director
        salida.println(director);

        int numPeliculas = Integer.parseInt(entrada.readLine());
        if (numPeliculas > 0) {
            System.out.println("Películas encontradas por el director " + director + ":");
            for (int i = 0; i < numPeliculas; i++) {
                String pelicula = entrada.readLine();
                System.out.println(pelicula);
            }
        } else {
            System.out.println("No se encontraron películas para el director especificado.");
        }
    }

    private static void agregarPelicula(PrintWriter salida, BufferedReader entrada, Scanner sc) throws IOException {
        System.out.print("Ingrese el ID de la nueva película: ");
        int id = sc.nextInt();
        System.out.print("Ingrese el título de la nueva película: ");
        String titulo = sc.next();
        System.out.print("Ingrese el director de la nueva película: ");
        String director = sc.next();

        salida.println(4); // Indicar al servidor que se agregará una nueva película
        salida.println(id + "," + titulo + "," + director );

        boolean operacionExitosa = Boolean.parseBoolean(entrada.readLine());
        if (operacionExitosa) {
            System.out.println("Película agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la película.");
        }
    }

    private static void salir() {
        System.out.println("Saliendo de la aplicación.");
    }
}
