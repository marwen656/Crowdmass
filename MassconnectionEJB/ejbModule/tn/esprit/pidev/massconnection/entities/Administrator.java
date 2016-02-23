package tn.esprit.pidev.massconnection.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrator
 *
 */
@Entity
@NamedQueries({
	 @NamedQuery(name = "Administrator.findByLoginAndPassword", query = "SELECT a FROM User a WHERE a.userName = :username and a.password = :password")})
public class Administrator extends User implements Serializable {

	
	

	public Administrator() {
		super();
	}
   
}
