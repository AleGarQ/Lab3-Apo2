package model;

import java.io.*;
import java.util.*;
import exceptions.NameNotFound;
import exceptions.RepeatedName;

public class Clan implements Serializable, Comparator<Clan>{
	private static final long serialVersionUID = 1L;
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
		Ninja aux2 = null;
		boolean found = false;
		
		while(aux != null && !found) {
			if(aux.getName().equalsIgnoreCase(ninjaName)) {
				found = true;
				
				if(aux2 == null) {
					firstNinja.setPrevious(null);
					aux.setNext(null);
					firstNinja = firstNinja.getNext();
					aux = firstNinja;
				}else {
					aux2.setNext(aux.getNext());
					aux.setNext(null);
					aux2.setPrevious(aux.getPrevious());
					aux.setPrevious(null);
					aux = aux2.getNext();
				}
			}else {
				aux2 = aux;
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

	public void sortNinjas() {
		boolean sortInProcess;
		if(firstNinja != null) {
			do {
				Ninja aux = firstNinja;
				Ninja aux2 = null;
				Ninja next = firstNinja.getNext();
				sortInProcess = false;
			
				while(next != null) {
					if(aux.compareTo(next) > 0) {
						sortInProcess = true;
						if(aux2 != null) {
							Ninja next2 = next.getNext();
							aux2.setNext(next);
							next.setPrevious(aux2);
							next.setNext(aux);
							aux.setPrevious(next);
							aux.setNext(next2);
						}else {
							Ninja next2 = next.getNext();
							firstNinja = next;
							next.setNext(aux);
							aux.setPrevious(next);
							aux.setNext(next2);
							next2.setPrevious(aux);
						}
						aux2= next;
						next = aux.getNext().getNext();
					}else {
						aux2 = aux;
						aux = next;
						next = next.getNext();
					}
				}
			}while(sortInProcess);
		}
	}
	
	public String showSortedInfo() {
		String msg = "";
		Ninja aux = firstNinja;
		
		while(aux != null) {
			msg += "\n" + aux.toString();
			aux = aux.getNext();
		}
		return msg;
	}

	public String findNinja(String ninjaName) throws NameNotFound {
		String msg = "";
		Ninja aux = firstNinja;
		boolean found = false;
		
		while(aux != null && !found) {
			if(aux.getName().equalsIgnoreCase(ninjaName)) {
				msg +="El personaje buscado es: "+ aux.toString();
				found = true;
			}else {
				aux = aux.getNext();
			}
		}
		
		if(found = false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
			
		return msg;
		
	}

	public void sortTechniques(String ninjaName) throws NameNotFound {
		Ninja aux = firstNinja;
		boolean found = false;
		
		while(aux != null && !found) {
			if(aux.getName().equalsIgnoreCase(ninjaName)) {
				aux.sortTechniques();
				found = true;
			}else {
				aux = aux.getNext();
			}
		}
		if(found == false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
	}

	public String showTechniquesInfo(String ninjaName) throws NameNotFound {
		String msg = "";
		Ninja aux = firstNinja;
		boolean found = false;
		
		while(aux != null && !found) {
			if(aux.getName().equalsIgnoreCase(ninjaName)) {
				msg += aux.showTechniquesInfo();
				found = true;
			}else {
				aux = aux.getNext();
			}
		}
		
		if(found = false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}
		return msg;
		
	}
}
