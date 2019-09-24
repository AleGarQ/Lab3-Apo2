package model;

import java.util.*;
import exceptions.RepeatedName;
import exceptions.NameNotFound;

public class Controller {
	private ArrayList<Clan> clans;

	public Controller() {
		clans = new ArrayList<Clan>();
	}

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public void setClans(ArrayList<Clan> clans) {
		this.clans = clans;
	}
	
	public void addClan(Clan clan) throws RepeatedName {
		boolean added = false;
		
		if(!equalName(clan.getName()) && !added) {
			clans.add(clan);
			added = true;
		}else {
			throw new RepeatedName("EL NOMBRE DEL CLAN YA ESTA EN USO");
		}
	}
	
	public boolean equalName(String name) {
		boolean same = false;
		
		for(int i = 0; i < clans.size() && !same; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(name)) {
				same = true;
			}
		}
		return same;
	}
	
	public void addNinja(String clanName, Ninja nwNinja) throws RepeatedName, NameNotFound {
		boolean found = false;
		
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				found = true;
				clans.get(i).addNinja(nwNinja);
			}
		}
		
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}
	
	public void addTechnique(String clanName, String ninjaName, Technique nwTechnique) throws RepeatedName, NameNotFound{
		boolean found = false;
		
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				found = true;
				clans.get(i).addTechnique(ninjaName, nwTechnique);
			}
		}
		
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}
	
	public void deleteClan(String name) throws NameNotFound{
		boolean found = false;
		
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(name)) {
				found = true;
				clans.remove(i);
			}
		}
		
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public void deleteNinja(String clanName, String ninjaName) throws NameNotFound {
		boolean found = false;
		
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				found = true;
				clans.get(i).deleteNinja(ninjaName);
			}
		}
		
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public void deleteTechnique(String clanName, String ninjaName, String ninjaTechnique) throws NameNotFound {
		boolean found = false;
		
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				found = true;
				clans.get(i).deleteTechnique(ninjaName, ninjaTechnique);
			}
		}
	}

	public void sortClansByName() {
		Clan aux = null;
		
		for (int i = 1; i < clans.size(); i++) {
			for(int j = 0; j < clans.size()-i; j++) {
				Clan lesser = clans.get(j);
				if(clans.get(j).compare(lesser, clans.get(j+1)) > 0) {
					aux = lesser;
					lesser = clans.get(j+1);
					clans.set(j, lesser);
					clans.set(j+1, aux);
				}
			}
		}
	}
	
	public String showSortedInfo() {		
		String msg = "";
		
		for(int i = 0; i < clans.size();i++) {
			msg = "\n " + clans.get(i).toString();
		}
		
		return msg;
	}
}
