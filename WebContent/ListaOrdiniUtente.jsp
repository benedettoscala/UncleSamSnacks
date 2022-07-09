<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import ="bean.OrdineBean, java.util.List, bean.UtenteBean"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width">
    <title>List</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/list.css">
    <link rel="stylesheet" href="css/nav.css">
     <link rel="stylesheet" href="headerfooter/header.css">
     <link rel="stylesheet" href="headerfooter/footer.css">
     <link rel="stylesheet" href="css/searchbar.css">
</head>

<body>
<%@ include file="header.jsp" %>
<%
	
	if(usr == null)
		response.sendRedirect("login.jsp");
	else
	{
		@SuppressWarnings("unchecked")
		List<OrdineBean> listaOrdini = (List<OrdineBean>)request.getAttribute("listaOrdini");
%>
	<div class="mannaggia">
    <div class="X">
    <ul class="breadcrumb">
		  <li><a href="ProfiloUtente.jsp">PaginaUtente</a></li>
		  <li><a href="OrdineControl?azione=visualizzaOrdini">Ordini</a></li>
	</ul>
        <h1>Ordini effettuati</h1>
        
        <ul class="SG">
        <%if(listaOrdini.size()==0){ %>
        <div class="none-box">
        	<p>Non hai ancora effettuato nessun ordine</p>
        	<a class="none" href="HomepageControl">Torna all'homepage</a>
        </div>
        <%} %>
        <%
        	for(OrdineBean ord: listaOrdini)
        	{
        %>
        
        
          <li class="sgLi">
            <div class="box">
            <h3><a href="OrdineControl?azione=visualizzaOrdine&idOrdine=<%=ord.getIdOrdine()%>&numPag=0">Ordine <%=ord.getIdOrdine() %></a></h3>
            <ul class="df">
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
	<%@include file="footer.html" %>
</body>

</html>
<%
	}
%>