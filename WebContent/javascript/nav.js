(function($) { 
  $(function() {
    $('nav ul li a:not(:only-child)').click(function(e) {
      $(this).siblings('.nav-dropdown').toggle();
      // chiudi i menu dropdown quando ne apri un altro
      $('.nav-dropdown').not($(this).siblings()).hide();
      e.stopPropagation();
    });
    // se si clicca altrove si chiuderà il menu dropdown
    $('html').click(function() {
      $('.nav-dropdown').hide();
    });
    // Apri menu hamburger
    $('#nav-toggle').click(function() {
      $('nav ul').slideToggle();
    }); //trasforma l'hamburger in una x
    $('#nav-toggle').on('click', function() {
      this.classList.toggle('active');
    });
  });
})(jQuery); 