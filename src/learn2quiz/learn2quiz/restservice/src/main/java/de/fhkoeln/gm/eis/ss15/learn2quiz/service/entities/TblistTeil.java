package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


import java.sql.Timestamp;


/**
 * The persistent class for the tblistteil database table.
 * 
 */
@XmlRootElement
@Entity
@NamedQuery(name="TblistTeil.findAll", query="SELECT t FROM TblistTeil t")
public class TblistTeil implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TblistTeilPK id;

	private Timestamp dtTimestamp;

	//bi-directional many-to-one association to Tblgruppe
	@ManyToOne
	@JoinColumn(name="fiGruppe")
	private Tblgruppe tblgruppe;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiUser")
	private Tbluser tbluser;

	public TblistTeil() {
	}

	public TblistTeilPK getId() {
		return this.id;
	}

	public void setId(TblistTeilPK id) {
		this.id = id;
	}

	public Timestamp getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(Timestamp dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public Tblgruppe getTblgruppe() {
		return this.tblgruppe;
	}

	public void setTblgruppe(Tblgruppe tblgruppe) {
		this.tblgruppe = tblgruppe;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

}