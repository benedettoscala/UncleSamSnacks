<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*, bean.*, java.io.File"%>
<%
	@SuppressWarnings("unchecked")
	List<ProdottoBean> lista = (List<ProdottoBean>)request.getAttribute("lista");
	@SuppressWarnings("unchecked")
	List<AziendaBean> listaAziende = (List<AziendaBean>)request.getAttribute("listaAziende");
	@SuppressWarnings("unchecked")
	List<CategoriaBean> listaCategorie = (List<CategoriaBean>)request.getAttribute("listaCategorie");
	String pagina = request.getParameter("pagina");
	String buffer;
	String categoria = (buffer=request.getParameter("categoria"))==null ? "" : buffer;
	String marca = (buffer=request.getParameter("marca"))==null ? "" : buffer;
	int numPagina;
	if(pagina==null || Integer.parseInt(pagina)<=0)
	{
		numPagina = 1;
	}
	else
	{
		numPagina = Integer.parseInt(pagina);
	}
%>
<html>
<head>
	<meta name="viewport" content="width=device-width">
	<link rel="shortcut icon" href="headerfooter/logo-head.png" >
	<link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/catalogo.css">
    <link rel="stylesheet" href="css/searchbar.css">
    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="css/notifiche.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    
    
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="sfondo">
<input type ="hidden" id="page" value=<%=numPagina %>>
  <div class="santo">
    <div class="sidebar">
          <div class="category">
              <h3 class="text">Categoria</h3>
          <div class="choice">
          <%
          int i = 0;
          for(CategoriaBean cat: listaCategorie){
          %>
          	<div class="choice-card">
                  <button class="btn-<%=(i%2)==1 ? 1 : 2 %>" id="<%=cat.getTipo()%>"><%=cat.getTipo() %></button>
             </div>
          
	      <%
	      i++;
	      }
	      %>
              <br>
          </div>
          </div>
          <hr class="zitella">
          <div class="category">
              <br>
              <h3 class="text">Marca</h3>
          <div class="choice">
              <select id="selectMarca" size="2">
                <%for(AziendaBean azd: listaAziende){%>
                	<option><%=azd.getNome()%></option>
                <%}%>
              </select>
              <br>
              <button class="btn-3" id="btnMarca">Applica</button>
          </div>
          </div>
      </div>
  </div>

  <div class="catalogo-container">

    <div class="container">
    <%if(lista.size()==0)
    	{%>
    	<h1>Nessun prodotto trovato</h1>
    	<%}
    %>
      <%for(ProdottoBean prodotto: lista){%>		
      <div class="card">
        <div class="card__header">
          <img class ="card-image" src="<%=prodotto.getUrlImmagine() %>" alt="card__image" class="card__image" width="600">
        </div>
        <div class="card__body">
          <span class="tag tag-red"><%=prodotto.getCategoria() %></span>
          <h4 class="product-name"><a class="btn_normal" href="ProdottoControl?prodotto=<%=prodotto.getIdProdotto()%>"><%=prodotto.getNome() %></a></h4>
          <p class="price"><%if(prodotto.getSconto()!=0){ %> <span class="product-name-price-scontato"><%=prodotto.getPrezzoIva()%>$</span><%} %> <span><%=prodotto.getPrezzoReale() %>$</span></p>
          
          <%if(usr!=null && usr.getAutorizzazione().equals("Amministratore")){ %>
          <b>
                <a class="admin" href="ModificaProdottoControl?id=<%=prodotto.getIdProdotto() %>">Modifica il prodotto</a>
                <br>
                <a class="admin" onclick="eliminato(<%=prodotto.getIdProdotto() %>)">Elimina il prodotto</a>
            </b>
          <%}%>
            <%if(prodotto.getSconto()!=0){ %>
          <span class="tag tag-saldo"><%=prodotto.getSconto()%>% di sconto</span> 
          <%} %>   	
        </div>
        
        <div class="card__footer">
          <div class="user">
           <button class="btn_primary" id="aggiungiCarrello" onclick="aggiungiAlCarrello(<%=prodotto.getIdProdotto()%>)">Aggiungi al carrello</button>
            
            <div class="user__info">
            </div>
          </div>
        </div>
      </div> 
      <%} %> 

    </div>
    <div class="navigation-bar">
	<a<%if(numPagina==1||lista.size()==0){%> style="display:none"<%} %> id="indietro" href="CatalogoControl?pagina=<%=numPagina-1 %>&categoria=<%=categoria%>&marca=<%=marca%>"><img class="navImage" src="images/indietro.png"></a>
	<a<%if(lista.size()!=12){%> style="display:none"<%} %> id="avanti" href="CatalogoControl?pagina=<%=numPagina+1 %>&categoria=<%=categoria%>&marca=<%=marca%>"><img class="navImage" src="images/avanti.png"></a>
</div>
  </div>


<%@include file="footer.html" %>
<script src="javascript/carrello.js"></script>
<script src="javascript/filtro.js"></script>
  <div id="aggiunto">Aggiunto al carrello</div><!-- Ti serve -->
      <div id="eliminato">Sicuro di voler eliminare il prodotto? <!-- Ti serve -->
        <a href=""> Si</a><!-- Ti serve -->
      </div><!-- Ti serve -->
</div>
</body>

</html>