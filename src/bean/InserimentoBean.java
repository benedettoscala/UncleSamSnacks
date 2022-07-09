package bean;

public class InserimentoBean {
	private Integer ordine, prodotto;
	private Double prezzo;
	private Integer iva;
	private Integer quantita;
	private String nomeProdotto;
	private String username, urlImmagine;
	
	
	
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public Integer getOrdine() {
		return ordine;
	}
	public void setOrdine(Integer ordine) {
		this.ordine = ordine;
	}
	public Integer getProdotto() {
		return prodotto;
	}
	public void setProdotto(Integer prodotto) {
		this.prodotto = prodotto;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public Integer getIva() {
		return iva;
	}
	public void setIva(Integer iva) {
		this.iva = iva;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	@Override
	public String toString() {
		return "InserimentoBean [ordine=" + ordine + ", prodotto=" + prodotto + ", prezzo=" + prezzo + ", iva=" + iva
				+ ", quantita=" + quantita + ", nomeProdotto=" + nomeProdotto + ", username=" + username
				+ ", urlImmagine=" + urlImmagine + "]";
	}

}
