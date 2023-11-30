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
				System.out.println("\nMenú:");
				System.out.print("Ingrese el número de la opción deseada: ");
				System.out.println("1. Consultar película por ID");
				System.out.println("2. Consultar película por título");
				System.out.println("3. Consultar películas por director");
				System.out.println("4. Agregar película");
				System.out.println("5. Salir de la aplicación");
				
				String opcion = sc.nextLine();

				PrintStream salida = new PrintStream(socketServer.getOutputStream());
				if(opcion.equals("5")) {
					System.out.println("La aplicación se cierra");
					salida.println("5");
					System.exit(0);
				}
				InputStreamReader entrada = new InputStreamReader(socketServer.getInputStream());
				BufferedReader bf = new BufferedReader(entrada);

				socketServer.close();
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