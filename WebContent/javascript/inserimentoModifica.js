
function checkPrezzo(inputtxt)
{

	var prezzoPattern = /^\d+(.\d{1,2})?$/;
	if(inputtxt.match(prezzoPattern)) 
		return true;
	
	return false;
}


function validate(obj) {	
	var valid = true;	
	
	var name = $("#prezzo");
	var prezzo = name.val().replace(",",".");
	$("#prezzo").val($("#prezzo").val().replace(",","."))
	if(!checkPrezzo(name.val())) {
		valid = false;
		$("#prezzo").siblings(".details").html("Prezzo (senza IVA) <span style=\"color:red\">Inserire prezzo valido</span>");
	} else {

		$("#prezzo").siblings(".details").html("Prezzo (senza IVA)")
	}

	if(valid) obj.submit();
}