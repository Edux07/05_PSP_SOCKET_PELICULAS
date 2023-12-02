package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
	public static final int PUERTO = 2180;

	private static List<Peliculas> pelis = new ArrayList<>();

	public static void main(String[] args) {
		Biblioteca();
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

	protected static void Biblioteca() {
		pelis.add(new Peliculas(1, "Star Wars III- La venganza de los Sith", "George Lucas"));
		pelis.add(new Peliculas(2, "Kill Bill", "Quentin Tarantino"));
		pelis.add(new Peliculas(3, "Star Wars VI- El retorno del Jedi ", "George Lucas"));
		pelis.add(new Peliculas(4, "Tiburon", "Steven Spielberg"));
		pelis.add(new Peliculas(5, "Interestellar", "Christopher Nolan"));
	}

}
