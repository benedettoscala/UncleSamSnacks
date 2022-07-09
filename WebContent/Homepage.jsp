<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, bean.*, java.io.File"%>
<%
	@SuppressWarnings("unchecked")
	List<CategoriaBean> listaCategorie = (List<CategoriaBean>)request.getAttribute("listaCategorie");
	@SuppressWarnings("unchecked")
	List<AziendaBean> listaAziende = (List<AziendaBean>)request.getAttribute("listaAziende");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="shortcut icon" href="headerfooter/logo-head.png" >
    <link rel="stylesheet" href="css/banner.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/categoria.css">
    <link rel="stylesheet" href="css/marche.css">
    <link rel="stylesheet" href="headerfooter/header.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="headerfooter/footer.css">
    <link rel="stylesheet" href="css/searchbar.css">

</head>
<body>
<%@include file="header.jsp" %>
    <div class="banner">
        <section class="hero">
            <div class="banner-container">
    
              <h2 class="h1 hero-title">Uncle Sam Snacks</h2>
    
              <p class="hero-text">
                Il tuo sito preferito per l'acquisto di Snack Americani. Più di 10 marche, spedizioni veloci!
              </p>
    
              <div class="btn-group">
                <a href="CatalogoControl" class="btn btn-primary">Inizia l'acquisto</a> <!--Li mandi alla registrazione/login-->
              </div>
    
            </div>
          </section>
    </div>

    <div class="ass">
        <section class="popular">
          <div class="banner-container">
      
            <p class="section-subtitle">Venduti, Amati e Mangiati!</p>
      
            <h2 class="h2 section-title">Top 3</h2>
      
            <p class="section-text">Buttati a capofitto tra i prodotti più apprezzati dai nostri clienti, 
              lasciati avvolgere dal gusto intenso e per nulla scontato dei nostri snack. <br> Fidati non te ne pentirai!</p>
      
            <ul class="popular-list">
      
              <li>
                <div class="popular-card">
      
                  <div class="card-img">
                    <img src="images/hero1.jpg"  >
                  </div>
      
                  <div class="card-content">
      
                    <p class="card-subtitle">
                      Doritos
                     </p>
       
                     <h3 class="h3 card-title" >
                       <a href="CatalogoControl?keyword=Doritos">Doritos</a>
                     </h3>
       
                     <p class="card-text">
                      Fra gli snack salati più famosi negli U.S.A, più autentici e buoni di così davvero non ne ho trovati.
                     </p>
      
                  </div>
      
                </div>
              </li>
      
              <li>
                <div class="popular-card">
      
                  <div class="card-img">
                    <img src="images/Hero2.jpg"  >
                  </div>
      
                  <div class="card-content">
      
                    <p class="card-subtitle">
                      Chupa Chups
                     </p>
       
                     <h3 class="h3 card-title">
                       <a href="CatalogoControl?keyword=Chupa">Chupa Chups</a>
                     </h3>
       
                     <p class="card-text">
                      Chupa Chups ad oggi vende più di 100 gusti in tutto il mondo!
                     </p>
      
                  </div>
      
                </div>
              </li>
      
              <li>
                <div class="popular-card">
      
                  <div class="card-img">
                    <img src="images/Hero3.jpg">
                  </div>
      
                  <div class="card-content">
      
     
                    <p class="card-subtitle">
                      Oreo
                     </p>
       
                     <h3 class="h3 card-title">
                       <a href="CatalogoControl?keyword=Oreo">Oreo</a>
                     </h3>
       
                     <p class="card-text">
                      Le caramelle gommose per eccellenza: uniche, originali, travolgenti, colorate e irresistibilmente gustose!
                     </p>
      
                  </div>
      
                </div>
              </li>
      
            </ul>
      
            <a href="CatalogoControl" class="btn btn-primary">Altri prodotti</a>
      
          </div>
        </section>
      </div>
      
      <section class="BgImage2"></section>

      <div class="category">
        <section class="package" id="package">
            <div class="banner-container">
    
              <p class="section-subtitle">Fatti un giro</p>
    
              <h2 class="h2 section-title">Le noste categorie</h2>
    
              <p class="section-text">
                Trova la categoria più adatta a te! Ce ne è per tutti i gusti.
              </p>
    
              <ul class="package-list">
    		<%
    		for(CategoriaBean cat: listaCategorie)
    		{
    		%>
                <li>
                  <div class="package-card">
    
                    <figure class="card-banner">
                      <img src="images/categorie/<%=cat.getTipo()%>.jpg">
                    </figure>
    
                    <div class="card-content">
    
                      <h3 class="h3 card-title"><%=cat.getTipo() %></h3>
    
                      <p class="card-text">
                        <%=cat.getDescrizione() %>
                      </p>
    
                    </div>
    
                    <div class="card-price">
    
                      <div class="card-price">
    
                        <div class="homepage-wrapper">
                        <a class="btn btn-secondary" href="CatalogoControl?categoria=<%=cat.getTipo()%>">Mostra</a>
                        </div>
      
                    </div>
    				</div>
                  </div>
                </li>
              <%}%>
              	</ul>
            </div>
          </section>
       </div>

       <section class="BgImage"></section>

 <div class="marche">
  <div class="container-marche">
    <input type="radio" name="dot" id="one">
    <input type="radio" name="dot" id="two">
    <input type="radio" name="dot" id="three">
    <input type="radio" name="dot" id="four">
    <div class="main-card">
      <div class="cards">
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/chupa-chups.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/doritos.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/fanta.png" alt="">
           </div>
         </div>
        </div>
      </div>
      
      <div class="cards">
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/fluff.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/hershey.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/hostess.png" alt="">
           </div>
         </div>
        </div>
      </div>

      <div class="cards">
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/jif.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/nestle.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/oreo.png" alt="">
           </div>
         </div>
        </div>
      </div>

      <div class="cards">
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/pringles.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/reeses.png" alt="">
           </div>
         </div>
        </div>
        <div class="carta">
         <div class="content">
           <div class="img">
            <img src="images/marche/skittles.png" alt="">
           </div>
         </div>
        </div>
      </div>
    </div>
    
    <div class="button">
      <label for="one" class=" active one"></label>
      <label for="two" class="two"></label>
      <label for="three" class="three"></label>
      <label for="four" class="four"></label>
    </div>
  </div>
  </div>
      <%@include file="footer.html" %>
</body>
</html>