function selectB(element){
	    let selectData = element.textContent;
	    $(".wrapperO").find(".search-inputO").find("input").val(selectData);
	    icon.onclick = ()=>{
	        webLink = `CatalogoControl?keyword=${inputBox.value}`;
	        linkTag.setAttribute("href", webLink);
	        linkTag.click();
	    }
	    searchWrapper.classList.remove("active");
	}
	

$(document).ready(function(){
	
	$(".wrapperO").find("input").keyup(function(){
		let userData = $(this).val();
		 console.log(userData);
		let emptyArray = [];
		if(userData){
			$.get('UtenteControl', {"keyword":userData}, function(data,status){
				console.log(data)
				data = data.map((data)=>{
		            return data = `<li>${data}</li>`;
		        });
				$(".wrapperO").find(".search-inputO").addClass("activeO");

				showSuggestions(data);
				$(".autocom-boxO li").click(function(){
					$(".search-inputO input").val($(this).html());
					$(".wrapperO").find(".search-inputO").removeClass("activeO");
			
				})
				
			})
	    }else{
	    	$(".wrapperO").find(".search-inputO").removeClass("activeO"); //hide autocomplete box
	    }
	});
	
	
	
	function showSuggestions(list){
	    let listData;
	    if(!list.length){
	        userValue = $(".wrapperO").find(".search-inputO").find("input").val();
	        listData = `<li>${userValue}</li>`;
	    }else{
	      listData = list.join('');
	    }
	    $(".wrapperO").find(".search-inputO").find(".autocom-boxO").html(listData);
	}
});


