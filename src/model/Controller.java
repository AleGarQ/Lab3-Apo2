package model;

import java.io.*;
import java.util.*;
import exceptions.RepeatedName;
import exceptions.NameNotFound;

public class Controller implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Clan> clans;
	private String files;

	public Controller(String files) {
		clans = new ArrayList<Clan>();
		this.files = files;
		deserialize();
	}

	private void deserialize() {
		ArrayList<Clan> clans;
		
		try {
			File newFile = new File(files);
				
			FileInputStream fi = new FileInputStream(newFile);
			ObjectInputStream oi = new ObjectInputStream(fi);
				
			clans = (ArrayList<Clan>) oi.readObject();
			setClans(clans);
				
			oi.close();
		}catch(IOException e) {
			e.getMessage();
		}catch(ClassNotFoundException e) {
			e.getMessage();
		}
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

	public void sortClans() {
		Clan aux = null;
		
		for (int i = 1; i < clans.size(); i++) {
			aux = clans.get(i);
			int j = i - 1;
			while(j >= 0 && aux.compare(aux, clans.get(j)) < 0) {
					clans.set(j+1, clans.get(j));
					j--;
			}
			clans.set(j+1, aux);
		}
	}
	
	public String showClansSortedInfo() {		
		String msg = "";
		
		for(int i = 0; i < clans.size();i++) {
			msg += "\n " + clans.get(i).toString();
			
		}
		
		return msg;
	}

	public void ninjasSorted(String clanName) throws NameNotFound {
		boolean found = false;
	
		for(int i = 0; i < clans.size(); i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				clans.get(i).sortNinjas();
				found = true;
			}
		}
			
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public String showInfo(String clanName) throws NameNotFound {
		String msg = "";
		boolean found = false;
			
		for(int i = 0; i < clans.size() && !found; i++) {	
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				msg += " " +clans.get(i).showSortedInfo();
				found = true;
			}
		}
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
		return msg;
	}

	public void serialize() {
		try {
			File newFile = new File(files);
			
			FileOutputStream fo = new FileOutputStream(newFile);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			
			oo.writeObject(clans);
			oo.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String findNinja(String clanName, String ninjaName) throws NameNotFound {
		String msg = "";
		boolean found = false;
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				msg += clans.get(i).findNinja(ninjaName);
				found = true;
			}
		}
		
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
		
		return msg;
	}

	public void techniquesSorted(String clanName, String ninjaName) throws NameNotFound {
		boolean found = false;
		
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				clans.get(i).sortTechniques(ninjaName); 
				found = true;
			}
		}
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public String showTechniquesInfo(String clanName, String ninjaName) throws NameNotFound {
		String msg = "";
		boolean found = false;
		for(int i = 0; i < clans.size() && !found; i++) {
			if(clans.get(i).getName().equalsIgnoreCase(clanName)) {
				msg += clans.get(i).showTechniquesInfo(ninjaName);
				found = true;
			}
		}
		
		if(found == false) {
			throw new NameNotFound("EL CLAN NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
		
		return msg;
	}

}
