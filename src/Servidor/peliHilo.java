package Servidor;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class peliHilo extends Thread {
    public Socket socketAlCliente;
    private List<Peliculas> pelis;

    public peliHilo(Socket socketAlCliente, List<Peliculas> pelis) {
        this.socketAlCliente = socketAlCliente;
        this.pelis = pelis;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketAlCliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socketAlCliente.getInputStream());

            while (true) {
                int op = ois.readInt();
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
        oos.flush();
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
        oos.flush();
    }

    private Peliculas buscarPeliculaPorTitulo(String titulo) {
        for (Peliculas pelicula : pelis) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula;
            }
        }
        return null;
    }

    private void consultarDirector(ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
        String director = ois.readUTF();
        List<Peliculas> peliculas = buscarDirector(director);
        oos.writeInt(peliculas.size());
        oos.flush();
        for (Peliculas pelicula : peliculas) {
            oos.writeObject(pelicula);
            oos.flush();
        }
    }

    private List<Peliculas> buscarDirector(String director) {
        List<Peliculas> peliculasEncontradas = new ArrayList<>();
        for (Peliculas pelicula : pelis) {
            if (pelicula.getDirector().equalsIgnoreCase(director)) {
                peliculasEncontradas.add(pelicula);
            }
        }
        return peliculasEncontradas;
    }

    private void agregarPeli(ObjectOutputStream oos, ObjectInputStream ois) {
        try {
            Peliculas nuevaPelicula = (Peliculas) ois.readObject();
            boolean operacionExitosa = agregarPeliculaALaLista(nuevaPelicula);
            oos.writeBoolean(operacionExitosa);
            oos.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean agregarPeliculaALaLista(Peliculas nuevaPelicula) {
        synchronized (pelis) {
            return pelis.add(nuevaPelicula);
        }
    }
}
