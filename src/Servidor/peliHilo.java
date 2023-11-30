package Servidor;

import java.net.Socket;

public class peliHilo extends Thread {

	private Socket socketAlCliente;

	public peliHilo(Socket socketAlCliente) {
		this.socketAlCliente = socketAlCliente;
	}
	



}
