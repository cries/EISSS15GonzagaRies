package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;

import javax.management.RuntimeErrorException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.glassfish.jersey.client.*;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbluser;
 

public class RESTHandler {
	private String hostname;
	private int port;
	private WebTarget target;
	private Client client;
	private String baseURI;

	public RESTHandler(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		this.baseURI = "http://" + this.hostname + ":" + this.port + "/restservice/rest";
	}

	public boolean getConnection() {
		
		ClientConfig config = new ClientConfig(); 
		if (this.hostname == null || this.port <= 0)
			return false;

		this.target = ClientBuilder.newClient(config).target("http://" + this.hostname + ":" + this.port);

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
	
		
	public Tbluser getUser(String userId) {
		Tbluser myObj = null;
			try {
				String uri = baseURI + "/user/" + userId;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");
				 
					JAXBContext jc = JAXBContext.newInstance(Tbluser.class);
					InputStream xml = connection.getInputStream();
					
					// Get object from InputStream
					if (connection.getResponseCode() == 200)  {
						myObj = (Tbluser) jc.createUnmarshaller().unmarshal(xml);
					}
					connection.disconnect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return myObj;
	}
	
	public int addUser(Tbluser myUser) {
		int myStatusCode = -1;
		try { 
			String uri = baseURI + "/user";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tbluser.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myUser, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
	
	public int updateUser(Tbluser myUser) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/user";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("PUT");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tbluser.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myUser, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
}