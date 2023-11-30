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
		System.out.println("--------hhññ--------");

		InetSocketAddress direccionServer = new InetSocketAddress(IP_SERVER, PUERTO);

		try (Socket socketServer = new Socket(); Scanner sc = new Scanner(System.in)) {
			socketServer.connect(direccionServer);
			while (true) {
				System.out.println("\nMenu:");
				System.out.println("Ingrese el numero de la opcion deseada: ");
				System.out.println("1. Consultar pelicula por ID");
				System.out.println("2. Consultar pelicula por titulo");
				System.out.println("3. Consultar peliculas por director");
				System.out.println("4. Agregar pelicula");
				System.out.println("5. Salir de la aplicacion");

				String opcion = sc.nextLine();

				PrintStream salida = new PrintStream(socketServer.getOutputStream());
				if (opcion.equals("5")) {
					System.out.println("La aplicación se cierra");
					salida.println("5");
					System.exit(0);
				}
				if (opcion.equals("1")) {
					System.out.println("Ha elegido la opcion 1");
					String op1= " ";
					salida.println(op1);
				} else if (opcion.equals("2")) {
					System.out.println("Ha elegido la opcion 2");
					String op2= " ";
					salida.println(op2);
				} else if (opcion.equals("3")) {
					System.out.println("Ha elegido la opcion 3");
					String op3= " ";
					salida.println(op3);
				} else if (opcion.equals("4")) {
					System.out.println("Ha elegido la opcion 4");
					String op4= " ";
					salida.println(op4);
				}
				InputStreamReader entrada = new InputStreamReader(socketServer.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);

				System.out.println("Esperando al resultado del servidor...");
				String resultado = bf.readLine();

				System.out.println("CLIENTE: El resultado de la operacion es:" + resultado);
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
}