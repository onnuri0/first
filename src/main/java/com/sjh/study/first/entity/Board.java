package com.sjh.study.first.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="st_board")
public class Board {

	@Id
	@GenericGenerator(name="id_generator", strategy="com.sjh.study.first.generator.RnfTbIdGenerator")
	@GeneratedValue(generator="id_generator")
	@Column(name="bd_id", unique=true, nullable=false, length=50)  // unique=true 
	private String bdId;
	
	@Column(name="bd_title", nullable=false, length=200)
	private String bdTitle;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="bd_write_date")
	private Date bdWriteDate;
	
	@Lob
	@Column(name="bd_content")
	private String bdContent;
	
	private Integer bdCount;
	
	@JoinColumn(name = "bd_writer_id", referencedColumnName = "bu_id")
    @ManyToOne
	private User bdWriterId;
	
}
