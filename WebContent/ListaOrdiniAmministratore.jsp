<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import ="bean.OrdineBean, java.util.List, bean.UtenteBean"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width">
    <title>List</title>
    <link rel="stylesheet" href="css/list.css">
      <meta charset="UTF-8">
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
</head>
<body>
<%@include file="header.jsp"%>
<%
if(usr == null || !usr.getAutorizzazione().equals("Amministratore"))
	response.sendRedirect("AccessoRifiutato.jsp");
else
{
	@SuppressWarnings("unchecked")
	List<OrdineBean> listaOrdini = (List<OrdineBean>)request.getAttribute("listaOrdini");
%>
<div class="mannaggia">
    <div class="X">
    <ul class="breadcrumb">
		  <li><a href="ProfiloUtente.jsp">PaginaUtente</a></li>
		  <li><a href="OrdineControl?azione=visualizzaOrdiniAmministratore">Amministratore: Ordini Effettuati</a></li>
	</ul>
        <h1>Amministratore: Ordini Effettuati <%if(request.getParameter("utente") !=null&&!request.getParameter("utente").equals("") ){%>da <%=request.getParameter("utente")%> <%} %></h1>  
        
        
        <form method="get">
        <span>Ordina per
        	<select name="ordinazione">
        		<option value="utente">utente</option>
        		<option value="data">data</option>
        		<option value="totale">totale</option>
        	</select>
        	<select name="ordine">
        		<option value="asc">ascendente</option>
        		<option value="desc">discendente</option>
        	</select>
        	<input type="hidden" name="azione" value="visualizzaOrdiniAmministratore">
        	
        	
        	</span>
       
       	<div class="wrapperO">
        <div class="search-inputO">
          <a href="" target="_blank" hidden=""></a>
          <input type="text" name ="utente" placeholder="Cerca l'utente" onkeypress="return cerca(event)" value="">
          <div class="autocom-boxO"></div>
          
        </div>
      </div>
      	<input type="submit" value="invia" class="submit-select">
      	</form>
        	
        
        
        <ul class="SG">
        <%
        	for(OrdineBean ord: listaOrdini)
        	{
        %>
          <li class="sgLi">
            <div class="box">
            <h3><a href="OrdineControl?azione=visualizzaOrdine&idOrdine=<%=ord.getIdOrdine()%>&numPag=0">Ordine <%=ord.getIdOrdine() %></a></h3>
            <ul class="df">
            <li>Utente: <%=ord.getUtente() %></li>
            <li>Data: <%=ord.getData() %></li>
            <li>Ora: <%=ord.getOra() %></li>
            <li>Totale:<%=ord.getTotale() %></li>
            </ul> 
          </div>
         </li> 
        
		<%
        	}
		%>
		</ul>
		</div>
		</div>
</body>

<%@include file="footer.html" %>
<script src = "javascript/searchBarOrdini.js"></script>
</html>
<%
	}
%>