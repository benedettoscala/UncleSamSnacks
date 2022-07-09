<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width">
    <title>Modifica dati</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/catalogo.css">
    <link rel="stylesheet" href="css/searchbar.css">
    <link rel="stylesheet" href="css/modifica-dati.css">
</head>
<body>
<%@include file="header.jsp" %>
<%if(usr==null)
	response.sendRedirect("login.jsp");
else{
%>
<div class="mannaggia">
    <div class="container-utente">
      <div class="title">Modifica i tuoi dati</div>
      <br>

      <div class="content">

        <form action="ModificaDatiControl" method="post" onsubmit="event.preventDefault(); validate(this)">
          <div class="user-details">
            
            <div class="input-box"><!-- Non so se sia possibile modificare l'ID -->
              <span class="details">Username</span>
              <input type="text" name="username" id="username" value="<%=usr.getUsername() %>" required>
            </div><!-- Se no cancella questo div (dal primo commento a mo) -->
            <div class="input-box">
              <span class="details">Nome</span>
              <input type="text" name="nome" id="nome" value="<%=usr.getNome() %>" required>
            </div>
            <div class="input-box">
              <span class="details">Cognome</span>
              <input type="text" name="cognome" id="cognome" value="<%=usr.getCognome() %>" required>
            </div>
            <div class="input-box">
              <span class="details">Email</span>
              <input type="text" name="email" id="email" value="<%=usr.getEmail() %>" required>
            </div>
            <div class="input-box">
              <span class="details">Password</span>
              <input type="password" name="password" id="password" value="<%=usr.getPassword() %>" required>
            </div>
            <div class="input-box">
            <span class="details">Conferma Password</span>
            <input type="password" placeholder="Conferma la password" name="confirmPassword" value="<%=usr.getPassword() %>" required>
          </div>
            <div class="input-box">
              <span class="details">Data di nascita</span>
              <input type="date" name="data" id="data" value="<%=usr.getDataNascita() %>" required>
            </div>
          <div class="button">
            <input type="submit" value="Invia">
          </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <%@include file="footer.html" %>
  <script src="javascript/registrazione.js"></script>
  <%} %>
  </body>
</html>