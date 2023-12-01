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
	
	protected static List<Peliculas> pelis = new ArrayList<>();

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
	
	
	public static class peliHilo extends Thread {

		public Socket socketAlCliente;
		Scanner sc = new Scanner(System.in);
		
		public peliHilo(Socket socketAlCliente) {
			this.socketAlCliente = socketAlCliente;
		
		}

		@Override
		public void run() {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socketAlCliente.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socketAlCliente.getInputStream());

				while (true) {
					int op = sc.nextInt();
					switch (op) {
					case 1: {
						consultarID(oos, ois);
						break;
					}
					case 2: {
						consultarTitulo(oos, ois);
						break;

					}
					case 3: {
						consultarDirector(oos, ois);
						break;
					}
					case 4: {
						agregarPeli(oos, ois);
						break;

					}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void consultarID(ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
			int id = ois.readInt();
			Peliculas peli = buscarID(id);
			oos.writeObject(peli);
			oos.close();

		}
		 private Peliculas buscarID(int id) {
	         for (Peliculas pelicula : pelis) {
	             if (pelicula.getID() == id) {
	                 return pelicula;
	             }
	         }
	         return null;
	     }

		private void consultarTitulo(ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
			String titulo = ois.readUTF();
			Peliculas pelicula = buscarPeliculaPorTitulo(titulo);
			oos.writeObject(pelicula);
			oos.close();

		}

		private Peliculas buscarPeliculaPorTitulo(String titulo) {
			// TODO Auto-generated method stub
			return null;
		}

		private void consultarDirector(ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
			String director = ois.readUTF();
			List<Peliculas>peli= buscarDirector(director);
			oos.writeObject(peli);
			

		}

		private List<Peliculas> buscarDirector(String director) {
			// TODO Auto-generated method stub
			return null;
		}

		private void agregarPeli(ObjectOutputStream oos, ObjectInputStream ois) {
			// TODO Auto-generated method stub

		}

}
}
