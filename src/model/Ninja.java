package model;

import exceptions.NameNotFound;
import exceptions.RepeatedName;

public class Ninja {
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
		this.power = score * firstTechnique.getFactor();
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
	
}
