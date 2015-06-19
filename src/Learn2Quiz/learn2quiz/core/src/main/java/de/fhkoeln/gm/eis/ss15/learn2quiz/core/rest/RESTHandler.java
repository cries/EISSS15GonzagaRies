package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest;

import java.io.IOException;
import java.net.Socket;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.*;

public class RESTHandler {
	private String hostname;
	private int port;
	private WebTarget target;

	public RESTHandler(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public boolean getConnection() {

		if (this.hostname == null || this.port <= 0)
			return false;

		this.target = ClientBuilder.newClient().target("http://" + this.hostname + ":" + this.port);

		Socket sock = null;
		try {
			sock = new Socket(hostname, port);
			return true;
		} catch (IOException e) {
		} finally {
			if (sock != null) {
				try {
					sock.close();
				} catch (IOException e) {
				}
			}
		}

		return false;
	}
}
