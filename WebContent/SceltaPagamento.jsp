<%@ page language="java" import ="java.util.List, bean.PagamentoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	@SuppressWarnings("unchecked")
	List<PagamentoBean> pagamenti = (List<PagamentoBean>)request.getAttribute("pagamenti");
%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width">
    <title>Prova acquisto</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/acquisto.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
<meta charset="ISO-8859-1">
<title>Scelta Pagamento</title>
</head>
<body>
<%@include file="header.jsp"%>
<%if(usr==null){
	response.sendRedirect("login.jsp");
} else {
%>
<div class="mannaggia">

    <div class="container">
      <div class="title">Seleziona il tuo metodo di pagamento</div>
      <br>
      <div class="content">

        <form method=post action="AcquistoControl" class="form2" >
            <label for="pagamento">Pagamenti precedentemente registrati</label>
            <select name="idPagamento">
                <%
					for(PagamentoBean pag: pagamenti)
					{
				%>
					<option value=<%=pag.getIdPagamento()%>><%=pag.getNome() %> <%=pag.getCognome() %> <%=pag.getNumeroCarta() %></option>
				<%
					}
				%>
            </select>
            <input type=hidden name=idIndirizzo value="<%=request.getParameter("idIndirizzo")%>">
			<input type=hidden name=username value="<%=usr.getUsername()%>">
            <br>
            <div class="button">
         		 <input type="submit" value="Procedi" class="btn">
        	</div>
        </form>
        
       <form action="PagamentoControl" class="form" method="post" onsubmit="event.preventDefault(); validate(this)" >
            <div class="user-details">
              <div class="input-box">
                <span class="details">Numero Carta</span>
                <input type="text" name="numeroCarta" id="numeroCarta" required>
              </div>
              <div class="input-box">
                <span class="details">CVV</span>
                <input type="number" name="cvv" id ="cvv" required>
              </div>
              <div class="input-box">
                <span class="details">Data di Scadenza</span>
                <input type="date" name="dataScadenza" required>
              </div>
              <div class="input-box">
                <span class="details">Nome</span>
                <input type="text" name="nome" id="nome" required>
              </div>
              <div class="input-box">
                <span class="details">Cognome</span>
                <input type="text" name="cognome" id="cognome" required>
              </div>
            <div class="button">
            <input type="hidden" name="username" value="<%=usr.getUsername()%>"><br>
            <input type=hidden name=idIndirizzo value="<%=request.getParameter("idIndirizzo")%>">
            <input type="hidden" name="azione" value="aggiungi">
              <input type="submit" value="Invia">
            </div>
            </div>
          </form>
        </div>
      </div>
</div>
<%@include file="footer.html" %>
<script src="javascript/pagamento.js"></script>
</body>
</html>
<%}%>