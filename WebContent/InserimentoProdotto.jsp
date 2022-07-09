<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, bean.CategoriaBean,bean.AziendaBean, bean.UtenteBean, java.io.File"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width">
    <title>Inserimento</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/inserisciprodotto.css">
    <link rel="stylesheet" href="css/drag_drop.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">
</head>
<body>
<%@include file="header.jsp" %>
<%
	if(usr == null || !usr.getAutorizzazione().equalsIgnoreCase("amministratore"))
	{
		response.sendRedirect("AccessoRifiutato.jsp");
	}
	@SuppressWarnings("unchecked")
	List<CategoriaBean> listaCategorie = (List<CategoriaBean>)request.getAttribute("listaCategorie");
	@SuppressWarnings("unchecked")
	List<AziendaBean> listaAziende = (List<AziendaBean>)request.getAttribute("listaAziende");
%>
<div class="mannaggia">
    <div class="container">
      <div class="title">Inserisci un nuovo prodotto</div>
      <br>
      <div class="content">
        <form action="InserimentoProdottoControl" method="post" enctype="multipart/form-data" onsubmit="event.preventDefault(); validate(this)">
          <div class="user-details">
            <div class="input-box">
              <span class="details">Azienda</span>
              <select name="azienda" class="selection-azienda">
                 <%for(AziendaBean azd: listaAziende){ %>
                 	 <option value="<%=azd.getNome()%>"><%=azd.getNome() %></option>
                 <%} %>           
       		</select>
            </div>
            <div class="input-box">
              <span class="details">Categoria</span>
              <select name="categoria" class="selection">
				   <%for(CategoriaBean cat: listaCategorie){ %>
                 	 <option value="<%=cat.getTipo()%>"><%=cat.getTipo() %></option>
                 <%} %> 	
            </select>
            </div>
            <div class="input-box">
              <span class="details">Nome</span>
              <input type="text" name="nome" id="nome" required>
            </div>
            <div class="input-box">
              <span class="details">Prezzo (senza IVA)</span>
              <input type="text" name="prezzo" id="prezzo" required>
            </div>
            <div class="input-box">
              <span class="details">Descrizione</span>
              <input type="text" name="descrizione" id="descrizione" required>
            </div>
            <div class="input-box">
              <span class="details">Possibile sconto</span>
              <input type="number" name="sconto" id="sconto" max=100 min=0 required>
            </div>
            <div class="input-box">
              <span class="details">Quantità</span>
              <input type="number" name="quantita" id="quantita" placeholder="Se non ti serve metti 0" required>
            </div>
            <div class="input-box">
              <span class="details">IVA</span>
              <input type="number" name="IVA" id="quantita" placeholder="0" max=100 min=0 required>
            </div>
            <div class="input-box">
              <div class="drop-zone">
                <span class="drop-zone__prompt">Trascina qui la tua immagine</span>
                <input type="file" name="uploadFile" class="drop-zone__input">
              </div>
            </div>
            </div>
          <div class="button">
            <input type="submit" value="Invia">
          </div>
        </form>
      </div>
    </div>
    </div>
    <%@include file="footer.html" %>
    <script src="javascript/drag_drop.js"></script>
     <script src="javascript/inserimentoModifica.js"></script>
  </body>
</html>