<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List, bean.UtenteBean" %>
<%
	List<UtenteBean> listaUtenti = (List<UtenteBean>)request.getAttribute("listaUtenti");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista id</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/catalogo.css">
    <link rel="stylesheet" href="css/searchbar.css">
    <link rel="stylesheet" href="css/lista_id.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class=user>
    <section>
        <h1 class="text-title">Lista utenti</h1>
        <div class="tbl-header">
          <table class="user-table">
            <thead>
              <tr>
                <th class="user-th">Username</th>
                <th class="user-th">Nome</th>
                <th class="user-th">Cognome</th>
                <th class="user-th">Dettagli</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table class="user-table">
            <tbody>
            
            <%for(UtenteBean ut: listaUtenti){ %>
              <tr>
                <td class="user-td"><%=ut.getUsername() %></td>
                <td class="user-td"><%=ut.getNome() %></td>
                <td class="user-td"><%=ut.getCognome() %></td>
                <td class="user-td"><a href="VisualizzaUtenteControl?utente=<%=ut.getUsername()%>" class="go">Visualizza</a></td>
               </tr>
             <%} %>
            </tbody>
          </table>
        </div>
      </section>
</div>
<%@include file="footer.html" %>
</body>
</html>