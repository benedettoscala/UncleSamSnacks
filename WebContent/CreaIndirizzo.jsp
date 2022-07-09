<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List, bean.IndirizzoBean" %>
<%
 	@SuppressWarnings("unchecked")
	List<IndirizzoBean> indirizzi = (List<IndirizzoBean>)request.getAttribute("indirizzi");
	
%>
<!DOCTYPE html>
<html>
<head>
	<link rel="shortcut icon" href="headerfooter/logo-head.png" >
  <meta name="viewport" content="width=device-width">
    <title>Prova acquisto</title>
    <link rel="stylesheet" href="css/CreaIndirizzo.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
</head>
<body>
<%@include file="header.jsp"%>
<%
if(usr==null ||usr.getUsername()==null)
	response.sendRedirect("login.jsp");
else{
%>

<div class="mannaggia">
    <div class="container">
    <div class="title">Inserisci il tuo nuovo indirizzo di spedizione</div>
    <br>
        <form action="IndirizzoControl" method="post">
          <div class="user-details">
            <div class="input-box">
              <span class="details">Città</span>
              <input type="text" name="citta" id="citta" placeholder="In che città vivi?" required>
            </div>
            <div class="input-box">
              <span class="details">Via</span>
              <input type="text" name="via" id="via"  placeholder="Es. via l. ferrante" required>
            </div>
            <div class="input-box">
              <span class="details">Numero Civico</span>
              <input type="number" name="numeroCivico" id="numeroCivico"  placeholder="Es. 18" required>
            </div>
            <div class="input-box">
              <span class="details">Codice Postale</span>
              <input type="number" name="codicePostale" id="codicePostale" required>
            </div>
          <div class="button">
          <input type="hidden" name="username" value="<%=usr.getUsername()%>">
          <input type="hidden" name="azione" value="aggiungiUtente">
            <input type="submit" value="Invia">
          </div>
          </div>
        </form>
      </div>
    </div>
<div></div>

<%@include file="footer.html" %>
  </body>
</html>
<%}%>