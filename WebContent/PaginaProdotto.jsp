<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*, bean.ProdottoBean, java.io.File"%>
<%
	ProdottoBean prodotto = (ProdottoBean)request.getAttribute("prodotto");
	if(prodotto==null)
		response.sendRedirect("CatalogoControl");
	else
	{
%>
<html>
<head>
<meta name="viewport" content="width=device-width">
<meta charset="ISO-8859-1">
<title><%=prodotto.getNome() %></title>
	<link rel="shortcut icon" href="headerfooter/logo-head.png" >
	<link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/prodotto.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/searchbar.css">
    <style>
  
    	#immagine{
    	float:left;
    	}
    		
    </style>
</head>
<body>
	<%@include file="header.jsp" %>
		<div class = "card-wrapper">
            <div class = "card">
              <!-- PARTE SINISTRA -->
              <div class = "product-imgs">
                <div class = "img-display">
                  <div class = "img-showcase">
                   <div class="zoom-show"> <img src = "<%=prodotto.getUrlImmagine() %>" alt = "immagine"></div>
                  </div>
                </div>
              </div>
              <!-- PARTE DESTRA -->
              <div class = "product-content">
                <h2 class = "product-title"><%=prodotto.getNome() %></h2>
                <a href = "#" class = "product-link"><%=prodotto.getAzienda() %></a> 
                <div class = "product-price">
                <%
                	if(prodotto.getSconto()!=0)
                	{
                %>
                  <p class = "last-price">Prezzo Iniziale: <span><%=prodotto.getPrezzoIva() %>$</span></p> <!-- nel caso di sconto -->
                  <p class = "new-price">Prezzo Scontato: <span><%=prodotto.getPrezzoReale()%>$ (<%=prodotto.getSconto()%>%)</span></p>
                <%
                	} else {	
                %>
                    <p class = "new-price">Prezzo: <span><%=prodotto.getPrezzoReale() %>$</span></p>
                <%
                	}
                %> 
                </div>
          
                <div class = "product-detail">
                  <h2>Scopri di più: </h2>
                  <p><%=prodotto.getDescrizione() %></p>
                  <p><%=prodotto.getCategoria()%></p>
                  <ul><!-- Uguale per tutti -->
                    <li> <span>Puoi pagare anche alla consegna</span></li>
                    <li>Disponibile: <span> approfittane subito!</span></li>
                    <li><span>Consegna in </span> 3-5 giorni lavorativi</li>
                    <li><span>Spedizione</span> GRATIS sopra i 15 euro <span> di spesa</span></li>
                  </ul>
                </div>
          
          		
               <div class = "purchase-info">
                <form method=get action="CarrelloControl">
                  <input type = "number" min = "0" value = "1" name=quantita >
                  <input type="hidden" name="azione" value="aggiungi">
                  <input type="hidden" name=id value="<%=prodotto.getIdProdotto()%>">
                  <button type = "submit" class = "btn" >
                    AGGIUNGI AL CARRELLO 
                  </button>
                  <a id="insideBtn" href="Carrello.jsp"><button type = "button" class = "btn">PROCEDI ALL'ACQUISTO</button></a>
                  </form>
                  </div>
                </div>
              </div>
              </div>
	<%@include file="footer.html" %>
	<script src="javascript/zoom.js"></script>
</body>
<%
	}
%>
</html>