$(document).ready(function(){
	var amministratore = false;
	$.get( "AmministratoreControl", function( data){
		if(data)
			amministratore =true;
	});
	function prezzoScontato(prezzo, sconto){
		var prezzoScont = (prezzo * (100-sconto))/100;
		return prezzoScont.toFixed(2);
	}

	$(".choice-card").children().click(function(){
		$(".card").remove();
		var id = $(this).attr("id");
		
		$.get( "CatalogoControl?azione=categoria&categoria="+id, function( data, status){ //fetcha dal server le informazioni dei prodotti
			console.log(data.length);
			var page =$("#page").attr("value");
			
			//pulsanti di navigazione
			if(data.length==12){
				$("#avanti").css("display","block");
				$("#avanti").attr("href", "CatalogoControl?pagina="+2+"&categoria="+id);
			} else {
				$("#avanti").css("display","none");
			}
			

				$("#indietro").css("display","none");
			//
				
				
			data.forEach(function(value){ //per ogni prodotto dal server
				//costruisci la card per il prodotto
				var buff = "<div class=\"card\">" +
								"<div class=\"card__header\">" +
									"<img class =\"card-image\" src="+value.urlImmagine+" alt=\"card__image\" class=\"card__image\" width=\"600\">" +
							"</div>" +
								"<div class=\"card__body\">" +
									"<span class=\"tag tag-red\">"+value.categoria+"</span>" +
									"<a class=\"btn_normal\" href=\"ProdottoControl?prodotto="+value.idProdotto+"\"><h4 class=\"product-name\">"+value.nome+"</h4></a>";
				if(value.sconto!=0)
								buff+="<p class=\"price\"> <span class=\"product-name-price-scontato\">"+value.prezzo+"$</span> <span>"+prezzoScontato(value.prezzo, value.sconto)+"$</span></p>";
				else				
					buff+="<p class=\"price\"> <span>"+prezzoScontato(value.prezzo, value.sconto)+"$</span></p>";
				if(amministratore){ //caso in cui l'utente è amministratore
					buff+="<b>"+
						"<a class=\"admin\" href=\"ModificaProdottoControl?id="+value.idProdotto+"\">Modifica il prodotto</a>"+
					"<br>"+
						"<a class=\"admin\"  onclick=\"eliminato("+value.idProdotto+")\" >Elimina il prodotto</a>"+
					"</b>"
				}		
				if(value.sconto!=0)				
					buff+="<span class=\"tag tag-saldo\">"+value.sconto+"% di sconto</span>"
				buff+=	"</div>" +
						"<div class=\"card__footer\">" +
							"<div class=\"user\">" +
								"<button class=\"btn_primary\" id=\"aggiungiCarrello\" onclick=\"aggiungiAlCarrello("+value.idProdotto+")\">Aggiungi al carrello</button>" +
							"<div class=\"user__info\">";
				
				
				buff+=	"</div>" +
						"</div>" +
						"</div>" +
						"</div>";				
				$(".container").append(buff);
		       
				});
			
		});
		
		
		
	})
	
	
	$("#btnMarca").click(function(){
		$(".card").remove();
		var id = $("#selectMarca").val();
		$.get( "CatalogoControl?azione=marca&marca="+id, function( data, status){
			var page =$("#page").attr("value");
			if(data.length==12){
				var buff=parseInt(page)+1;
				$("#avanti").css("display","block");
				$("#avanti").attr("href", "CatalogoControl?pagina="+(buff)+"&marca="+id);
			} else {
				$("#avanti").css("display","none");
			}
			
			if(page==1)
				$("#indietro").css("display","none");
			else{
				var buff = page-1;
				$("#indietro").css("display","block");
				$("#indietro").attr("href", "CatalogoControl?pagina="+(buff)+"&marca="+id);
			}
			data.forEach(function(value){
				var buff = "<div class=\"card\">" +
				"<div class=\"card__header\">" +
					"<img class =\"card-image\" src="+value.urlImmagine+" alt=\"card__image\" class=\"card__image\" width=\"600\">" +
			"</div>" +
				"<div class=\"card__body\">" +
					"<span class=\"tag tag-red\">"+value.categoria+"</span>" +
					"<a class=\"btn_normal\" href=\"ProdottoControl?prodotto="+value.idProdotto+"\"><h4 class=\"product-name\">"+value.nome+"</h4></a>";
					if(value.sconto!=0)
									buff+="<p class=\"price\"> <span class=\"product-name-price-scontato\">"+value.prezzo+"$</span> <span>"+prezzoScontato(value.prezzo, value.sconto)+"$</span></p>";
					else				
						buff+="<p class=\"price\"> <span>"+prezzoScontato(value.prezzo, value.sconto)+"$</span></p>";
					if(amministratore){
						buff+="<b>"+
							"<a class=\"admin\" href=\"ModificaProdottoControl?id="+value.idProdotto+"\">Modifica il prodotto</a>"+
						"<br>"+
							"<a class=\"admin\" onclick=\"eliminato("+value.idProdotto+")\">Elimina il prodotto</a>"+
						"</b>"
}		
if(value.sconto!=0)				
	buff+="<span class=\"tag tag-saldo\">"+value.sconto+"% di sconto</span>"
buff+=	"</div>" +
		"<div class=\"card__footer\">" +
			"<div class=\"user\">" +
				"<button class=\"btn_primary\" id=\"aggiungiCarrello\" onclick=\"aggiungiAlCarrello("+value.idProdotto+")\">Aggiungi al carrello</button>" +
			"<div class=\"user__info\">";


buff+=	"</div>" +
		"</div>" +
		"</div>" +
		"</div>";					
				$(".container").append(buff);
		       
				});
			
		});
	});
}
)

