package it.tesi.java.accessi.model;

public class Contatto {
	private int id;
	private String Utente;
	private String Evento;
	private String Data;
	private String Orario;
	
	public Contatto() {
		super();
	}


	public Contatto(String Utente, String Evento, String Data,String Orario) {
		super();
		this.Utente = Utente;
		this.Evento = Evento;
		this.Data = Data;
		this.Orario = Orario;
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
	 * @return the Utente
	 */
	public String getUtente() {
		return Utente;
	}


	/**
	 * @param Utente the Utente to set
	 */
	public void setUtente(String Utente) {
		this.Utente = Utente;
	}


	/**
	 * @return the Evento
	 */
	public String getEvento() {
		return Evento;
	}


	/**
	 * @param Evento the Evento to set
	 */
	public void setEvento(String Evento) {
		this.Evento = Evento;
	}


	/**
	 * @return the Data
	 */
	public String getData() {
		return Data;
	}

	/**
	 * @return the Orario
	 */
	public String getOrario() {
		return Orario;
	}

	


	/**
	 * @param Data the Data to set
	 */
	public void setData(String Data) {
		this.Data = Data;
	}

	/**
	 * @param Data the Data to set
	 */
	public void setOrario(String Orario) {
		this.Orario = Orario;
	}
	
	
}
