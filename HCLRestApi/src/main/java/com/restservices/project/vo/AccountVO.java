package com.restservices.project.vo;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AccountVO {
	
	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String pass;
	@NotEmpty
	private String info;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public String getInfo() {
		return info;
	}
	
}