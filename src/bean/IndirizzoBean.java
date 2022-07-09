package bean;

public class IndirizzoBean {
	private Integer idIndirizzo;
	private Integer numerocivico;
	private String  citta;
	private Integer cap;
	private String username;
	private String via;
	public Integer getIdIndirizzo() {
		return idIndirizzo;
	}
	public void setIdIndirizzo(Integer idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public Integer getNumerocivico() {
		return numerocivico;
	}
	public void setNumerocivico(Integer numerocivico) {
		this.numerocivico = numerocivico;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public Integer getCap() {
		return cap;
	}
	public void setCap(Integer cap) {
		this.cap = cap;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	@Override
	public String toString() {
		return "IndirizzoBean [idIndirizzo=" + idIndirizzo + ", numerocivico=" + numerocivico + ", citta=" + citta
				+ ", cap=" + cap + ", username=" + username + ", via=" + via + "]";
	}
	
}
