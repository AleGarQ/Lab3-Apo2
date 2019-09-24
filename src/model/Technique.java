package model;

public class Technique {
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
	
	
}
