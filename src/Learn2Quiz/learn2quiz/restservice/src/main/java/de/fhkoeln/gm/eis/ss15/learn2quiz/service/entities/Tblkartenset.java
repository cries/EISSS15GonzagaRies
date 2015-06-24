package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.util.List;


/**
 * The persistent class for the tblkartenset database table.
 * 
 */
@UuidGenerator(name="cardsetUUID")
@XmlRootElement

@Entity
@NamedQuery(name="Tblkartenset.findAll", query="SELECT t FROM Tblkartenset t")
public class Tblkartenset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="cardsetUUID")
	private String idKartenset;

	private String dtTimestamp;

	private String dtTitel;

	//bi-directional many-to-one association to Tblkarteikarte
	@OneToMany(mappedBy="tblkartenset")
	private List<Tblkarteikarte> tblkarteikartes;

	//bi-directional many-to-one association to Tblgruppe
	@ManyToOne
	@JoinColumn(name="fiGruppe")
	private Tblgruppe tblgruppe;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="fiErsteller")
	private Tbluser tbluser;

	//bi-directional many-to-one association to Tblspielsession
	@OneToMany(mappedBy="tblkartenset")
	private List<Tblspielsession> tblspielsessions;

	public Tblkartenset() {
	}

	public String getIdKartenset() {
		return this.idKartenset;
	}

	public void setIdKartenset(String idKartenset) {
		this.idKartenset = idKartenset;
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

	public List<Tblkarteikarte> getTblkarteikartes() {
		return this.tblkarteikartes;
	}

	public void setTblkarteikartes(List<Tblkarteikarte> tblkarteikartes) {
		this.tblkarteikartes = tblkarteikartes;
	}

	public Tblkarteikarte addTblkarteikarte(Tblkarteikarte tblkarteikarte) {
		getTblkarteikartes().add(tblkarteikarte);
		tblkarteikarte.setTblkartenset(this);

		return tblkarteikarte;
	}

	public Tblkarteikarte removeTblkarteikarte(Tblkarteikarte tblkarteikarte) {
		getTblkarteikartes().remove(tblkarteikarte);
		tblkarteikarte.setTblkartenset(null);

		return tblkarteikarte;
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

	public List<Tblspielsession> getTblspielsessions() {
		return this.tblspielsessions;
	}

	public void setTblspielsessions(List<Tblspielsession> tblspielsessions) {
		this.tblspielsessions = tblspielsessions;
	}

	public Tblspielsession addTblspielsession(Tblspielsession tblspielsession) {
		getTblspielsessions().add(tblspielsession);
		tblspielsession.setTblkartenset(this);

		return tblspielsession;
	}

	public Tblspielsession removeTblspielsession(Tblspielsession tblspielsession) {
		getTblspielsessions().remove(tblspielsession);
		tblspielsession.setTblkartenset(null);

		return tblspielsession;
	}

}