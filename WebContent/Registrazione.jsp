<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
  	<meta name="viewport" content="width=device-width">
    <title>Signup</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/registrazione.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
   </head>
<body>
<%@include file="header.jsp" %>
<div class="mannaggia">

  <div class="container">
    <div class="title">Registrazione</div>
    <div class="content">
      <form action="RegistrazioneControl" onsubmit="event.preventDefault(); validate(this)" method="post">
        <div class="user-details">
          <div class="input-box">
            <span class="details" id = "NOME">Nome</span>
            <input type="text" placeholder="Inserisci il tuo nome" name ="nome" required>
          </div>
          <div class="input-box">
            <span class="details" id="COGNOME">Cognome</span>
            <input type="text" placeholder="Inserisci il tuo cognome" name = "cognome" required>
          </div>
          <div class="input-box">
            <span class="details">Username</span>
            <input type="text" placeholder="Inserisci lo username scelto" name = "username" required>
          </div>
          <div class="input-box">
            <span class="details">Email</span>
            <input type="text" placeholder="Inserire la email" name ="email" required>
          </div>
          <div class="input-box">
            <span class="details">Data</span>
            <input type="date" name ="data" required>
          </div>
          <div class="input-box">
            <span class="details">Password</span>
            <input type="password" placeholder="Inserisci la tua password" name = "password" required>
          </div>
          <div class="input-box">
            <span class="details">Conferma Password</span>
            <input type="password" placeholder="Conferma la password" name = "confirmPassword" required>
          </div>
        </div>
        <div class="button">
          <input type="submit" value="Invia">
        </div>
      </form>
    </div>
  </div>
  </div>
  <script src="javascript/jquery-3.5.1.js"></script>
<script src="javascript/registrazione.js"></script>
<%@include file="footer.html" %>
</body>
</html>