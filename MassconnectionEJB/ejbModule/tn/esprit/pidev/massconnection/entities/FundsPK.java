package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class FundsPK implements Serializable{
	private int idCrowd;
	private int idProject;
	public int getIdCrowd() {
		return idCrowd;
	}
	public void setIdCrowd(int idCrowd) {
		this.idCrowd = idCrowd;
	}
	public int getIdProject() {
		return idProject;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCrowd;
		result = prime * result + idProject;
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
		FundsPK other = (FundsPK) obj;
		if (idCrowd != other.idCrowd)
			return false;
		if (idProject != other.idProject)
			return false;
		return true;
	}
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

}
