package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private int type;
	private String text;
	private List<Crowd> crowds;
	
	
	@ManyToMany()
	
	public List<Crowd> getCrowds() {
		return crowds;
	}


	public void setCrowds(List<Crowd> crowds) {
		this.crowds = crowds;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Notification() {
		super();
	}
   
}
