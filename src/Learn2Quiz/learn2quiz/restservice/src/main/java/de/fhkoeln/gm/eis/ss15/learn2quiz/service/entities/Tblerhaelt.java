package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The persistent class for the tblerhaelt database table.
 * 
 */
@XmlRootElement
@Entity
@NamedQuery(name="Tblerhaelt.findAll", query="SELECT t FROM Tblerhaelt t")
public class Tblerhaelt implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblerhaeltPK id;

	private String dtTimestamp;

	//bi-directional many-to-one association to Tbleinladung
	@ManyToOne
	@JoinColumn(name="fiEinladung")
	private Tbleinladung tbleinladung;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiEmpfänger")
	private Tbluser tbluser;

	public Tblerhaelt() {
	}

	public TblerhaeltPK getId() {
		return this.id;
	}

	public void setId(TblerhaeltPK id) {
		this.id = id;
	}

	public String getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(String dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public Tbleinladung getTbleinladung() {
		return this.tbleinladung;
	}

	public void setTbleinladung(Tbleinladung tbleinladung) {
		this.tbleinladung = tbleinladung;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

}