package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Challenge
 * 
 */
@Entity
@NamedQueries({

@NamedQuery(name = "challange.all", query = "SELECT ch from Challenge ch inner join ch.crowd as c where c.lastName like :args or ch.details.title like :args or ch.details.description like :args or c.firstName like :args") })
public class Challenge implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Details details;
	private Boolean validationStatus;
	private Crowd crowd;
	private String photo;
	private Float reward;
	public Float getReward() {
		return reward;
	}

	public void setReward(Float reward) {
		this.reward = reward;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}



	private int seen=0;
	public int getSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}



	/*
	 * attribute with system date when creation of challenge for generating
	 * statistics about number of project per day , month and year
	 */
	private Date creationDate;
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	

	private List<Participation> participations;

	@OneToMany(mappedBy = "challenge")
	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	@ManyToOne
	public Crowd getCrowd() {
		return crowd;
	}

	public void setCrowd(Crowd crowd) {
		this.crowd = crowd;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Embedded
	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	@Column(name = "validationstatus", columnDefinition = "bit")
	public Boolean isValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(Boolean validationStatus) {
		this.validationStatus = validationStatus;
	}

	public Challenge() {
		super();
	}

}
