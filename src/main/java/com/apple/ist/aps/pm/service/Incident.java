package com.apple.ist.aps.pm.service;

public class Incident {
	private long id;
	private int position;
	private String incident;
	private String created;
	private String type;
	private int status;
	
	public Incident(final long id, final int pos, final String inc,final String created, final String type,final int status) {
		this.id = id;
		this.position = pos;
		this.incident = inc;
		this.created = created;
		this.type = type;
		this.status = status;
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
