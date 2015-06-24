package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.util.List;


/**
 * The persistent class for the tblkarteikarte database table.
 * 
 */
@UuidGenerator(name="cardUUID")
@XmlRootElement

@Entity
@NamedQuery(name="Tblkarteikarte.findAll", query="SELECT t FROM Tblkarteikarte t")
public class Tblkarteikarte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="cardUUID")
	private String idKarteikarte;

	private String dtDritteAlt;

	private String dtDritterTip;

	private String dtErsteAlt;

	private String dtErsterTip;

	private String dtFrage;

	private String dtSchwierigkeit;

	private String dtTimestamp;

	private String dtZweiteAlt;

	private String dtZweiterTip;

	//bi-directional many-to-one association to Tblkartenset
	@ManyToOne
	@JoinColumn(name="fiKartenset")
	private Tblkartenset tblkartenset;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiErsteller")
	private Tbluser tbluser;

	//bi-directional many-to-one association to Tblkommentar
	@OneToMany(mappedBy="tblkarteikarte")
	private List<Tblkommentar> tblkommentars;

	public Tblkarteikarte() {
	}

	public String getIdKarteikarte() {
		return this.idKarteikarte;
	}

	public void setIdKarteikarte(String idKarteikarte) {
		this.idKarteikarte = idKarteikarte;
	}

	public String getDtDritteAlt() {
		return this.dtDritteAlt;
	}

	public void setDtDritteAlt(String dtDritteAlt) {
		this.dtDritteAlt = dtDritteAlt;
	}

	public String getDtDritterTip() {
		return this.dtDritterTip;
	}

	public void setDtDritterTip(String dtDritterTip) {
		this.dtDritterTip = dtDritterTip;
	}

	public String getDtErsteAlt() {
		return this.dtErsteAlt;
	}

	public void setDtErsteAlt(String dtErsteAlt) {
		this.dtErsteAlt = dtErsteAlt;
	}

	public String getDtErsterTip() {
		return this.dtErsterTip;
	}

	public void setDtErsterTip(String dtErsterTip) {
		this.dtErsterTip = dtErsterTip;
	}

	public String getDtFrage() {
		return this.dtFrage;
	}

	public void setDtFrage(String dtFrage) {
		this.dtFrage = dtFrage;
	}

	public String getDtSchwierigkeit() {
		return this.dtSchwierigkeit;
	}

	public void setDtSchwierigkeit(String dtSchwierigkeit) {
		this.dtSchwierigkeit = dtSchwierigkeit;
	}

	public String getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(String dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public String getDtZweiteAlt() {
		return this.dtZweiteAlt;
	}

	public void setDtZweiteAlt(String dtZweiteAlt) {
		this.dtZweiteAlt = dtZweiteAlt;
	}

	public String getDtZweiterTip() {
		return this.dtZweiterTip;
	}

	public void setDtZweiterTip(String dtZweiterTip) {
		this.dtZweiterTip = dtZweiterTip;
	}

	public Tblkartenset getTblkartenset() {
		return this.tblkartenset;
	}

	public void setTblkartenset(Tblkartenset tblkartenset) {
		this.tblkartenset = tblkartenset;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

	public List<Tblkommentar> getTblkommentars() {
		return this.tblkommentars;
	}

	public void setTblkommentars(List<Tblkommentar> tblkommentars) {
		this.tblkommentars = tblkommentars;
	}

	public Tblkommentar addTblkommentar(Tblkommentar tblkommentar) {
		getTblkommentars().add(tblkommentar);
		tblkommentar.setTblkarteikarte(this);

		return tblkommentar;
	}

	public Tblkommentar removeTblkommentar(Tblkommentar tblkommentar) {
		getTblkommentars().remove(tblkommentar);
		tblkommentar.setTblkarteikarte(null);

		return tblkommentar;
	}

}