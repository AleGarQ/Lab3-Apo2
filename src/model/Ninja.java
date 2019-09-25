package model;

import java.io.*;
import exceptions.NameNotFound;
import exceptions.RepeatedName;

public class Ninja implements Serializable, Comparable<Ninja>{
	private static final long serialVersionUID = 1L;
	private String name;
	private String personality;
	private String builtDate;
	private double power;
	private double score;
	private Technique firstTechnique;
	private Ninja next;
	private Ninja previous;
	
	public Ninja(String name, String personality, String builtDate, double score, Technique firstTechnique, Ninja next,
			Ninja previous) {
		this.name = name;
		this.personality = personality;
		this.builtDate = builtDate;
		this.score = score;
		this.firstTechnique = firstTechnique;
		this.next = next;
		this.previous = previous;
		this.power = setRealPower();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getBuiltDate() {
		return builtDate;
	}

	public void setBuiltDate(String builtDate) {
		this.builtDate = builtDate;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public Technique getFirstTechnique() {
		return firstTechnique;
	}

	public void setFirstTechnique(Technique firstTechnique) {
		this.firstTechnique = firstTechnique;
	}

	public Ninja getNext() {
		return next;
	}

	public void setNext(Ninja next) {
		this.next = next;
	}

	public Ninja getPrevious() {
		return previous;
	}

	public void setPrevious(Ninja previous) {
		this.previous = previous;
	}
	
	public double setRealPower() {
		return power = (getScore() * firstTechnique.getFactor());
	}
	
	public void addTechnique(Technique nwTechnique) throws RepeatedName{
		boolean added = false;
		
		if(!equalName(nwTechnique.getName()) && !added) {
			if (firstTechnique == null) {
				firstTechnique = nwTechnique;
			} else {
				nwTechnique.setNext(firstTechnique);
				firstTechnique = nwTechnique;
			}
			added = true;
		}else {
			throw new RepeatedName("EL NOMBRE DE LA TECNICA YA ESTA EN USO");
		}
	}
		
	public boolean equalName(String name) {
		Technique aux = firstTechnique;
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

	public void deleteTechnique(String ninjaTechnique) throws NameNotFound {
		Technique aux = firstTechnique;
		boolean found = false;
		
		if(aux != null) {
			while(aux.getNext() != null && !found) {
				if(aux.getNext().getName().equalsIgnoreCase(ninjaTechnique)) {
					found = true;
					Technique next = aux.getNext().getNext();
					aux.setNext(next);
					aux = next;
				}
				aux = aux.getNext();
			}
		}
		if(found == false) {
			throw new NameNotFound("EL PERSONAJE NO HA SIDO ENCONTRADO, REVISE SI EL NOMBRE DIGITADO ES CORRECTO");
		}		
	}
	
	public String toString() {
		String msg = "";
		
		msg += "El nombre del personaje es: " + getName() + " || ";
		msg += "La personalidad del personaje es: " + getPersonality() + " || ";
		msg += "La fecha de creacion del personaje es: " + getBuiltDate() + " || ";
		msg += "El poder del personaje es: " + getPower() + " || ";
		msg += "El puntaje del personaje es: " + getScore() + " || ";
		
		return msg;
	}

	@Override
	public int compareTo(Ninja aux) {
		return getName().compareToIgnoreCase(aux.getName());
	}

	public void sortTechniques() throws NameNotFound {
		Technique higher = null;
		int pos = 0;

		for(int i = 0; i < techniqueLenght(); i++) {
			Technique lesser = elementInTechnique(i);
			for(int j = i + 1; j < techniqueLenght(); j++) {
				higher = elementInTechnique(j);
				
				if(higher.compareTo(lesser) < 0) {
					lesser = higher;	 
					pos = j;
			 }
		 }
			Technique aux = new Technique(lesser.getName(), lesser.getFactor(), null);
			Technique aux2 = new Technique(elementInTechnique(i).getName(), elementInTechnique(i).getFactor(), null);
			
			if(elementInTechnique(i) == firstTechnique) {
				deleteTechnique(aux2.getName());
				addAtStart(aux);
				modifyPos(pos, aux2);
			}else {
				insert(lesser.getName(), aux2);
				deleteTechnique(lesser.getName());
				modifyPos(i, lesser);
			}
	 }
	}

	private void insert(String name, Technique aux2) {
		Technique aux = firstTechnique;
		Technique auxPrev = null;
		boolean inserted = false;
		
		while(aux != null && !inserted) {
			if(aux.getName().compareTo(name) == 0) {
				auxPrev = aux;
				inserted = true;
			}else {
				aux = aux.getNext();
			}
		}
		
		Technique auxNext = auxPrev.getNext();
		auxPrev.setNext(aux2);
		aux2.setNext(auxNext);
	}

	private void modifyPos(int pos, Technique aux2) {
		Technique aux;
		Technique auxPrev;
		Technique auxNext;
		
		if(pos == 0) {
			aux = firstTechnique;
			auxPrev = null;
			auxNext = aux.getNext();
			firstTechnique = aux2;
			aux2.setNext(auxNext);
		}else if(pos != techniqueLenght()-1) {
			aux = elementInTechnique(pos);
			auxPrev = elementInTechnique(pos - 1);
			auxNext = aux.getNext();
			
			auxPrev.setNext(aux2);
			aux2.setNext(auxNext);
		}else if(pos == techniqueLenght() -1) {
			auxNext = null;
			aux = elementInTechnique(pos);
			auxPrev = elementInTechnique(pos - 1);
			auxPrev.setNext(aux2);
			aux2.setNext(auxNext);
		}
	}

	private void addAtStart(Technique tec) {
		Technique aux = firstTechnique;
		
		if(firstTechnique == null) {
			firstTechnique = tec;
		}else {
			tec.setNext(aux);
			firstTechnique = tec;
		}
	}

	private Technique elementInTechnique(int pos) {
		Technique aux = firstTechnique;
		
		if(pos != 0) {
			for(int i = 0; i < pos; i++) {
				aux = aux.getNext();
			}
		}else {
			aux = firstTechnique;
		}
		return aux;
	}

	private int techniqueLenght() {
		int lenght = 0;
		Technique aux = firstTechnique;
		
		while(aux != null) {
			lenght++;
			aux = aux.getNext();
		}
		
		return lenght;
	}

	public String showTechniquesInfo() {
		String msg = "";
		Technique aux = firstTechnique;
		
		if(aux == null) {
			msg = "No tiene tecnicas";
		}else {
			while(aux != null) {
				
				msg += aux.toString() + "\n";
				aux = aux.getNext();	
			}
		}
		return msg;
	}
	
}
