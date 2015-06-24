package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.util.List;


/**
 * The persistent class for the tblgruppe database table.
 * 
 */
@UuidGenerator(name="groupUUID")
@XmlRootElement
@Entity
@NamedQuery(name="Tblgruppe.findAll", query="SELECT t FROM Tblgruppe t")
public class Tblgruppe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="groupUUID")
	private String idGruppe;

	private String dtBeschreibung;

	private String dtTimestamp;

	private String dtTitel;

	//bi-directional many-to-one association to Tbleinladung
	@OneToMany(mappedBy="tblgruppe")
	private List<Tbleinladung> tbleinladungs;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiErsteller")
	private Tbluser tbluser;

	//bi-directional many-to-one association to TblistTeil
	@OneToMany(mappedBy="tblgruppe")
	private List<TblistTeil> tblistteils;

	//bi-directional many-to-one association to Tblkartenset
	@OneToMany(mappedBy="tblgruppe")
	private List<Tblkartenset> tblkartensets;

	public Tblgruppe() {
	}

	public String getIdGruppe() {
		return this.idGruppe;
	}

	public void setIdGruppe(String idGruppe) {
		this.idGruppe = idGruppe;
	}

	public String getDtBeschreibung() {
		return this.dtBeschreibung;
	}

	public void setDtBeschreibung(String dtBeschreibung) {
		this.dtBeschreibung = dtBeschreibung;
	}

	public String getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(String dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public String getDtTitel() {
		return this.dtTitel;
	}

	public void setDtTitel(String dtTitel) {
		this.dtTitel = dtTitel;
	}

	public List<Tbleinladung> getTbleinladungs() {
		return this.tbleinladungs;
	}

	public void setTbleinladungs(List<Tbleinladung> tbleinladungs) {
		this.tbleinladungs = tbleinladungs;
	}

	public Tbleinladung addTbleinladung(Tbleinladung tbleinladung) {
		getTbleinladungs().add(tbleinladung);
		tbleinladung.setTblgruppe(this);

		return tbleinladung;
	}

	public Tbleinladung removeTbleinladung(Tbleinladung tbleinladung) {
		getTbleinladungs().remove(tbleinladung);
		tbleinladung.setTblgruppe(null);

		return tbleinladung;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

	public List<TblistTeil> getTblistteils() {
		return this.tblistteils;
	}

	public void setTblistteils(List<TblistTeil> tblistteils) {
		this.tblistteils = tblistteils;
	}

	public TblistTeil addTblistteil(TblistTeil tblistteil) {
		getTblistteils().add(tblistteil);
		tblistteil.setTblgruppe(this);

		return tblistteil;
	}

	public TblistTeil removeTblistteil(TblistTeil tblistteil) {
		getTblistteils().remove(tblistteil);
		tblistteil.setTblgruppe(null);

		return tblistteil;
	}

	public List<Tblkartenset> getTblkartensets() {
		return this.tblkartensets;
	}

	public void setTblkartensets(List<Tblkartenset> tblkartensets) {
		this.tblkartensets = tblkartensets;
	}

	public Tblkartenset addTblkartenset(Tblkartenset tblkartenset) {
		getTblkartensets().add(tblkartenset);
		tblkartenset.setTblgruppe(this);

		return tblkartenset;
	}

	public Tblkartenset removeTblkartenset(Tblkartenset tblkartenset) {
		getTblkartensets().remove(tblkartenset);
		tblkartenset.setTblgruppe(null);

		return tblkartenset;
	}

}