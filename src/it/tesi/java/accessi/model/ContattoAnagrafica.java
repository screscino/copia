package it.tesi.java.accessi.model;

public class ContattoAnagrafica {
	private int id;
	private String Nome;
	private String Cognome;
	private String Codice;
	private String Ruolo;
	private String Cellulare;
	private String Luogodinascita;
	private String Datadinascita;
	private String Genere;
	private String Codicefiscale;
	private String Indirizzo;
	private String Citta;
	private String Provincia;
	private String Cap;
	private String Stato;
	private String Email;
	private String Note;
	private String Istruzione;

	
	
	public ContattoAnagrafica() {
		super();
	}


	public ContattoAnagrafica(String Nome, String Cognome, String Codice,String Ruolo,String Cellulare,String Luogodinascita,String Datadinascita,String Genere,String Codicefiscale,String Indirizzo,String Citta ,String Provincia ,String Cap  ,String Stato,String Email,String Note,String Istruzione ) {
		super();
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Codice = Codice;
		this.Ruolo = Ruolo;
		this.Cellulare = Cellulare;
		this.Luogodinascita = Luogodinascita;
		this.Datadinascita = Datadinascita;
		this.Genere = Genere;
		this.Codicefiscale = Codicefiscale;
		this.Indirizzo = Indirizzo;
		this.Citta = Citta;
		this.Provincia = Provincia;
		this.Cap = Cap;
		this.Stato = Stato;
		this.Email = Email;
		this.Note = Note;
		this.Istruzione = Istruzione;
		
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the Nome
	 */
	public String getNome() {
		return Nome;
	}


	/**
	 * @param Nome the Nome to set
	 */
	public void setNome(String Nome) {
		this.Nome = Nome;
	}


	/**
	 * @return the Cognome
	 */
	public String getCognome() {
		return Cognome;
	}


	/**
	 * @param Cognome the Cognome to set
	 */
	public void setCognome(String Cognome) {
		this.Cognome = Cognome;
	}


	/**
	 * @return the Codice
	 */
	public String getCodice() {
		return Codice;
	}

	/**
	 * @return the Ruolo
	 */
	public String getRuolo() {
		return Ruolo;
	}

	public String getCellulare() {
		return Cellulare;
	}
	
	public String getLuogodinascita() {
		return Luogodinascita;
	}
	
	public String getDatadinascita() {
		return Datadinascita;
	}
	public String getGenere() {
		return Genere;
	}
	
	public String getCodicefiscale() {
		return Codicefiscale;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public String getCitta() {
		return Citta;
	}
	public String getProvincia() {
		return Provincia;
	}
	public String getCap() {
		return Cap;
	}
	public String getStato() {
		return Stato;
	}
	public String getEmail() {
		return Email;
	}
	public String getNote() {
		return Note;
	}
	public String getIstruzione() {
		return Istruzione;
	}
	


	/**
	 * @param Codice the Codice to set
	 */
	public void setCodice(String Codice) {
		this.Codice = Codice;
	}

	/**
	 * @param Codice the Codice to set
	 */
	public void setRuolo(String Ruolo) {
		this.Ruolo = Ruolo;
	}
	public void setCellulare(String Cellulare) {
		this.Cellulare = Cellulare;
	}
	public void setLuogodinascita(String Luogodinascita) {
		this.Luogodinascita = Luogodinascita;
	}
	public void setDatadinascita(String Datadinascita) {
		this.Datadinascita = Datadinascita;
	}
	public void setGenere(String Genere) {
		this.Genere = Genere;
	}
	public void setCodicefiscale(String Codicefiscale) {
		this.Codicefiscale = Codicefiscale;
	}
	public void setIndirizzo(String Indirizzo) {
		this.Indirizzo = Indirizzo;
	}
	
	public void setCitta(String Citta) {
		this.Citta = Citta;
	}
	public void setProvincia(String Provincia) {
		this.Provincia = Provincia;
	}
	public void setCap(String Cap) {
		this.Cap = Cap;
	}
	public void setStato(String Stato) {
		this.Stato = Stato;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public void setNote(String Note) {
		this.Note = Note;
	}
	public void setIstruzione(String Istruzione) {
		this.Istruzione = Istruzione;
	}
	
}
