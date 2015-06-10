package de.fhkoeln.gm.eis.ss15.learn2quiz.service.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tblistteil database table.
 * 
 */
@Embeddable
public class TblistteilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String fiUser;

	@Column(insertable=false, updatable=false)
	private String fiGruppe;

	public TblistteilPK() {
	}
	public String getFiUser() {
		return this.fiUser;
	}
	public void setFiUser(String fiUser) {
		this.fiUser = fiUser;
	}
	public String getFiGruppe() {
		return this.fiGruppe;
	}
	public void setFiGruppe(String fiGruppe) {
		this.fiGruppe = fiGruppe;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TblistteilPK)) {
			return false;
		}
		TblistteilPK castOther = (TblistteilPK)other;
		return 
			this.fiUser.equals(castOther.fiUser)
			&& this.fiGruppe.equals(castOther.fiGruppe);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fiUser.hashCode();
		hash = hash * prime + this.fiGruppe.hashCode();
		
		return hash;
	}
}