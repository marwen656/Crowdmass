package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Funds
 * 
 */
@Entity
public class Funds implements Serializable {

	private static final long serialVersionUID = 1L;
	private FundsPK fundsPK;
	@EmbeddedId
	
	public FundsPK getFundsPK() {
		return fundsPK;
	}

	public void setFundsPK(FundsPK fundsPK) {
		this.fundsPK = fundsPK;
	}

	private int type;
	private double amount;
	private Date date;
	private Crowd crowd;
	private Project project;

	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@ManyToOne
	@JoinColumn(name="idCrowd", referencedColumnName="id", insertable=false,updatable=false)
	public Crowd getCrowd() {
		return crowd;
	}

	public void setCrowd(Crowd crowd) {
		this.crowd = crowd;
	}
	@ManyToOne
	@JoinColumn(name="idProject", referencedColumnName="id", insertable=false,updatable=false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	 public Funds() {
		super();
	}

}
