package com.greatlearning.employeemanagement.entity;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString(exclude ="user")
@EqualsAndHashCode(of="roleId")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private long roleId;
	@Column(name="role_name")
	private String roleName;
	
	@ManyToOne
	@JoinColumn(name="user_id_fk", nullable =false)
	private  User user;
	

}
