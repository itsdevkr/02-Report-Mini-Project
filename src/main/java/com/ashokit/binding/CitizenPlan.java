package com.ashokit.binding;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZENS_PLAN_INFO")
public class CitizenPlan {
	
	@Id
	private int cid;
	private String planName;
	private String planStatus;
	private String cname;
	private String cemail;
	private Long phno;
	private Long ssn;
	private String gender;
		
	
}
