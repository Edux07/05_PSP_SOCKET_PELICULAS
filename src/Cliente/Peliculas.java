package Cliente;

public class Peliculas {
	public int ID;
	public String titulo;
	public String director;
	public String precio;

	public Peliculas(int iD, String titulo, String director, String precio) {

		ID = iD;
		this.titulo = titulo;
		this.director = director;
		this.precio = precio;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Peliculas [ID=" + ID + ", titulo=" + titulo + ", director=" + director + ", precio=" + precio + "]";
	}

}
