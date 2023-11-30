package Servidor;

import java.net.Socket;

public class peliHilo extends Thread {

	public Socket socketAlCliente;

	public peliHilo(Socket socketAlCliente) {
		this.socketAlCliente = socketAlCliente;
	}
	



}
