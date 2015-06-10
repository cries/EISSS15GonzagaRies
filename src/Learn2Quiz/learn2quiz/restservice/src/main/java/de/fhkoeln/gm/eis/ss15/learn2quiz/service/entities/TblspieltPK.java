package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tblspielt database table.
 * 
 */
@Embeddable
public class TblspieltPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String fiUser;

	@Column(insertable=false, updatable=false)
	private String fiSpielsession;

	public TblspieltPK() {
	}
	public String getFiUser() {
		return this.fiUser;
	}
	public void setFiUser(String fiUser) {
		this.fiUser = fiUser;
	}
	public String getFiSpielsession() {
		return this.fiSpielsession;
	}
	public void setFiSpielsession(String fiSpielsession) {
		this.fiSpielsession = fiSpielsession;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblspieltPK)) {
			return false;
		}
		TblspieltPK castOther = (TblspieltPK)other;
		return 
			this.fiUser.equals(castOther.fiUser)
			&& this.fiSpielsession.equals(castOther.fiSpielsession);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fiUser.hashCode();
		hash = hash * prime + this.fiSpielsession.hashCode();
		
		return hash;
	}
}