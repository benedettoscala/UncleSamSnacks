function aggiungiAlCarrello(idProdotto){
	$.get('CarrelloControl', {"azione":"aggiungi", "quantita":"1", "id":idProdotto}, function(data,status){
		$("#aggiunto").addClass("show")
		setTimeout(reactivate, 3000)
	})
	
}

function reactivate()
{
	$("#aggiunto").removeClass("show")
}


function eliminato(idProdotto) {
	  var x = document.getElementById("eliminato");
	  x.className = "eshow";
	  x.children[0].href = "EliminaProdottoControl?id=" + idProdotto;
	  setTimeout(function(){ x.className = x.className.replace("eshow", ""); }, 5000);
}