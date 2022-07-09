package bean;

public class ProdottoBean {

	private int idProdotto;
	private String azienda;
	private String categoria;
	private String nome;
	private double prezzo;
	private String descrizione;
	private String urlImmagine;
	private int quantita;
	private int sconto;
	private int IVA;

	public int getIVA() {
		return IVA;
	}
	public void setIVA(int iVA) {
		IVA = iVA;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getAzienda() {
		return azienda;
	}
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getPrezzoIva() {
		return Double.parseDouble((String.format("%,.2f", ((prezzo *(100+IVA))/100)).replace(',', '.')));
	}
	
	public double getPrezzoReale()
	{
		Double prezzoIVA = (prezzo *(100+IVA))/100;
		
		return Double.parseDouble((String.format("%,.2f", (prezzoIVA * (100-sconto))/100)).replace(',', '.'));
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	@Override
	public String toString() {
		return "ProdottoBean [idProdotto=" + idProdotto + ", azienda=" + azienda + ", categoria=" + categoria
				+ ", nome=" + nome + ", prezzo=" + prezzo + ", descrizione=" + descrizione + ", urlImmagine="
				+ urlImmagine + ", quantita=" + quantita + ", sconto=" + sconto + "]";
	}
}
