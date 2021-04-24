/**
 * 
 */
package com.gadgetBadget.model;

/**
 * @author chithu
 *
 */
public class Researcher {
	
	private int id;
	private String name;
	private String projectType;
	private float payment;
	private int contact;
	
	public Researcher(int id, String name, String projectType, float payment, int contact) {
		super();
		this.id = id;
		this.name = name;
		this.projectType = projectType;
		this.payment = payment;
		this.contact = contact;
	}
	
	public Researcher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}
	
	
	

}


