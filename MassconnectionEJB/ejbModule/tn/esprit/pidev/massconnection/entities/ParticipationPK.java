package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class ParticipationPK implements Serializable {
	private int idCrowd;
	private int idChallenge;
	public int getIdCrowd() {
		return idCrowd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idChallenge;
		result = prime * result + idCrowd;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipationPK other = (ParticipationPK) obj;
		if (idChallenge != other.idChallenge)
			return false;
		if (idCrowd != other.idCrowd)
			return false;
		return true;
	}
	public void setIdCrowd(int idCrowd) {
		this.idCrowd = idCrowd;
	}
	public int getIdChallenge() {
		return idChallenge;
	}
	public void setIdChallenge(int idChallenge) {
		this.idChallenge = idChallenge;
	}

}
