package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

			PrintStream salida = new PrintStream(socketServer.getOutputStream());

			InputStreamReader entrada = new InputStreamReader(socketServer.getInputStream());
			BufferedReader bf = new BufferedReader(entrada);

			while (true) {

				mostrarMenu();
				int opcion = sc.nextInt();

				switch (opcion) {
				case 1: {
					consultarId(salida, entrada, bf);
					break;
				}

				case 2: {
					consultarTitulo(salida, entrada, bf);
					break;
				}

				case 3: {
					consultarDirector(salida, entrada, bf);
					break;
				}

				case 4: {
					agregarPelicula(salida, entrada, bf);
					break;
				}

				case 5: {
					System.out.println("Saliendo de la aplicación.");
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
		System.out.println("\nMenú:");
		System.out.println("1. Consultar película por ID");
		System.out.println("2. Consultar película por título");
		System.out.println("3. Consultar películas por director");
		System.out.println("4. Agregar película");
		System.out.println("5. Salir de la aplicación");
		System.out.print("Ingrese el número de la opción deseada: ");

	}

	private static void agregarPelicula(PrintStream salida, InputStreamReader entrada, BufferedReader bf) {
		// TODO Auto-generated method stub

	}

	private static void consultarDirector(PrintStream salida, InputStreamReader entrada, BufferedReader bf) {
		// TODO Auto-generated method stub

	}

	private static void consultarTitulo(PrintStream salida, InputStreamReader entrada, BufferedReader bf) {
		// TODO Auto-generated method stub

	}

	private static void consultarId(PrintStream salida, InputStreamReader entrada, BufferedReader bf) {
		// TODO Auto-generated method stub

	}

}