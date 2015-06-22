package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.sql.Timestamp;


/**
 * The persistent class for the tblkommentar database table.
 * 
 */
@UuidGenerator(name="commentUUID")
@XmlRootElement

@Entity
@NamedQuery(name="Tblkommentar.findAll", query="SELECT t FROM Tblkommentar t")
public class Tblkommentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="commentUUID")
	private String idKommentar;

	private String dtInhalt;

	private String dtTimestamp;

	//bi-directional many-to-one association to Tblkarteikarte
	@ManyToOne
	@JoinColumn(name="fiKarteikarte")
	private Tblkarteikarte tblkarteikarte;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiErsteller")
	private Tbluser tbluser;

	public Tblkommentar() {
	}

	public String getIdKommentar() {
		return this.idKommentar;
	}

	public void setIdKommentar(String idKommentar) {
		this.idKommentar = idKommentar;
	}

	public String getDtInhalt() {
		return this.dtInhalt;
	}

	public void setDtInhalt(String dtInhalt) {
		this.dtInhalt = dtInhalt;
	}

	public String getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(String dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public Tblkarteikarte getTblkarteikarte() {
		return this.tblkarteikarte;
	}

	public void setTblkarteikarte(Tblkarteikarte tblkarteikarte) {
		this.tblkarteikarte = tblkarteikarte;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

}