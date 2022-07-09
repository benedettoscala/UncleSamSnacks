<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DecimalFormat, bean.ProdottoBean, java.util.HashMap, java.util.Map, java.util.Iterator,bean.Carrello, bean.CategoriaBean, model.CategoriaModel" %>
<%
	Carrello car = (Carrello)request.getSession().getAttribute("carrello");

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="css/carrello.css">
<link rel="stylesheet" href="headerfooter/header.css">
<link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/carrello.css"> 
    <link rel="stylesheet" href="css/searchbar.css">
    <link rel="stylesheet" href="css/message.css">
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
<%@include file="header.jsp" %>
<%DecimalFormat ds = new DecimalFormat("#.##"); %>
<div class="background">
	<div class="wrappercar">
        <h1>Il tuo carrello</h1>
        
        
            
            <%
				if(car != null && car.restituisciCarrello().size()!=0)
				{
			%>
			<div class="project">
			<div class="shop">
			<%
					CategoriaModel catMdl = new CategoriaModel();
					Double subTotale= 0.0;
					for(Map.Entry<ProdottoBean, Integer> pair: car.restituisciCarrello().entrySet())
					{
						ProdottoBean prodotto = pair.getKey();
						subTotale += (prodotto.getPrezzoReale()*pair.getValue());
						
			%>
			
			
                <div class="box">
                    <img src="<%=prodotto.getUrlImmagine() %>" alt="">
                    <div class="content">
                        <h3><%=prodotto.getNome() %></h3>
                        <h5><%=ds.format(prodotto.getPrezzoReale()) %>$</h5>
                        <form action="CarrelloControl" method="get">
                        	<p class="unit"><input class="quantity" type = "number" name="quantita" min = "1" value = "<%=pair.getValue()%>"></p>
                        	<p class="btn-area">
                            <a href="CarrelloControl?azione=rimuovi&id=<%=prodotto.getIdProdotto() %>" class="btn-2">Rimuovi</a>
	                        </p>
	                        <p class="btn-area2">
	                            <button type="submit" class="button1">Aggiungi</button>
	                        </p>
	                        <input type="hidden" name="id" value="<%=prodotto.getIdProdotto()%>">
							<input type="hidden" name="azione" value="modifica">
                        </form>  
                    </div>
                </div>
                
              <%} %>  
            
			</div>
            <div class="right-bar">
                <p><span>Subtotale </span><span><%=ds.format(subTotale) %>$</span></p>
                <hr>
                <p><span>Spedizione </span><span>1,99$</span></p>
                <hr>
                <p><span><b>Totale</b> </span><span><%=ds.format(subTotale + 1.99) %>$</span></p>
           
                <a href="IndirizzoControl?azione=visualizza">Procedi all'acquisto</a>
            </div>
        
        
        </div>
        <%
		} else {
%>
	<h1>Carrello vuoto</h1>
<% 	
	}
%>

</div>
</div>

<script src="javascript/aggiunto.js"></script>
<%@include file="footer.html" %>	
</body>
</html>