<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width">
<link rel="shortcut icon" href="headerfooter/logo-head.png" >
	<link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/catalogo.css">
    <link rel="stylesheet" href="css/ProfiloUtente.css">
    <link rel="stylesheet" href="css/searchbar.css">
    <title>Sezione utente</title>
    <link rel="stylesheet" href="boh.css">
</head>
<body>
<%@include file="header.jsp" %>
<%if(usr==null)
	response.sendRedirect("login.jsp");
else{
%>
<div class="mannaggia">
<div class="task-manager">
  <div class="left-bar">
    <div class="upper-part">
    </div>
    <div class="left-content">
    <h3 class="title">Utente</h3>
    <hr class="line">
      <ul class="action-list">
        <li class="item">
          <a href=""><span>Torna all'Homepage</span></a>
        </li>
        <%if(usr==null){ %>
        
        <li class="item">
            <a href=""><span>Effettua il Login</span></a>
        </li>
        <%} else{ %>
        
        <li class="item">
          <a href=""><span>Effettua il Logout</span></a>
        </li>
        
        <%} %>
        <li class="item">
            <a href="OrdineControl?azione=visualizzaOrdini"><span>Recap degli ordini</span></a>
        </li>
        
        <li class="item">
            <a href="PagamentoControl?azione=visualizzaUtente"><span>Metodi di pagamento</span></a>
        </li>
        <li class="item">
            <a href="IndirizzoControl?azione=visualizzaUtente"><span>Indirizzi</span></a>
        </li>
      </ul>
      <%if(usr.getAutorizzazione().equals(("Amministratore"))){ %>
      <h3 class="title">Amministratore</h3>
      <hr class="line">
      <ul class="action-list">
      	<li class="item">
            <a href="InserisciProdottoControl"><span>Inserisci Nuovo Prodotto</span></a>
        </li>
        <li class="item">
            <a href="OrdineControl?azione=visualizzaOrdiniAmministratore"><span>Visualizza Ordini Utenti</span></a>
        </li>
        <li class="item">
            <a href="ListaUtentiControl"><span>Visualizza Utenti</span></a>
        </li>
      </ul>
      <%} %>
    </div>
  </div>
  <div class="page-content">
    <div class="header">Sezione utente</div>
    <hr>
    <div class="tasks-wrapper">
      <p>
         Username: <%=usr.getUsername() %>
         <br>
         <br>
         Nome: <%=usr.getNome() %>
         <br>
         <br>
         Cognome: <%=usr.getCognome() %>
         <br>
         <br>
         Data di nascita: <%=usr.getDataNascita() %>
      </p>
      <button id="modificaDati">Aggiorna i tuoi dati</button>
    </div>
  </div>
</div>
</div>
<%@include file="footer.html" %>
<script>
$("#modificaDati").click(function(){
	window.location = 'ModificaDatiUtente.jsp'
})
</script>
</body>
<%} %>
</html>