package it.polito.tdp.itunes.model;

public class Arco {
	
	Track t1;
	Track t2;
	public Arco(Track t1, Track t2) {
		super();
		this.t1 = t1;
		this.t2 = t2;
	}
	public Track getT1() {
		return t1;
	}
	public void setT1(Track t1) {
		this.t1 = t1;
	}
	public Track getT2() {
		return t2;
	}
	public void setT2(Track t2) {
		this.t2 = t2;
	}
	
	

}
