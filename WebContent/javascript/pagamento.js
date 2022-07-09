function checkNamesurname(inputtxt) {
	var name = /^[A-Za-z]+$/;;
	if(inputtxt.value.match(name)) 
		return true

	return false;	
}


function checkCarta(inputtxt){
	var cartaPat = /^([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})$/;
	return inputtxt.value.match(cartaPat)
}


function checkScadenza(inputtxt){
	var scadenza = new Date(inputtxt.value);
	var oggi = new Date();
	return scadenza > oggi;
}

function checkCVV(inputtxt){
	var cvvPat = /^[0-9]{3,4}$/;
	return inputtxt.value.match(cvvPat)
}

function validate(obj) {	
	var valid = true;	
	var name = document.getElementsByName("nome")[0];
	var nameDetails = name.parentElement.getElementsByClassName("details")[0];

	if(!checkNamesurname(name)) {
		valid = false;
		name.classList.remove("background-white");
		nameDetails.innerHTML = " Nome<span class=errortext>nome non valido</span>"
		name.classList.add("error");
	} else {
		nameDetails.innerHTML = "Nome";
		name.classList.remove("error");
		name.classList.add("background-white");
	}
	
	var surname = document.getElementsByName("cognome")[0];
	var surnameDetails = surname.parentElement.getElementsByClassName("details")[0];

	if(!checkNamesurname(surname)) {
		valid = false;
		surname.classList.remove("background-white");
		surnameDetails.innerHTML = "Cognome <span class=errortext>Cognome non valido</span>"
		surname.classList.add("error");
	} else {
		surnameDetails.innerHTML = "Cognome";
		surname.classList.remove("error");
		surname.classList.add("background-white");
	}
	var carta = document.getElementsByName("numeroCarta")[0];
	var cartaDetails = carta.parentElement.getElementsByClassName("details")[0];

	if(!checkCarta(carta)) {
		valid = false;
		carta.classList.remove("background-white");
		cartaDetails.innerHTML = "Numero Carta <span class=errortext>Numero Carta Non Valido</span>"
		carta.classList.add("error");
	} else {
		cartaDetails.innerHTML = "Numero Carta";
		carta.classList.remove("error");
		carta.classList.add("background-white");
	}
	
	var cvv = document.getElementsByName("cvv")[0];
	var cvvDetails = cvv.parentElement.getElementsByClassName("details")[0];
	
	if(!checkCVV(cvv)) {
		valid = false;
		cvv.classList.remove("background-white");
		cvvDetails.innerHTML = "CVV <span class=errortext>CVV Non Valido</span>"
		cvv.classList.add("error");
	} else {
		cvvDetails.innerHTML = "CVV";
		cvv.classList.remove("error");
		cvv.classList.add("background-white");
	}
	
	var scadenza = document.getElementsByName("dataScadenza")[0];
	var scadenzaDetails = scadenza.parentElement.getElementsByClassName("details")[0];
	
	if(!checkScadenza(scadenza)) {
		valid = false;
		scadenza.classList.remove("background-white");
		scadenzaDetails.innerHTML = "Data Di Scadenza <span class=errortext>La Carta e' Scaduta</span>"
		scadenza.classList.add("error");
	} else {
		scadenzaDetails.innerHTML = "CVV";
		scadenza.classList.remove("error");
		scadenza.classList.add("background-white");
	}
	
	
	
	if(valid) obj.submit();
}