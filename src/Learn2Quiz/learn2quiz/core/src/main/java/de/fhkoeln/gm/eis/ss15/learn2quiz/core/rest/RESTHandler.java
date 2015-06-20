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

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbleinladung;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblgruppe;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblkarteikarte;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblkartenset;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblkommentar;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbluser;
 

public class RESTHandler {
	private String hostname;
	private int port;
	private WebTarget target;
	private Client client;
	private String baseURI;
	private Socket sock;

	public RESTHandler(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
		this.baseURI = "http://" + this.hostname + ":" + this.port + "/restservice/rest";
	}

//	public boolean getConnection() {
//		
//		ClientConfig config = new ClientConfig(); 
//		if (this.hostname == null || this.port <= 0)
//			return false;
//
//		this.target = ClientBuilder.newClient(config).target("http://" + this.hostname + ":" + this.port);
//
//		sock = null;
//		try {
//			sock = new Socket(hostname, port);
//			return true;
//		} catch (IOException e) {
//		} finally {
//			if (sock != null) {
//				try {
//					sock.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//
//		return false;
//	}
	
	public void closeSocket() {
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> USER RESOURCE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	/**
	 * Returns an User Object of Tbluser for a given userId
	 *
	 * @param  userId  The users unique ID
	 * @return      User Object
	 */
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
	
	/**
	 * Creates a new user from myUser
	 *
	 * @param  myUser  The new user object
	 * @return      HTTPStatusCode (201, 404...)
	 */
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
	
	/**
	 * Updates a specific user (userId in myUser object)
	 *
	 * @param  myUser  The updated user object
	 * @return      HTTPStatusCode (201, 404...)
	 */
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
	
	/**
	 * Deletes a specific user by given userId
	 *
	 * @param  userId  The users unique ID
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int deleteUser(String userId) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/user/" + userId;
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("DELETE");
	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
	
	
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> KARTEIKARTE RESOURCE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	
	public Tblkarteikarte getKarteikarte(String karteikarteId) {
		Tblkarteikarte myObj = null;
			try {
				String uri = baseURI + "/card/" + karteikarteId;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");
				 
					JAXBContext jc = JAXBContext.newInstance(Tblkarteikarte.class);
					InputStream xml = connection.getInputStream();
					if (connection.getResponseCode() == 200)  {
						myObj = (Tblkarteikarte) jc.createUnmarshaller().unmarshal(xml);
					}
					connection.disconnect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return myObj;
	}

	/**
	 * Creates a new card from myCard
	 *
	 * @param  myCard  The new card object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int addKarteikarte(Tblkarteikarte myCard) {
		int myStatusCode = -1;
		try { 
			String uri = baseURI + "/card";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tblkarteikarte.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myCard, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
	
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> KARTENSET RESOURCE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public Tblkartenset getKartenset(String kartensetId) {
		Tblkartenset myObj = null;
			try {
				String uri = baseURI + "/cardset/" + kartensetId;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");
				 
					JAXBContext jc = JAXBContext.newInstance(Tblkartenset.class);
					InputStream xml = connection.getInputStream();
					if (connection.getResponseCode() == 200)  {
						myObj = (Tblkartenset) jc.createUnmarshaller().unmarshal(xml);
					}
					connection.disconnect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return myObj;
	}
	
	/**
	 * Creates a new cardset from myCardset
	 *
	 * @param  myCardset  The new cardset object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int addKartenset(Tblkartenset myCardset) {
		int myStatusCode = -1;
		try { 
			String uri = baseURI + "/cardset";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tblkartenset.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myCardset, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> KOMMENTAR RESOURCE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public Tblkommentar getKommentar(String kommentarId) {
		Tblkommentar myObj = null;
			try {
				String uri = baseURI + "/comment/" + kommentarId;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");
				 
					JAXBContext jc = JAXBContext.newInstance(Tblkommentar.class);
					InputStream xml = connection.getInputStream();
					if (connection.getResponseCode() == 200)  {
						myObj = (Tblkommentar) jc.createUnmarshaller().unmarshal(xml);
					}
					connection.disconnect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return myObj;
	}
	
	/**
	 * Creates a new comment from myComment
	 *
	 * @param  myComment  The new comment object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int addKommentar(Tblkommentar myComment) {
		int myStatusCode = -1;
		try { 
			String uri = baseURI + "/comment";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tblkommentar.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myComment, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GRUPPE RESOURCE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public Tblgruppe getGruppe(String gruppeId) {
		Tblgruppe myObj = null;
			try {
				String uri = baseURI + "/group/" + gruppeId;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");
				 
					JAXBContext jc = JAXBContext.newInstance(Tblgruppe.class);
					InputStream xml = connection.getInputStream();
					if (connection.getResponseCode() == 200)  {
						myObj = (Tblgruppe) jc.createUnmarshaller().unmarshal(xml);
					}
					connection.disconnect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return myObj;
	}

	/**
	 * Creates a new group from myGroup
	 *
	 * @param  myGroup  The new group object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int addGruppe(Tblgruppe myGroup) {
		int myStatusCode = -1;
		try { 
			String uri = baseURI + "/group";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tblgruppe.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myGroup, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> EINLADUNG RESOURCE <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	public Tbleinladung getEinladung(String einladungId) {
		Tbleinladung myObj = null;
			try {
				String uri = baseURI + "/invite/" + einladungId;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/xml");
				 
					JAXBContext jc = JAXBContext.newInstance(Tbleinladung.class);
					InputStream xml = connection.getInputStream();
					if (connection.getResponseCode() == 200)  {
						myObj = (Tbleinladung) jc.createUnmarshaller().unmarshal(xml);
					}
					connection.disconnect();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return myObj;
	}
	
	/**
	 * Creates a new invite from myInvite
	 *
	 * @param  myInvite  The new invite object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int addEinladung(Tbleinladung myInvite) {
		int myStatusCode = -1;
		try { 
			String uri = baseURI + "/invite";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/xml");
	        
	        JAXBContext jc = JAXBContext.newInstance(Tbleinladung.class);
	        OutputStream os = connection.getOutputStream();
	        
	        // Write object to OutputStream
	        jc.createMarshaller().marshal(myInvite, os);
	        os.flush();

	        myStatusCode = connection.getResponseCode();
	        connection.disconnect();
	    } catch(Exception e) {
	        throw new RuntimeException(e);
	    }
		return myStatusCode;
	}
}
