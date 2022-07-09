const nomeSulDatabase = document.getElementsByName("username")[0].value; 
const emailSulDatabase = document.getElementsByName("email")[0].value; 
function checkNamesurname(inputtxt) {
	var name = /^[A-Za-z]+$/;;
	if(inputtxt.value.match(name)) 
		return true

	return false;	
}

function checkEmail(inputtxt) {
	var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(email)) 
		return true;
	
	return false;	
}

function checkPhonenumber(inputtxt) {
	var phoneno = /^([0-9]{3}-[0-9]{7})$/;
	if(inputtxt.value.match(phoneno)) 
		return true;
	
	return false;
}

function checkPassword(firstPassword, secondPassword)
{
	return firstPassword.value == secondPassword.value;
}

function checkOtherEmails(inputtxt){
	var email = document.getElementsByName("email")[0]
	var surnameEmail = email.parentElement.getElementsByClassName("details")[0]
	var result;
	xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	xmlDoc = this.responseXML;
	    	result = xmlDoc.getElementsByTagName("result")[0].innerHTML;
	    	
	    }
	  };
	  xhttp.open("POST", "ControllaRegistrazioneControl", false);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("azione=email&email="+inputtxt.value);
	  return result == "true"

}

function checkOtherUsernames(inputtxt){
	var result;
	xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	xmlDoc = this.responseXML;
	    	result = xmlDoc.getElementsByTagName("result")[0].innerHTML;
	    	
	    }
	  };
	  xhttp.open("POST", "ControllaRegistrazioneControl", false);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("azione=username&username="+inputtxt.value);
	  return result == "true"

}

function validate(obj) {	
	var valid = true;	
	
	var name = document.getElementsByName("nome")[0];
	var nameDetails = name.parentElement.getElementsByClassName("details")[0];
	if(!checkNamesurname(name)) {
		valid = false;
		name.classList.remove("background-white");
		nameDetails.innerHTML = "Nome <span class=errortext>Nome non valido</span>"
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
	
	var email = document.getElementsByName("email")[0];
	var surnameEmail = email.parentElement.getElementsByClassName("details")[0]
	console.log(emailSulDatabase)
	console.log(email.value)
	if(!checkEmail(email)) {
		email.classList.remove("background-white");
		valid = false;
		email.classList.add("error");
		surnameEmail.innerHTML = "email <span class=errortext>Inserire un a mail valida</span>"
	} else if(!(emailSulDatabase==email.value)&&checkOtherEmails(email)){
		email.classList.remove("background-white");
		valid = false;
		email.classList.add("error");
		surnameEmail.innerHTML = "email <span class=errortext>Mail già presente sul sito</span>"
	} else {
		surnameEmail.innerHTML = "Email";
		email.classList.remove("error");
		email.classList.add("background-white");
	}
	
	var username = document.getElementsByName("username")[0];
	var usernameDetails = username.parentElement.getElementsByClassName("details")[0]
	if(!(nomeSulDatabase==username.value)&&checkOtherUsernames(username)) {
		valid = false;
		username.classList.remove("background-white");
		usernameDetails.innerHTML = "Username <span class=errortext>Username già esistente</span>"
		username.classList.add("error");
	} else {
		usernameDetails.innerHTML = "Username";
		username.classList.remove("error");
		username.classList.add("background-white");
	}
	
	var firstPassword = document.getElementsByName("password")[0];
	var passwordDetails = firstPassword.parentElement.getElementsByClassName("details")[0];
	var secondPassword = document.getElementsByName("confirmPassword")[0];
	if(!checkPassword(firstPassword, secondPassword))
	{
		valid = false;
		firstPassword.classList.remove("background-white");
		secondPassword.classList.remove("background-white");
		firstPassword.classList.add("error");
		secondPassword.classList.add("error");
		passwordDetails.innerHTML = "password <span class=errortext>Le password non corrispondono</span>"
	} else {
		passwordDetails.innerHTML = "Password";
		firstPassword.classList.remove("error");
		secondPassword.classList.remove("error");
		firstPassword.classList.add("background-white");
		secondPassword.classList.add("backgorund-white");
	}

	if(valid) obj.submit();
}
