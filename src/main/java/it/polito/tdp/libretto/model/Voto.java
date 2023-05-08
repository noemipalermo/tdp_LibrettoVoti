package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class Voto{ //aggiungo implements Comparator<Voto> se vi è un solo metodo di ordinamento
	private String nomeCorso;
	private int punti; // da 18 a 31ù
	private LocalDate dataEsame;


	public Voto(String nomeCorso, int punti, LocalDate dataEsame) {
		super();
		this.nomeCorso = nomeCorso;
		this.punti = punti;
		this.dataEsame = dataEsame;
	}
	
	//Copy constructor di voto	
	public Voto(Voto v) {
		this.nomeCorso = v.nomeCorso;
		this.punti = v.punti;
		this.dataEsame = v.dataEsame;
	}
	public String getNomeCorso() {
		return nomeCorso;
	}


	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}


	public int getPunti() {
		return punti;
	}


	public void setPunti(int punti) {
		this.punti = punti;
	}


	public LocalDate getDataEsame() {
		return dataEsame;
	}


	public void setDataEsame(LocalDate dataEsame) {
		this.dataEsame = dataEsame;
	}


	@Override
	public String toString() {
		return nomeCorso  + "("+ punti +" pt) il " + dataEsame ;
	}

	public boolean isDuplicato(Voto altro) {
		return this.getNomeCorso().equals(altro.getNomeCorso()) &&
				this.getPunti() == altro.getPunti();
	}
	
	public boolean isConflitto(Voto altro) {
		return this.getNomeCorso().equals(altro.getNomeCorso()) &&
				this.getPunti() != altro.getPunti();
		
	}

	
	public Voto clone() {
	
		return new Voto(this.nomeCorso, this.punti, this.dataEsame);
	}
	
	
}
