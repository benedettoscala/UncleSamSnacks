package bean;

import java.util.Map;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class Carrello {
	private Map<ProdottoBean, Integer> carrello;
	
	public Carrello() {
		carrello = new HashMap<>();
	}
	
	public void aggiungiProdotto(ProdottoBean daAggiungere, Integer quantita) {
		for(ProdottoBean prodotto: carrello.keySet())
		{
			if( prodotto.getIdProdotto() == daAggiungere.getIdProdotto()) {
				carrello.put(daAggiungere, quantita);
				carrello.remove(prodotto);
				return;
			}
		}
		carrello.put(daAggiungere, quantita);
	}
	
	public void rimuoviProdotto(ProdottoBean daRimuovere) {
		try {
		for(ProdottoBean prodotto: carrello.keySet())
		{
			if( prodotto.getIdProdotto() == daRimuovere.getIdProdotto()) {
				carrello.remove(prodotto);
			}
		}
		} catch(ConcurrentModificationException e) {
			System.out.println("bello");
		}
	}
	
	public void modificaProdotto(ProdottoBean prodottoDaModificare, Integer quantitaDaAggiungere)
	{
		for(ProdottoBean prodotto: carrello.keySet())
		{
			if( prodotto.getIdProdotto() == prodottoDaModificare.getIdProdotto()) {
				int quantita = carrello.get(prodotto);
				carrello.put(prodotto, quantita+quantitaDaAggiungere);
				
			}
		}
	}
	
	public int restituisciQuantita(ProdottoBean prodotto)
	{
		int quantita = 0;
		for(ProdottoBean prdNelCarrello: carrello.keySet())
		{
			if( prdNelCarrello.getIdProdotto() == prodotto.getIdProdotto()) {
				quantita = carrello.get(prdNelCarrello);
				
			}
		}
		return quantita;
	}
	public Map<ProdottoBean, Integer> restituisciCarrello()
	{
		return carrello;
	}
	
	public double getTotale()
	{
		Carrello car = this;
		Double subTotale= 0.0;
		if(car != null && car.restituisciCarrello().size()!=0)
		{	
			for(Map.Entry<ProdottoBean, Integer> pair: car.restituisciCarrello().entrySet())
			{
				ProdottoBean prodotto = pair.getKey();
				
				subTotale += (prodotto.getPrezzoReale()*pair.getValue());
			}
		}
		
		return Double.parseDouble((String.format("%,.2f", subTotale)).replace(',', '.'));
	}
}
