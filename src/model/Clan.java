package model;

import java.util.*;
import exceptions.NameNotFound;
import exceptions.RepeatedName;

public class Clan implements Comparator<Clan>{
	private String name;
	private Ninja firstNinja;
	private Ninja lastNinja;
	
	public Clan(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ninja getFirstNinja() {
		return firstNinja;
	}

	public void setFirstNinja(Ninja firstNinja) {
		this.firstNinja = firstNinja;
	}
	
	public Ninja getLastNinja() {
		return lastNinja;
	}

	public void setLastNinja(Ninja lastNinja) {
		this.lastNinja = lastNinja;
	}

	public void addNinja(Ninja nwNinja) throws RepeatedName{
		boolean added = false;
		
		if(!equalName(nwNinja.getName()) && !added) {
			if (firstNinja == null) {
				firstNinja = nwNinja;
			} else {
				nwNinja.setNext(firstNinja);
				firstNinja = nwNinja;
			}
			added = true;
		}else {
			throw new RepeatedName("EL NOMBRE DEL PERSONAJE YA ESTA EN USO");
		}
	}
		
	public boolean equalName(String name) {
		Ninja aux = firstNinja;
		boolean same = false;
		
		if(aux != null) {
			while(aux.getNext() != null && !same) {
				if(aux.getName().equalsIgnoreCase(name)) {
					same = true;
				}
				aux = aux.getNext();
			}
		}
		return same;
	}
	
	public void addTechnique(String ninjaName, Technique nwTechnique) throws RepeatedName, NameNotFound{
		Ninja aux = firstNinja;
		boolean found = false;
		
		if(aux != null) {
			while(aux.getNext() != null && !found) {
				if(aux.getName().equalsIgnoreCase(ninjaName)) {
					found = true;
					aux.addTechnique(nwTechnique);
				}
				aux = aux.getNext();
			}
		}
		if(found == false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public void deleteNinja(String ninjaName) throws NameNotFound{
		Ninja aux = firstNinja;
		boolean found = false;
		
		if(aux != null) {
			while(aux.getNext() != null && !found) {
				if(aux.getName().equalsIgnoreCase(ninjaName)) {
					found = true;
					Ninja previous = aux.getPrevious();
					Ninja next = aux.getNext();
					previous.setNext(next);
					next.setPrevious(previous);
					aux = next;
				}
				aux = aux.getNext();
			}
		}
		if(found == false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public void deleteTechnique(String ninjaName, String ninjaTechnique) throws NameNotFound {
		Ninja aux = firstNinja;
		boolean found = false;
		
		if(aux != null) {
			while(aux.getNext() != null && !found) {
				if(aux.getName().equalsIgnoreCase(ninjaName)) {
					found = true;
					aux.deleteTechnique(ninjaTechnique);
				}
				aux = aux.getNext();
			}
		}
		if(found == false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}
	
	@Override
	public int compare(Clan lesser, Clan clan) {
		return lesser.getName().compareToIgnoreCase(clan.getName());
	}
	
	public String toString() {
		String msg = "";
		
		msg += "El nombre del clan es: " + getName() + " || ";

		return msg;
	}
}
