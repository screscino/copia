package it.tesi.java.accessi.model;

public class ContattoBustepaga {
	private int id;
	private String Nome;
	private String Cognome;
	private String Codice;
	private String Retribuzione;
	private String Oremensili;
	private String Sistemaorario;
	private String Sistemamensilizzato;
	private String Straordinari;
	private String Assenze;
	private String Mensilita;
	private String Anno;
	private String Iban;
	private String Datapagamento;
	private String Stato;
	private String Permessi;
	private String Note;
	private String Acconto;

	
	
	public ContattoBustepaga() {
		super();
	}


	public ContattoBustepaga(String Nome, String Cognome, String Codice,String Retribuzione,String Oremensili,String Sistemaorario,String Sistemamensilizzato,String Straordinari,String Assenze,String Mensilita,String Anno ,String Iban ,String Datapagamento  ,String Stato,String Permessi,String Note,String Acconto ) {
		super();
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Codice = Codice;
		this.Retribuzione = Retribuzione;
		this.Oremensili = Oremensili;
		this.Sistemaorario = Sistemaorario;
		this.Sistemamensilizzato = Sistemamensilizzato;
		this.Straordinari = Straordinari;
		this.Assenze = Assenze;
		this.Mensilita = Mensilita;
		this.Anno = Anno;
		this.Iban = Iban;
		this.Datapagamento = Datapagamento;
		this.Stato = Stato;
		this.Permessi = Permessi;
		this.Note = Note;
		this.Acconto = Acconto;
		
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
	 * @return the Retribuzione
	 */
	public String getRetribuzione() {
		return Retribuzione;
	}

	public String getOremensili() {
		return Oremensili;
	}
	
	public String getSistemaorario() {
		return Sistemaorario;
	}
	
	public String getSistemamensilizzato() {
		return Sistemamensilizzato;
	}
	public String getStraordinari() {
		return Straordinari;
	}
	
	public String getAssenze() {
		return Assenze;
	}
	public String getMensilita() {
		return Mensilita;
	}
	public String getAnno() {
		return Anno;
	}
	public String getIban() {
		return Iban;
	}
	public String getDatapagamento() {
		return Datapagamento;
	}
	public String getStato() {
		return Stato;
	}
	public String getPermessi() {
		return Permessi;
	}
	public String getNote() {
		return Note;
	}
	public String getAcconto() {
		return Acconto;
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
	public void setRetribuzione(String Retribuzione) {
		this.Retribuzione = Retribuzione;
	}
	public void setOremensili(String Oremensili) {
		this.Oremensili = Oremensili;
	}
	public void setSistemaorario(String Sistemaorario) {
		this.Sistemaorario = Sistemaorario;
	}
	public void setSistemamensilizzato(String Sistemamensilizzato) {
		this.Sistemamensilizzato = Sistemamensilizzato;
	}
	public void setStraordinari(String Straordinari) {
		this.Straordinari = Straordinari;
	}
	public void setAssenze(String Assenze) {
		this.Assenze = Assenze;
	}
	public void setMensilita(String Mensilita) {
		this.Mensilita = Mensilita;
	}
	
	public void setAnno(String Anno) {
		this.Anno = Anno;
	}
	public void setIban(String Iban) {
		this.Iban = Iban;
	}
	public void setDatapagamento(String Datapagamento) {
		this.Datapagamento = Datapagamento;
	}
	public void setStato(String Stato) {
		this.Stato = Stato;
	}
	public void setPermessi(String Permessi) {
		this.Permessi = Permessi;
	}
	public void setNote(String Note) {
		this.Note = Note;
	}
	public void setAcconto(String Acconto) {
		this.Acconto = Acconto;
	}
	
}
