package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Project.statisticsDon", query = "SELECT Count(p) FROM Project p WHERE p.fundingModel=1"),
	@NamedQuery(name = "Project.statisticsReward", query = "SELECT Count(p) FROM Project p WHERE p.fundingModel=2"),
	@NamedQuery(name = "Project.statisticsEquity", query = "SELECT Count(p) FROM Project p WHERE p.fundingModel=3"),
	@NamedQuery(name = "Project.statisticsCredit", query = "SELECT Count(p) FROM Project p WHERE p.fundingModel=4") })
public class Project implements Serializable {

	private int id;

	private int fundingModel;
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

	private String avatar;
	private List<Funds>funds;
	@OneToMany(mappedBy="project")
	public List<Funds> getFunds() {
		return funds;
	}

	public void setFunds(List<Funds> funds) {
		this.funds = funds;
	}

	@ManyToOne
	public Crowd getCrowd() {
		return crowd;
	}

	public void setCrowd(Crowd crowd) {
		this.crowd = crowd;
	}

	private double investmentAmount;

	private boolean validationStatus;

	private Crowd crowd;
	private Category category;
	private Details details;
	@Embedded
	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	@ManyToOne
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFundingModel() {
		return this.fundingModel;
	}

	public void setFundingModel(int fundingModel) {
		this.fundingModel = fundingModel;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public double getInvestmentAmount() {
		return this.investmentAmount;
	}

	public void setInvestmentAmount(double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	


	@Column(name = "validationstatus", columnDefinition = "bit")
	public Boolean isValidationStatus() {
		return validationStatus;
	}
	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}

}
