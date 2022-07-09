package bean;

import java.sql.Date;
import java.sql.Time;

public class OrdineBean {
	private Integer idOrdine;
	private Date data;
	private Time ora;
	private String utente;
	private Integer idIndirizzo;
	private Integer idPagamento;
	private Double totale;
	
	
	
	public Double getTotale() {
		return totale;
	}
	public void setTotale(Double totale) {
		this.totale = totale;
	}
	public Integer getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOra() {
		return ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public Integer getIdIndirizzo() {
		return idIndirizzo;
	}
	public void setIdIndirizzo(Integer idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public Integer getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}
	@Override
	public String toString() {
		return "OrdineBean [idOrdine=" + idOrdine + ", data=" + data + ", ora=" + ora + ", utente=" + utente
				+ ", idIndirizzo=" + idIndirizzo + ", idPagamento=" + idPagamento + "]";
	}
}
