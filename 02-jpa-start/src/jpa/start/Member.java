/*
 * CREATE TABLE MEMBER (
 * ID VARCHAR(255) NOT NULL,
 * NAME VARCHAR(255),
 * AGE INTEGER NOT NULL,
 * PRIMARY KEY (ID)
 * )
 */
package jpa.start;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class Member {
	@Id
	@Column(name="ID")
	private String id;			// ID
	
	@Column(name="NAME")
	private String username;	// NAME
	
	private Integer age;		// AGE
	
	public String getId() {
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
