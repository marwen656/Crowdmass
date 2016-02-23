package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Message implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int id;
	private Crowd recepient;
	private Crowd sender;
	private String text;
	private Date date;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	public Crowd getRecepient() {
		return recepient;
	}

	public void setRecepient(Crowd recepient) {
		this.recepient = recepient;
	}
	@ManyToOne
	public Crowd getSender() {
		return sender;
	}

	public void setSender(Crowd sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Message() {
		super();
	}
   
}
