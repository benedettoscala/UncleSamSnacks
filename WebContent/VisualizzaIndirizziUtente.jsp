<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List, bean.IndirizzoBean" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista id</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/lista_indirizzo.css">
        <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
</head>
<body>
<%@include file="header.jsp"%>
<%
if(usr == null)
	response.sendRedirect("login.jsp");
else{
	List<IndirizzoBean> indirizzi = (List<IndirizzoBean>)request.getAttribute("indirizzi");
%>
<div class="address">
    <section>
        <div class="text-content">
        <ul class="breadcrumb">
		  <li><a href="ProfiloUtente.jsp">PaginaUtente</a></li>
		  <li><a href="IndirizzoControl?azione=visualizzaUtente">Indirizzi</a></li>
	</ul>
          <h1 class="text-title">Indirizzi</h1>
        <a href="IndirizzoControl?azione=aggiungiIndirizzo" class="text">Aggiungi un nuovo indirizzo</a>
        </div>
        <div class="tbl-header">
          <table class="address-table">
            <thead>
              <tr>
                <th class="address-th">Via</th>
                <th class="address-th">Numero civico</th>
                <th class="address-th">Citta</th>
                <th class="address-th">Cap</th>
                <th class="address-th">elimina</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table class="address-table">
            <tbody>
            <%for(IndirizzoBean ind: indirizzi){ %>
              <tr><!-- Essenziamente devi ripetere da qua -->
                <td class="address-td"> </a><%=ind.getVia() %></a></td>
                <td class="address-td"><%=ind.getNumerocivico() %></td>
                <td class="address-td"><%=ind.getCitta() %></td>
                <td class="address-td"><%=ind.getCap() %></td>
                <td class="address-td "><a href="IndirizzoControl?azione=elimina&idIndirizzo=<%=ind.getIdIndirizzo() %>" id="ancora"><img class="close" src="images/close.png" alt=""></a></td>
              </tr><!-- a qua -->
              <%} %>
            </tbody>
          </table>
        </div>
      </section>
</div>
<%@include file="footer.html" %>
</body>
<%} %>
</html>