package Servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	public static final int PUERTO = 2180;
	
	protected List<Peliculas> pelis= new ArrayList<>();
	public static void main(String[] args) {
		System.out.println("-------- Servidor  --------");
	

		try (ServerSocket server = new ServerSocket(PUERTO)) {
			System.out.println("Esperando conexiones en el puerto " + PUERTO);

			while (true) {
				Socket socketAlCliente = server.accept();
				System.out.println("Nuevo cliente conectado.");

				new peliHilo(socketAlCliente).start();

			}

		} catch (Exception e) {
			System.err.println("Error en el servidor -> " + e);
			e.printStackTrace();
		}
	}
}
