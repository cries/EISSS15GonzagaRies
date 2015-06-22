package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.glassfish.jersey.client.*;

import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbleinladung;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblgruppe;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblkarteikarte;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblkartenset;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tblkommentar;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.Tbluser;
import de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities.ListWrapper;
 

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
	
	public List<Tbluser> getUsers(String idGruppe){
		List<Tbluser> myUsers = null;
		
		try {
			String uri = baseURI + "/group/" + idGruppe + "/users";
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
			 
				JAXBContext jc = JAXBContext.newInstance(ListWrapper.class, Tbluser.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				InputStream xml = connection.getInputStream();

				// Get object from InputStream
				if (connection.getResponseCode() == 200)  {
					myUsers = unmarshalList(unmarshaller, xml);
				}
				connection.disconnect();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return myUsers;
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
	
	/**
	 * Updates a specific card (cardId in myCard object)
	 *
	 * @param  myCard  The updated card object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int updateKarteikarte(Tblkarteikarte myCard) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/card";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("PUT");
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
	
	/**
	 * Deletes a specific card by given cardId
	 *
	 * @param  cardId  The cards unique ID
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int deleteKarteikarte(String cardId) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/card/" + cardId;
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
	
	public List<Tblkarteikarte> getKarteikarten(String idKartenset){
		List<Tblkarteikarte> myKarteikarten = null;
		
		try {
			String uri = baseURI + "/cardset/" + idKartenset + "/cards";
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
			 
				JAXBContext jc = JAXBContext.newInstance(ListWrapper.class, Tblkarteikarte.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				InputStream xml = connection.getInputStream();

				// Get object from InputStream
				if (connection.getResponseCode() == 200)  {
					myKarteikarten = unmarshalList(unmarshaller, xml);
				}
				connection.disconnect();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return myKarteikarten;
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
	
	/**
	 * Updates a specific cardset (cardsetId in myCardset object)
	 *
	 * @param  myCardset  The updated cardset object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int updateKartenset(Tblkartenset myCardset) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/cardset";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("PUT");
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
	
	/**
	 * Deletes a specific cardset by given cardsetId
	 *
	 * @param  cardsetId  The cardsets unique ID
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int deleteKartenset(String cardsetId) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/cardset/" + cardsetId;
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
	
	/**
	 * Updates a specific comment (commentId in myComment object)
	 *
	 * @param  myComment  The updated comment object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int updateKommentar(Tblkommentar myComment) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/comment";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("PUT");
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
	
	/**
	 * Deletes a specific comment by given commentId
	 *
	 * @param  commentId  The comments unique ID
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int deleteKommentar(String commentId) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/comment/" + commentId;
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
	
	

	public List<Tblkommentar> getKommentare(String idKarteikarte){
		List<Tblkommentar> myKommentare = null;
		
		try {
			String uri = baseURI + "/card/" + idKarteikarte + "/comments";
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
			 
				JAXBContext jc = JAXBContext.newInstance(ListWrapper.class, Tblkommentar.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				InputStream xml = connection.getInputStream();

				// Get object from InputStream
				if (connection.getResponseCode() == 200)  {
					myKommentare = unmarshalList(unmarshaller, xml);
				}
				connection.disconnect();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return myKommentare;
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
	
	/**
	 * Updates a specific group (groupId in myGroup object)
	 *
	 * @param  myGroup  The updated group object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int updateGruppe(Tblgruppe myGroup) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/group";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("PUT");
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
	
	/**
	 * Deletes a specific group by given groupId
	 *
	 * @param  groupId  The groups unique ID
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int deleteGruppe(String groupId) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/group/" + groupId;
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
	
	public List<Tblgruppe> getGruppen(String idUser){
		List<Tblgruppe> myGruppen = null;
		
		try {
			String uri = baseURI + "/user/" + idUser + "/groups";
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
			 
				JAXBContext jc = JAXBContext.newInstance(ListWrapper.class, Tblgruppe.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				InputStream xml = connection.getInputStream();

				// Get object from InputStream
				if (connection.getResponseCode() == 200)  {
					myGruppen = unmarshalList(unmarshaller, xml);
				}
				connection.disconnect();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return myGruppen;
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
	
	/**
	 * Updates a specific invite (inviteId in myInvite object)
	 *
	 * @param  myInvite  The updated invite object
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int updateEinladung(Tbleinladung myInvite) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/invite";
	        URL url = new URL(uri);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setInstanceFollowRedirects(false);
	        connection.setRequestMethod("PUT");
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
	
	/**
	 * Deletes a specific invite by given inviteId
	 *
	 * @param  inviteId  The invites unique ID
	 * @return      HTTPStatusCode (201, 404...)
	 */
	public int deleteEinladung(String inviteId) {
		int myStatusCode = -1;
		try {
			String uri = baseURI + "/invite/" + inviteId;
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
	
	public List<Tbleinladung> getEinladungen(String idUser){
		List<Tbleinladung> myEinladungen = null;
		
		try {
			String uri = baseURI + "/user/" + idUser + "/invites";
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "application/xml");
			 
				JAXBContext jc = JAXBContext.newInstance(ListWrapper.class, Tbleinladung.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				InputStream xml = connection.getInputStream();

				// Get object from InputStream
				if (connection.getResponseCode() == 200)  {
					myEinladungen = unmarshalList(unmarshaller, xml);
				}
				connection.disconnect();
				
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return myEinladungen;
	}
	
	/**
	 * Unmarshal a given InputStream (XML) (Collection/list of objects) 
	 * with a given marshaller. Uses the ListWrapper Class to bind
	 * elements to its corresponding class and returns a list of objects.
	 * 
	 * @param unmarshaller JAXBContext unmarshaller
	 * @param xmlStream	XML InputStream
	 * @return List of objects
	 * @throws JAXBException
	 */
	private static <T> List<T> unmarshalList(Unmarshaller unmarshaller, InputStream xmlStream) 
			throws JAXBException {
	    StreamSource xml = new StreamSource(xmlStream);
	    ListWrapper<T> myWrapper = (ListWrapper<T>) unmarshaller.unmarshal(xml, ListWrapper.class).getValue();
	    return myWrapper.getItems();
	}
	
//	private static void marshal(Marshaller marshaller, List<?> list, String name)
//            throws JAXBException {
//        QName qName = new QName(name);
//        ListWrapper wrapper = new ListWrapper(list);
//        JAXBElement<ListWrapper> jaxbElement = new JAXBElement<ListWrapper>(qName,
//                ListWrapper.class, wrapper);
//        marshaller.marshal(jaxbElement, System.out);
//    }
	
}
	
