package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tbleinladung database table.
 * 
 */
@UuidGenerator(name="inviteUUID")
@XmlRootElement

@Entity
@NamedQuery(name="Tbleinladung.findAll", query="SELECT t FROM Tbleinladung t")
public class Tbleinladung implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="inviteUUID")
	private String idEinladung;

	private String dtTimestamp;

	//bi-directional many-to-one association to Tblgruppe
	@ManyToOne
	@JoinColumn(name="fiGruppe")
	private Tblgruppe tblgruppe;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiAbsender")
	private Tbluser tbluser;

	//bi-directional many-to-one association to Tblerhaelt
	@OneToMany(mappedBy="tbleinladung")
	private List<Tblerhaelt> tblerhaelts;

	public Tbleinladung() {
	}

	public String getIdEinladung() {
		return this.idEinladung;
	}

	public void setIdEinladung(String idEinladung) {
		this.idEinladung = idEinladung;
	}

	public String getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(String dtTimestamp) {
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

	public List<Tblerhaelt> getTblerhaelts() {
		return this.tblerhaelts;
	}

	public void setTblerhaelts(List<Tblerhaelt> tblerhaelts) {
		this.tblerhaelts = tblerhaelts;
	}

	public Tblerhaelt addTblerhaelt(Tblerhaelt tblerhaelt) {
		getTblerhaelts().add(tblerhaelt);
		tblerhaelt.setTbleinladung(this);

		return tblerhaelt;
	}

	public Tblerhaelt removeTblerhaelt(Tblerhaelt tblerhaelt) {
		getTblerhaelts().remove(tblerhaelt);
		tblerhaelt.setTbleinladung(null);

		return tblerhaelt;
	}

}