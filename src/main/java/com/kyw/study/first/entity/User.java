package com.kyw.study.first.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="st_user")
public class User {

	@Id
	@GenericGenerator(name="id_generator", strategy="com.kyw.study.first.generator.RnfTbIdGenerator")
	@GeneratedValue(generator="id_generator")
	@Column(name="bu_id", unique=true, nullable=false)  // unique=true 
	private String buId;
	
	@Column(name="bu_name", nullable=false, length=50)
	private String buName;
	
	@Column(name="bu_role", nullable=false, length=50)
	private String buRole;
	
}
