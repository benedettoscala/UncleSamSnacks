<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="bean.UtenteBean" %>
<% 
	String senzaAutorizzazione = "HomepageControl";
	String keyword = request.getParameter("keyword");
	if(keyword == null) keyword = "";
	UtenteBean usr = (UtenteBean)request.getSession().getAttribute("user");
%>


<header class="head">
		<div class="added">Prodotto aggiunto</div>
        <div class="containerimglogo">
            <a href="HomepageControl"><img class="imglogo" src="headerfooter/logosito.png"></a>
        </div>
        <div class="wrapper">
        <div class="search-input">
          <a href="" target="_blank" hidden=></a>
          <input type="text" placeholder="Cerca il prodotto" onkeypress="return cerca(event)" value="<%=keyword %>">
          <div class="autocom-box">
            <!-- here list are inserted from javascript -->
          </div>
          <div class="icon"><img src="images/search.png" class="searchicon" ></img></div>
        </div>
      </div>
        <div class="accountcart-container">
            <div class="action">
              <div class="cart">
                <a href="CarrelloControl?azione=visualizza"><img src="headerfooter/carrello.png"></a>
              </div>
              <div class="profile" onclick="menuToggle();">
                <img src="headerfooter/account.png">
              </div>
              <%if(usr!=null){ %>
              <div class="menu">
                <h3>Ciao, <%=usr.getNome()%>!<br><span><%=usr.getAutorizzazione()%></span></h3>
                <ul>
                  <li><img src="headerfooter/user.png"><a href="ProfiloUtente.jsp">Profilo</a></li>
                  <li><img src="headerfooter/orders.png"><a href="OrdineControl?azione=visualizzaOrdini">Ordini</a></li>
                  <li><img src="headerfooter/credit-card.png"><a href="http://localhost:8080/UncleSamSnacks/PagamentoControl?azione=visualizzaUtente">Metodi Di Pagamento</a></li>
                  <li><img src="headerfooter/logout.png"><a href="LogoutControl">Logout</a></li>
                </ul>
              </div>
              <%} else { %>
              	<div class="menu">
                <h3>Fai Il Login<br><span>O registrati</span></h3>
                <ul>
                  <li><img src="headerfooter/user.png"><a href="login.jsp">Login</a></li>
                  <li><img src="headerfooter/settings.png"><a href="Registrazione.jsp">Registrati</a></li>
                </ul>
              </div>
              <%} %>
            </div>
        </div>
    </header>
    <section class="navigation">
      <div class="nav-container">
        <div class="brand">
          <a href="#!">GOTTA LOVE SNACKS</a>
        </div>
        <nav>
          <div class="nav-mobile"><a id="nav-toggle" href="#!"><span></span></a></div>
          <ul class="nav-list">
            <li>
              <a href="HomepageControl">Home</a>
            </li>
            <li>
              <a href="CatalogoControl">Catalogo</a>
            </li>
            <li>
              <a href="#!">Su di Noi</a>
              <ul class="nav-dropdown">
                <li>
                  <a href="https://www.instagram.com/_benedettoscala_/">Benedetto Scala</a>
                </li>
                <li>
                  <a href="https://www.instagram.com/_mikela_2001_/">Michela Faella</a>
                </li>
                <li>
                  <a href="https://www.unisa.it/">Dove Studiamo</a>
                </li>
              </ul>
            </li>
            <li>
              <a href="CatalogoControl?pagina=1&categoria=Patatine%20e%20Salatini&marca=">Salato</a>
            </li>
            <li>
              <a href="CatalogoControl?pagina=1&categoria=Cioccolato&marca=">Dolce</a>
            </li>
            <li>
              <a href="CatalogoControl?pagina=1&categoria=bevande&marca=">Bevande</a>
            </li>
          </ul>
        </nav>
      </div>
    </section>