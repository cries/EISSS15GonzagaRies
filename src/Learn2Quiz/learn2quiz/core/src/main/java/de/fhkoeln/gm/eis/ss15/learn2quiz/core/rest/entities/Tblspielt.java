package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;


/**
 * The persistent class for the tblspielt database table.
 * 
 */
@XmlRootElement
@Entity
@NamedQuery(name="Tblspielt.findAll", query="SELECT t FROM Tblspielt t")
public class Tblspielt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblspieltPK id;

	private String dtPunkte;

	private Timestamp dtTimestamp;

	//bi-directional many-to-one association to Tblspielsession
	@ManyToOne
	@JoinColumn(name="fiSpielsession")
	private Tblspielsession tblspielsession;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiUser")
	private Tbluser tbluser;

	public Tblspielt() {
	}

	public TblspieltPK getId() {
		return this.id;
	}

	public void setId(TblspieltPK id) {
		this.id = id;
	}

	public String getDtPunkte() {
		return this.dtPunkte;
	}

	public void setDtPunkte(String dtPunkte) {
		this.dtPunkte = dtPunkte;
	}

	public Timestamp getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(Timestamp dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public Tblspielsession getTblspielsession() {
		return this.tblspielsession;
	}

	public void setTblspielsession(Tblspielsession tblspielsession) {
		this.tblspielsession = tblspielsession;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

}