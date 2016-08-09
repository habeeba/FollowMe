package com.findme.application.Models;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Ahmed on 3/28/2016.
 */
public class AgendaEntity {
	private String agendaID;
	private String ownter;
	private String lastUpdate;
	private String type;
	private String AgendaForShow;
	private String OlastUpdate;
	
	public String getOlastUpdate() {
		return OlastUpdate;
	}

	public void setOlastUpdate(String olastUpdate) {
		OlastUpdate = olastUpdate;
	}

	private ArrayList<String> cources;

	public ArrayList<String> getCources() {
		return cources;
	}

	public void setCources(ArrayList<String> cources) {
		this.cources = cources;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getAgendaForShow() {
		return AgendaForShow;
	}

	public void setAgendaForShow(String agendaForShow) {
		AgendaForShow = agendaForShow;
	}

	public AgendaEntity getInstace() {
		return this;
	}

	// it should be private becouces it's singlton
	public AgendaEntity(String agendaID, String owner, String lastUpdate,
			String type) {
		super();
		this.agendaID = agendaID;
		this.ownter = owner;
		this.lastUpdate = lastUpdate;
		this.type = type;
	}

	public AgendaEntity() {

	}

	public String getAgendaID() {
		return agendaID;
	}

	public String getOwnter() {
		return ownter;
	}

	public String getType() {
		return type;
	}

	public void setAgendaID(String agendaID) {
		this.agendaID = agendaID;
	}

	public void setOwnter(String ownter) {
		this.ownter = ownter;
	}

	public void setType(String type) {
		this.type = type;
	}

}
