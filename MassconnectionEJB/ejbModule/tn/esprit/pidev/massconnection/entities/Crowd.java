package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Crowd
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Crowd.statisticsCrowdsCreator", query = "SELECT Count(c) FROM Crowd c WHERE c.isCreator=true"),
		@NamedQuery(name = "Crowd.statisticsCrowdsInvestor", query = "SELECT Count(c) FROM Crowd c WHERE c.isInvestor=true"),
		@NamedQuery(name = "Crowd.statisticsCrowdsChallengeCreator", query = "SELECT Count(c) FROM Crowd c WHERE c.isChallengeCreator=true"),
		@NamedQuery(name = "Crowd.statisticsCrowdsChallengeSolver", query = "SELECT Count(c) FROM Crowd c WHERE c.isChallengeSolver=true") })
public class Crowd extends User implements Serializable {

	private int id;
	private Date date_of_birth;
	private String firstName;
	private String lastName;
	private boolean isConnected;
	private boolean isCreator;
	private boolean isInvestor;
	private boolean isChallengeCreator;
	private boolean isChallengeSolver;
	private String avatar;
	private Integer mark;

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	private List<Project> projects;
	private List<Funds> funds;
	private List<Participation> participations;
	private List<Message> messagesRecepient;
	private List<Message> messageSender;
	private List<Notification> notifications;
	private int status=1;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ManyToMany(mappedBy="crowds")
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	@OneToMany(mappedBy = "recepient")
	public List<Message> getMessagesRecepient() {
		return messagesRecepient;
	}

	public void setMessagesRecepient(List<Message> messagesRecepient) {
		this.messagesRecepient = messagesRecepient;
	}

	@OneToMany(mappedBy = "sender")
	public List<Message> getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(List<Message> messageSender) {
		this.messageSender = messageSender;
	}

	@OneToMany(mappedBy = "crowd")
	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	private List<Challenge> challenges;

	@OneToMany(mappedBy = "crowd")
	public List<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(List<Challenge> challenges) {
		this.challenges = challenges;
	}

	@OneToMany(mappedBy = "crowd")
	public List<Funds> getFunds() {
		return funds;
	}

	public void setFunds(List<Funds> funds) {
		this.funds = funds;
	}

	@OneToMany(mappedBy = "crowd")
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setCreator(boolean isCreator) {
		this.isCreator = isCreator;
	}

	public void setInvestor(boolean isInvestor) {
		this.isInvestor = isInvestor;
	}

	public void setChallengeCreator(boolean isChallengeCreator) {
		this.isChallengeCreator = isChallengeCreator;
	}

	public void setChallengeSolver(boolean isChallengeSolver) {
		this.isChallengeSolver = isChallengeSolver;
	}

	private static final long serialVersionUID = 1L;

	public Crowd() {
		super();
	}
	
	public Date getDate_of_birth() {
		return this.date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/*
	 * varible with boolean type which become true when the crowd create a
	 * project .
	 * this variable can help us to make statistics
	 */
	@Column(name = "iscreator", columnDefinition = "bit")
	public boolean getIsCreator() {
		return this.isCreator;
	}

	public void setIsCreator(boolean isCreator) {
		this.isCreator = isCreator;
	}
	/*
	 * varible with boolean type which become true when the crowd funds a
	 * challenge .
	 * this variable can help us to make statistics
	 */
	@Column(name = "isinvestor", columnDefinition = "bit")
	public boolean getIsInvestor() {
		return this.isInvestor;
	}

	public void setIsInvestor(boolean isInvestor) {
		this.isInvestor = isInvestor;
	}
	/*
	 * varible with boolean type which become true when the crowd creates a
	 * challenge .
	 * this variable can help us to make statistics
	 */
	@Column(name = "ischallegnecreator", columnDefinition = "bit")
	public boolean getIsChallengeCreator() {
		return this.isChallengeCreator;
	}

	public void setIsChallengeCreator(boolean isChallengeCreator) {
		this.isChallengeCreator = isChallengeCreator;
	}

	/*
	 * varible with boolean type which become true when the crowd participates a
	 * challenge .
	 * this variable can help us to make statistics
	 */
	@Column(name = "ischallengesolver", columnDefinition = "bit")
	public boolean getIsChallengeSolver() {
		return this.isChallengeSolver;
	}

	public void setIsChallengeSolver(boolean isChallengeSolver) {
		this.isChallengeSolver = isChallengeSolver;
	}

	/*
	 * varible with boolean type which become true when the crowd connect this
	 * variable can help us to make statistics
	 */
	@Column(name = "isconnected", columnDefinition = "bit")
	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}
