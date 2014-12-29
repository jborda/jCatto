package br.com.jmassucatto.filecontrol.client;

import br.com.jmassucatto.filecontrol.server.tcp.TCPServer;

public class Roda {

	public static void main(String[] args) throws Exception {
		TCPServer server = new TCPServer();
		Client client = new Client();

		server.start();
		client.sincroniza();
		server.para();
	}
}
