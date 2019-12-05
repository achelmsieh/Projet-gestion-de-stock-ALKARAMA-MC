package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bytebuddy.implementation.bind.annotation.Default;

@Entity
@Table(name="users")
public class User {
@Id
private String username;
private String password;

private boolean actived=true;
@ManyToMany
@JoinTable(name="USERS_ROLES")
private List<Role> roles;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String username, String password, boolean actived) {
	super();
	this.username = username;
	this.password = password;
	this.actived = actived;
}
public boolean isActived() {
	return actived;
}
public void setActived(boolean actived) {
	this.actived = actived;
}

}
