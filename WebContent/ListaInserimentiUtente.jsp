<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="bean.InserimentoBean, java.util.List, bean.UtenteBean, bean.OrdineBean, bean.IndirizzoBean, bean.PagamentoBean" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" href="headerfooter/logo-head.png" >
	<meta name="viewport" content="width=device-width">
	<link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/ordine.css"> 
    <link rel="stylesheet" href="css/searchbar.css">
    <title>CARRELLO</title>
</head>
<body>
<%@include file="header.jsp" %>
<%
	if(usr == null)
		response.sendRedirect("login.jsp");
	else
	{
		@SuppressWarnings("unchecked")
		List<InserimentoBean> listaInserimenti = (List<InserimentoBean>)request.getAttribute("listaInserimenti");
		OrdineBean ordine = (OrdineBean)request.getAttribute("ordine");
		IndirizzoBean indirizzo = (IndirizzoBean)request.getAttribute("indirizzo");
		PagamentoBean pagamento = (PagamentoBean)request.getAttribute("pagamento");
%>
<div class="mannaggia">
<div class="body-wrapper">
    <div class="wrappercar">
    	<ul class="breadcrumb">
		  <li><a href="ProfiloUtente.jsp">PaginaUtente</a></li>
		  <li><a href="OrdineControl?azione=visualizzaOrdini">Ordini</a></li>
		  <li><a href="#">Ordine <%=ordine.getIdOrdine() %></a></li>
		</ul>
        <h1>Ordine <%=ordine.getIdOrdine() %></h1>
        <hr>
        <h2>Ordinato il <%=ordine.getData() %> alle <%=ordine.getOra() %></h2>
        <hr>
        <br>
        <div class="project">
            <div class="shop">
            <%
            for(InserimentoBean ins: listaInserimenti)
            {
           	%>
                <div class="box">
                    <img src="<%=ins.getUrlImmagine()%>" alt="">
                    <div class="content">
                        <h3><%=ins.getNomeProdotto() %></h3>
                        <h5><%=ins.getPrezzo() %>$</h5>
                        <p class="unit"><%=ins.getQuantita() %></p>
                    </div>
                </div>
             <%
            }
             %>
            </div>
            <div class="right-bar">
                <p><span><b>Totale</b> </span><span><%=ordine.getTotale() %>$</span></p>
                <hr>
                <p><span><b>Indirizzo Di Spedizione</b></span><p>
                <p><%=indirizzo.getVia() %>, <%=indirizzo.getNumerocivico() %></p>
                <p><%=indirizzo.getCitta() %>, <%=indirizzo.getCap() %></p>
                <hr>
                <p><span><b>Modalità di Pagamento</b></span><p>
                <p><%=pagamento.getNumeroCarta() %></p>
                <p><%=pagamento.getNome() %>, <%=pagamento.getCognome() %></p>
                 <a href="FatturaControl?id=<%=ordine.getIdOrdine()%>">Download Fattura</a>
            </div>
        </div>
       
    </div>
   </div>
   </div>
   <%@include file="footer.html" %>
</body>
</html>
<%
	}
%>