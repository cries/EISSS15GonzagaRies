package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tblspielsession database table.
 * 
 */
@UuidGenerator(name="UUID")
@XmlRootElement

@Entity
@NamedQuery(name="Tblspielsession.findAll", query="SELECT t FROM Tblspielsession t")
public class Tblspielsession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="UUID")
	private String idSpielsession;

	private Timestamp dtTimestamp;

	//bi-directional many-to-one association to Tblkartenset
	@ManyToOne
	@JoinColumn(name="fiKartenset")
	private Tblkartenset tblkartenset;

	//bi-directional many-to-one association to Tblspielt
	@OneToMany(mappedBy="tblspielsession")
	private List<Tblspielt> tblspielts;

	public Tblspielsession() {
	}

	public String getIdSpielsession() {
		return this.idSpielsession;
	}

	public void setIdSpielsession(String idSpielsession) {
		this.idSpielsession = idSpielsession;
	}

	public Timestamp getDtTimestamp() {
		return this.dtTimestamp;
	}

	public void setDtTimestamp(Timestamp dtTimestamp) {
		this.dtTimestamp = dtTimestamp;
	}

	public Tblkartenset getTblkartenset() {
		return this.tblkartenset;
	}

	public void setTblkartenset(Tblkartenset tblkartenset) {
		this.tblkartenset = tblkartenset;
	}

	public List<Tblspielt> getTblspielts() {
		return this.tblspielts;
	}

	public void setTblspielts(List<Tblspielt> tblspielts) {
		this.tblspielts = tblspielts;
	}

	public Tblspielt addTblspielt(Tblspielt tblspielt) {
		getTblspielts().add(tblspielt);
		tblspielt.setTblspielsession(this);

		return tblspielt;
	}

	public Tblspielt removeTblspielt(Tblspielt tblspielt) {
		getTblspielts().remove(tblspielt);
		tblspielt.setTblspielsession(null);

		return tblspielt;
	}

}