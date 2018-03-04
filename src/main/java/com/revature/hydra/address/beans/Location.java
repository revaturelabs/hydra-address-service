package com.revature.hydra.address.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LOCATION")
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public class Location implements Activatable {

	@Id
	@Column(name = "ID")
	@SequenceGenerator(allocationSize = 1, name = "locationSeq", sequenceName = "LOCATION_SEQ")
	@GeneratedValue(generator = "locationSeq", strategy = GenerationType.SEQUENCE)
	private int ID;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "active", insertable = false)
	private boolean active;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "LOCATION")
	private List<Building> buildings;

	public Location() {
	//No arg constructor
	}

	public Location(int iD, String name, String city, String state, List<Building> buildings, boolean active) {
		super();
		ID = iD;
		this.name = name;
		this.city = city;
		this.state = state;
		this.buildings = buildings;
		this.active = active;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	@Override
	public String toString() {
		return "Location [ID = " + ID + ", name = " + name + ", city = "
				+ city + ", state = " + state + ", active = " + active
				+ ", buildings = " + buildings + "]";
	}
}