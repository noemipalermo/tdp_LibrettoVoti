package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	public static void main(String[] args) {

		Libretto lib = new Libretto();
		lib.add(new Voto("Analisi I", 29, LocalDate.of(2022, 02, 15)));
		lib.add(new Voto("Fisica II", 18, LocalDate.of(2022, 06, 21)));
		lib.add(new Voto("Fisica III", 18, LocalDate.of(2022, 02, 21)));
		lib.add(new Voto("Informatica", 26, LocalDate.of(2022, 02, 21)));
		
		System.out.println("Tutti gli esami passati:");
//		lib.stampa();
		
		System.out.println("Esami con voto  29:");
		lib.stampaPuntiUguali(29);
		
		System.out.println("Esami con nome 'Analisi I':");
		Voto v = lib.cercaVotoPerNome("Analisi I");
		System.out.println(v);
		
		
		System.out.println("Esiste esame:");
		Voto aIbis = new Voto("Analisi I", 29, LocalDate.of(2022, 02, 15));
		System.out.println(aIbis  +" è duplicato: "+ lib.esisteVotoDuplicato(aIbis));
		Voto aIter = new Voto("Analisi I", 18, LocalDate.of(2022, 02, 15));
		System.out.println(aIter  +" è duplicato: "+ lib.esisteVotoDuplicato(aIter));
		
		try {
		lib.add(new Voto("Informatica", 27, LocalDate.of(2022, 02, 9)));
		}catch(IllegalArgumentException e){
			System.out.println("Errore nell'inserimento del voto.");
			System.out.println(e.getMessage());
		}
		
		Libretto migliore = lib.librettoMigliorato();
		
		System.out.println("LIBRETTO MIGLIORATO");
//		migliore.stampa();
		System.out.println("LIBRETTO ORIGINARIO");
//		lib.stampa();
		
//		lib.cancellaVotiInferiori(20);
//		System.out.println("LIBRETTO CON VOTI RIMOSSI");
//		lib.stampa();
		
		
		System.out.println("LIBRETTO ORDINATO ALFABETICAMENTE");
//		lib.librettoOrdinatoAlfabeticamente().stampa();
		
		
		System.out.println("LIBRETTO ORDINATO PER VOTO");
//		lib.librettoOrdinatoPerVoto().stampa();
		
	}

}
