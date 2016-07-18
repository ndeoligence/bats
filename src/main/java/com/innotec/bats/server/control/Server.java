package com.innotec.bat.server.control;

import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		System.out.println("Server started.");
		System.out.println("Server Stopped.");
	}
	 class ClientHandler extends Thread {
	 	Socket socket;
	 	public ClientHandler(Socket socket) {
            this.socket = socket;
	 	}
	 	public void run() {

	 	}
	 }
}
