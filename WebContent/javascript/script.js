const searchWrapper = document.querySelector(".search-input");
const inputBox = searchWrapper.querySelector("input");
const suggBox = searchWrapper.querySelector(".autocom-box");
const icon = searchWrapper.querySelector(".icon");
let linkTag = searchWrapper.querySelector("a");
let webLink;



// se l'utente preme qualsiasi tasto
inputBox.onkeyup = (e)=>{
    let userData = e.target.value; //input dell'utente
    let emptyArray = [];
    if(userData){
        emptyArray = getSuggestions(userData);
    }else{
        searchWrapper.classList.remove("active"); //se l'utente non ha inserito nulla nascondi il menu delle suggestions
    }
}
function cerca(event){;//caso in cui l'utente prema invio
	if (event.keyCode == 13) {
		window.location = `CatalogoControl?keyword=${inputBox.value}`;
	}
        
}


function select(element){//caso in cui l'utente clicchi sulla lente di ingrandimento
    let selectData = element.textContent;
    inputBox.value = selectData;
    icon.onclick = ()=>{
    	window.location = `CatalogoControl?keyword=${inputBox.value}`;
    }
    searchWrapper.classList.remove("active");
}

function showSuggestions(list){
    let listData;
    if(!list.length){ //caso in cui non ci sia nessun entry nel database corrisponente alla keyword
        userValue = inputBox.value;
        listData = `<li>${userValue}</li>`;
    }else{
      listData = list.join('');
    }
    suggBox.innerHTML = listData;
}

function getSuggestions(keyword){
	var suggestions;
	$.get('SearchBarControl', {"keyword":keyword}, function(data,status){ //chiama il server
		data = data.map((data)=>{
            return data = `<li>${data}</li>`;
        });
		searchWrapper.classList.add("active");
		showSuggestions(data);
		let allList = suggBox.querySelectorAll("li");
		//ad ogni tag li nella suggBox aggiungi l'evento on click select(this)
        for (let i = 0; i < allList.length; i++) {
            allList[i].setAttribute("onclick", "select(this)");
        }
	})	
	
	return suggestions;
}
