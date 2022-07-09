package bean;

import java.sql.Date;

public class PagamentoBean {
	private Integer idPagamento;
	private Date dataScadenza;
	private Integer cvv;
	private String nome;
	private String cognome;
	private String numeroCarta;
	private String username;
	public Integer getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public Integer getCvv() {
		return cvv;
	}
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "PagamentoBean [idPagamento=" + idPagamento + ", dataScadenza=" + dataScadenza + ", cvv=" + cvv
				+ ", nome=" + nome + ", cognome=" + cognome + ", numeroCarta=" + numeroCarta + ", username=" + username
				+ "]";
	}	
}
