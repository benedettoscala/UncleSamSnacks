<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Non trovata</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/accesso-rifiutato.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
</head>
<body>
<%@include file="header.jsp" %>
    <main class="container">
        <article class="content">
          <h1>404: Pagina Non Trovata</h1>
          <p>
            <button>Torna all'homepage</button>
          </p>
        </article>
      </main>

<%@include file="footer.html" %>
<script>
$(".content p button").click(function(){
	window.location = "HomepageControl";
})

</script>
</body>
</html>