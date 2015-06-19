package de.fhkoeln.gm.eis.ss15.learn2quiz.core.rest.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tblerhaelt database table.
 * 
 */
@Embeddable
public class TblerhaeltPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String fiEinladung;

	@Column(insertable=false, updatable=false)
	private String fiEmpf�nger;

	public TblerhaeltPK() {
	}
	public String getFiEinladung() {
		return this.fiEinladung;
	}
	public void setFiEinladung(String fiEinladung) {
		this.fiEinladung = fiEinladung;
	}
	public String getFiEmpf�nger() {
		return this.fiEmpf�nger;
	}
	public void setFiEmpf�nger(String fiEmpf�nger) {
		this.fiEmpf�nger = fiEmpf�nger;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblerhaeltPK)) {
			return false;
		}
		TblerhaeltPK castOther = (TblerhaeltPK)other;
		return 
			this.fiEinladung.equals(castOther.fiEinladung)
			&& this.fiEmpf�nger.equals(castOther.fiEmpf�nger);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fiEinladung.hashCode();
		hash = hash * prime + this.fiEmpf�nger.hashCode();
		
		return hash;
	}
}