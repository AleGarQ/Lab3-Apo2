package model;

import java.io.*;

public class Technique implements Serializable, Comparable<Technique>{
	private static final long serialVersionUID = 1L;
	private String name;
	private double factor;
	private Technique next;
	
	public Technique(String name, double factor, Technique next) {
		this.name = name;
		this.factor = factor;
		this.next = next;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public Technique getNext() {
		return next;
	}

	public void setNext(Technique next) {
		this.next = next; 
	}

	@Override
	public String toString() {
		String msg = "";
		msg += "El nombre de la tecnica es: " + getName() + " || ";
		msg += "El factor de la tecnica es: " + getFactor() + " || ";
		return msg;
	}
	
	@Override
	public int compareTo(Technique lesser) {
		return name.compareTo(lesser.getName());
	}
	
	
}
