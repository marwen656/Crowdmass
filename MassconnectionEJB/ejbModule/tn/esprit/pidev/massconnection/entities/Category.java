package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 * 
 */
@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	public String picture;
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Category(String title) {
		super();
		this.title = title;
	}

	private String title;
	private List<Project> projects;

	@OneToMany(mappedBy = "category",fetch=FetchType.EAGER)
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category() {
		super();
	}

}
