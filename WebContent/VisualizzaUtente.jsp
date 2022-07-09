<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="bean.UtenteBean, java.util.List, bean.IndirizzoBean" %>
<%
	UtenteBean utente = (UtenteBean)request.getAttribute("utente");
	List<IndirizzoBean> listaIndirizzi = (List<IndirizzoBean>)request.getAttribute("listaIndirizzi");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizza utente</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/visualizza_utente.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/catalogo.css">
    <link rel="stylesheet" href="css/searchbar.css">
</head>
<body>
<%@include file="header.jsp" %>
<% 
if(usr == null || !usr.getAutorizzazione().equalsIgnoreCase("amministratore"))
{
		response.sendRedirect("AccessoRifiutato.jsp");
} else {
%>
<div class="user-view">
    <div class="blog_post">
        <div class="container_copy">
            <h3><%=utente.getDataNascita() %></h3>
            <h1><%=utente.getUsername() %></h1>
            <div class="user-info">
                <p>Nome: <%=utente.getNome() %></p>
                <p>Cognome: <%=utente.getCognome()%></p>
                <p>Email: <%=utente.getEmail() %></p>
                <p> Indirizzi associati:
                    <ol>
                    <%for(IndirizzoBean ind: listaIndirizzi){ %>
                        <li class="indirizzi"><%=ind.getCitta()%>, <%=ind.getVia()%>, <%=ind.getNumerocivico()%>, <%=ind.getCap()%></li>
					<%} %>
                    </ol>
                </p>
            </div>
        </div>
        <a class="user-credit" href="PagamentoControl?azione=visualizzaAmministratore&utente=<%=utente.getUsername()%>">Visualizza i metodi di pagamento</a>
        <a class="user-credit" href="OrdineControl?ordinazione=utente&ordine=asc&azione=visualizzaOrdiniAmministratore&utente=<%=utente.getUsername()%>">Visualizza ordini effettuati</a>
      </div>
</div>
<%@include file="footer.html" %>
<%} %>
</body>