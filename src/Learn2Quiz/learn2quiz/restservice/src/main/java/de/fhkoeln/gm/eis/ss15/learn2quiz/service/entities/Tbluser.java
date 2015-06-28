package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.util.List;


/**
 * The persistent class for the tbluser database table.
 * 
 */
@UuidGenerator(name="userUUID")
@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="Tbluser.findAll", query="SELECT t FROM Tbluser t"), 
})
public class Tbluser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="userUUID")
	private String idUser;

	private String dtBenutzername;

	private String dtEmail;

	private String dtPasswort;

	private int dtPunktzahl;

	//bi-directional many-to-one association to Tbleinladung
	@OneToMany(mappedBy="tbluser")
	private List<Tbleinladung> tbleinladungs;

	//bi-directional many-to-one association to Tblerhaelt
	@OneToMany(mappedBy="tbluser")
	private List<Tblerhaelt> tblerhaelts;

	//bi-directional many-to-one association to Tblgruppe
	@OneToMany(mappedBy="tbluser", cascade = CascadeType.PERSIST) //
	private List<Tblgruppe> tblgruppes;

	//bi-directional many-to-one association to TblistTeil
	@OneToMany(mappedBy="tbluser")
	private List<TblistTeil> tblistteils;

	//bi-directional many-to-one association to Tblkarteikarte
	@OneToMany(mappedBy="tbluser")
	private List<Tblkarteikarte> tblkarteikartes;

	//bi-directional many-to-one association to Tblkartenset
	@OneToMany(mappedBy="tbluser")
	private List<Tblkartenset> tblkartensets;

	//bi-directional many-to-one association to Tblkommentar
	@OneToMany(mappedBy="tbluser")
	private List<Tblkommentar> tblkommentars;

	//bi-directional many-to-one association to Tblspielt
	@OneToMany(mappedBy="tbluser")
	private List<Tblspielt> tblspielts;

	public Tbluser() {
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getDtBenutzername() {
		return this.dtBenutzername;
	}

	public void setDtBenutzername(String dtBenutzername) {
		this.dtBenutzername = dtBenutzername;
	}

	public String getDtEmail() {
		return this.dtEmail;
	}

	public void setDtEmail(String dtEmail) {
		this.dtEmail = dtEmail;
	}

	public String getDtPasswort() {
		return this.dtPasswort;
	}

	public void setDtPasswort(String dtPasswort) {
		this.dtPasswort = dtPasswort;
	}

	public int getDtPunktzahl() {
		return this.dtPunktzahl;
	}

	public void setDtPunktzahl(int dtPunktzahl) {
		this.dtPunktzahl = dtPunktzahl;
	}

	public List<Tbleinladung> getTbleinladungs() {
		return this.tbleinladungs;
	}

	public void setTbleinladungs(List<Tbleinladung> tbleinladungs) {
		this.tbleinladungs = tbleinladungs;
	}

	public Tbleinladung addTbleinladung(Tbleinladung tbleinladung) {
		getTbleinladungs().add(tbleinladung);
		tbleinladung.setTbluser(this);

		return tbleinladung;
	}

	public Tbleinladung removeTbleinladung(Tbleinladung tbleinladung) {
		getTbleinladungs().remove(tbleinladung);
		tbleinladung.setTbluser(null);

		return tbleinladung;
	}

	public List<Tblerhaelt> getTblerhaelts() {
		return this.tblerhaelts;
	}

	public void setTblerhaelts(List<Tblerhaelt> tblerhaelts) {
		this.tblerhaelts = tblerhaelts;
	}

	public Tblerhaelt addTblerhaelt(Tblerhaelt tblerhaelt) {
		getTblerhaelts().add(tblerhaelt);
		tblerhaelt.setTbluser(this);

		return tblerhaelt;
	}

	public Tblerhaelt removeTblerhaelt(Tblerhaelt tblerhaelt) {
		getTblerhaelts().remove(tblerhaelt);
		tblerhaelt.setTbluser(null);

		return tblerhaelt;
	}

	public List<Tblgruppe> getTblgruppes() {
		return this.tblgruppes;
	}

	public void setTblgruppes(List<Tblgruppe> tblgruppes) {
		this.tblgruppes = tblgruppes;
	}

	public Tblgruppe addTblgruppe(Tblgruppe tblgruppe) {
		getTblgruppes().add(tblgruppe);
		tblgruppe.setTbluser(this);

		return tblgruppe;
	}

	public Tblgruppe removeTblgruppe(Tblgruppe tblgruppe) {
		getTblgruppes().remove(tblgruppe);
		tblgruppe.setTbluser(null);

		return tblgruppe;
	}

	public List<TblistTeil> getTblistteils() {
		return this.tblistteils;
	}

	public void setTblistteils(List<TblistTeil> tblistteils) {
		this.tblistteils = tblistteils;
	}

	public TblistTeil addTblistteil(TblistTeil tblistteil) {
		getTblistteils().add(tblistteil);
		tblistteil.setTbluser(this);

		return tblistteil;
	}

	public TblistTeil removeTblistteil(TblistTeil tblistteil) {
		getTblistteils().remove(tblistteil);
		tblistteil.setTbluser(null);

		return tblistteil;
	}

	public List<Tblkarteikarte> getTblkarteikartes() {
		return this.tblkarteikartes;
	}

	public void setTblkarteikartes(List<Tblkarteikarte> tblkarteikartes) {
		this.tblkarteikartes = tblkarteikartes;
	}

	public Tblkarteikarte addTblkarteikarte(Tblkarteikarte tblkarteikarte) {
		getTblkarteikartes().add(tblkarteikarte);
		tblkarteikarte.setTbluser(this);

		return tblkarteikarte;
	}

	public Tblkarteikarte removeTblkarteikarte(Tblkarteikarte tblkarteikarte) {
		getTblkarteikartes().remove(tblkarteikarte);
		tblkarteikarte.setTbluser(null);

		return tblkarteikarte;
	}

	public List<Tblkartenset> getTblkartensets() {
		return this.tblkartensets;
	}

	public void setTblkartensets(List<Tblkartenset> tblkartensets) {
		this.tblkartensets = tblkartensets;
	}

	public Tblkartenset addTblkartenset(Tblkartenset tblkartenset) {
		getTblkartensets().add(tblkartenset);
		tblkartenset.setTbluser(this);

		return tblkartenset;
	}

	public Tblkartenset removeTblkartenset(Tblkartenset tblkartenset) {
		getTblkartensets().remove(tblkartenset);
		tblkartenset.setTbluser(null);

		return tblkartenset;
	}

	public List<Tblkommentar> getTblkommentars() {
		return this.tblkommentars;
	}

	public void setTblkommentars(List<Tblkommentar> tblkommentars) {
		this.tblkommentars = tblkommentars;
	}

	public Tblkommentar addTblkommentar(Tblkommentar tblkommentar) {
		getTblkommentars().add(tblkommentar);
		tblkommentar.setTbluser(this);

		return tblkommentar;
	}

	public Tblkommentar removeTblkommentar(Tblkommentar tblkommentar) {
		getTblkommentars().remove(tblkommentar);
		tblkommentar.setTbluser(null);

		return tblkommentar;
	}

	public List<Tblspielt> getTblspielts() {
		return this.tblspielts;
	}

	public void setTblspielts(List<Tblspielt> tblspielts) {
		this.tblspielts = tblspielts;
	}

	public Tblspielt addTblspielt(Tblspielt tblspielt) {
		getTblspielts().add(tblspielt);
		tblspielt.setTbluser(this);

		return tblspielt;
	}

	public Tblspielt removeTblspielt(Tblspielt tblspielt) {
		getTblspielts().remove(tblspielt);
		tblspielt.setTbluser(null);

		return tblspielt;
	}

}