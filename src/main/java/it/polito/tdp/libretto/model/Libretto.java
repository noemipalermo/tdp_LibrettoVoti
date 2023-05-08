package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.polito.tdp.libretto.db.VotoDAO;

public class Libretto {

	private List<Voto> voti;
	
	public Libretto() {
		//this.voti = new ArrayList<Voto>();
		VotoDAO dao = new VotoDAO();
		this.voti = dao.listaVoti();
	}
	/**
	 * Aggiungi un nuovo voto al libretto
	 * (per ora non fa nessun controllo)
	 * @param v il Voto da aggiungere 
	 * @return true 
	 */
	
	public boolean add(Voto v) { // perchè l'add ritorna un boolean
		
		// Controlli sul voto: esame già dato, punteggio corretto...
		
		if( this.esisteVotoConflitto(v) || this.esisteVotoDuplicato(v)) {
			//non aggiungere voto
			
//			System.out.println("Voto errato!");
			throw new IllegalArgumentException("Voto errato: "+v);
		}
		// Aggiungo il voto alla classe tramite delega, ovvero tramite la lista 

		
		VotoDAO dao = new VotoDAO();
		dao.createVoto(v);
		return this.voti.add(v); 
		
	}
	
	public String toString() {
		String txt = "";
		for (Voto v: this.voti) {
			txt = txt + v.toString()+"\n";
		}
		return txt;
	}
	
	public void stampaPuntiUguali(int valore) {
		for (Voto v: this.voti) {
			if (v.getPunti() == valore) {
				System.out.println(v);
			}
		} 
	}
	
	public Voto cercaVotoPerNome(String corso) {
		
		for (Voto v: this.voti) {
			if (v.getNomeCorso().equals(corso) == true) { // metodo di comparazione esplicito per gli oggetti: 
														 //if (v.getNomeCorso().compareTo(corso) == 0 -> (torna -1,0-1 a seconda che il secondo termine sia prima, uguale o dopo il primo) presuppone si sappia ordinare gli oggetti, equals no
				return v;
			}
		}
		return null; 
		/*se non trovo l'elemento posso tornare null oppure lanciare un'eccezione
		* in questo caso è meglio tornare null in quanto è una cosa comune che un metodo non trovi un elemento
		* da preferire l'eccezione nel caso di un eveto meno comune
		* throw new RunTimeException("Voto non trovato!");
		*/
		
	}
	
	public boolean esisteVotoDuplicato(Voto nuovo) {
		
		for (Voto v: this.voti) {
			if (v.isDuplicato(nuovo))
			{ 
				return true;
			}
		}
		return false; 
		
	}
	
	public boolean esisteVotoConflitto(Voto nuovo) {
		
		for (Voto v: this.voti) {
			if (v.isConflitto(nuovo)) 
			{ 
				return true;
			}
		}
		return false; 
		
	}
	
	
	/**
	 * Metodo di 'factory' per creare un nuovo 
	 * libretto con i voti migliorati
	 * 
	 * @return
	 */
	
	public Libretto librettoMigliorato() {
		
		Libretto migliore  = new Libretto();
//		migliore.voti = this.voti;  SBAGLIATO: sto creando un alias 
//		e non una copia che si evolve indistintamente dall'originale
	
		
		migliore.voti = new ArrayList<>(); //Nuovo oggetto libretto c
		
		for (Voto v: this.voti) { // Aggiungo al nuovo libretto i cloni dei voti con un ciclo for
			migliore.voti.add(v.clone());
		//	migliore.voti.add(new Voto(v));
			
			// Entrambi hanno lo stesso funzionamento
		}
		
		for(Voto v : migliore.voti) {
			if(v.getPunti()>= 18 && v.getPunti()<24) {
				v.setPunti(v.getPunti()+1);
			}else if (v.getPunti() >= 24) {
				v.setPunti(v.getPunti()+2);
				if (v.getPunti()>30) {
					v.setPunti(30);
				}
			}
		}
		
		return migliore;
	}
	
	public void cancellaVotiInferiori(int punti) {
// 	Non è corretto cancellare elementi un una lista che sto iterando -> CREA PROBLEMI

		
//		for(Voto v: this.voti) {
//		if(v.getPunti()<punti) {
//			this.voti.remove(v);
//		}
//	}
		
	
		
//	 	è preferibile una nuova lista con gli elementi da cancellare
		
		List<Voto> daCancellare = new ArrayList<Voto>();
		for(Voto v: this.voti) {
			if(v.getPunti()<punti) {
				daCancellare.add(v);
			}
		}
// 		e successivamente rimuovere gli elementi dalla lista originaria
		
		for(Voto v1 : daCancellare) {
			this.voti.remove(v1);
		}
		// oppure this.voti.removeAll(daCancellare);
		
	}
	
	
	public Libretto librettoOrdinatoAlfabeticamente() {
		Libretto ordinatoAlfabeticamente = new Libretto();
		ordinatoAlfabeticamente.voti = new ArrayList<>(this.voti);
		
		ordinatoAlfabeticamente.voti.sort(new ComparatorByName());
	// Collections.sort(ordinatoAlfabeticamente.voti, new ComparatorByName());
		
		return ordinatoAlfabeticamente;
	}
	
	public Libretto librettoOrdinatoPerVoto() {
		Libretto ordinatoPerVoto = new Libretto();
		ordinatoPerVoto.voti = new ArrayList<>(this.voti);
		
	//	ordinatoAlfabeticamente.voti.sort(new ComparatorByVoto());
		ordinatoPerVoto.voti.sort(new Comparator<Voto>(){

			@Override
			public int compare(Voto o1, Voto o2) {
				return o2.getPunti()-o1.getPunti();
			} 
			
		}); //new Interfaccia -> Java crea un interfaccia anonima inline con () e costruisce classe con {} 
		
		return ordinatoPerVoto;
	}
}
