package com.academy.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AcademyUser {

	public enum Role {
		
		ROLE_USER, ROLE_ADMIN, ROLE_OWNER;
	}

	@Id
	private String id;

	@Indexed
	private String name;

	private String password;

	@DBRef
	private Academy academy;

	private boolean active;

	private List<Role> roles;

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Academy getAcademy() {
		return academy;
	}

	public boolean isActive() {
		return active;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if(roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
	}

	public String[] getRolesAsArray() {
		Collection<String> rolesString = roles.stream().map(role -> role.name()).collect(Collectors.toList());
		String[] array = rolesString.toArray(new String[roles.size()]);
		return array;
	}

}
