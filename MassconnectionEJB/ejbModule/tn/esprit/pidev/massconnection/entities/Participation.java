package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Participation
 *
 */
@Entity

public class Participation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private ParticipationPK participationPK;
	@EmbeddedId
	public ParticipationPK getParticipationPK() {
		return participationPK;
	}
	public void setParticipationPK(ParticipationPK participationPK) {
		this.participationPK = participationPK;
	}
	private Crowd crowd;
	private Challenge challenge;
	private Date date;
	private List<Feedback>feedbacks;
	@OneToMany(mappedBy="participation")
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	private List<Solution>solutions;
	@OneToMany(mappedBy="participation")
	public List<Solution> getSolutions() {
		return solutions;
	}
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
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
	@JoinColumn(name="idChallenge", referencedColumnName="id", insertable=false,updatable=false)
	public Challenge getChallenge() {
		return challenge;
	}
	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Participation() {
		super();
	}
   
}
