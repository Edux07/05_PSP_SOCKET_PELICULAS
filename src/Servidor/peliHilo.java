package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class peliHilo extends Thread {
	public Socket socketAlCliente;
	private List<Peliculas> pelis;

	public peliHilo(Socket socketAlCliente) {
		this.socketAlCliente = socketAlCliente;
		this.pelis = pelis;

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
		List<Peliculas> pelis = buscarDirector(director);
		oos.writeObject(pelis);

	}

	private List<Peliculas> buscarDirector(String director) {
		// TODO Auto-generated method stub
		return null;
	}

	private void agregarPeli(ObjectOutputStream oos, ObjectInputStream ois) {
		// TODO Auto-generated method stub

	}

}