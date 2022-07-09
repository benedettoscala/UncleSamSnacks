var src = $('.zoom-show').find('img').attr("src");
$('.zoom-show').append('<img class="zoom" src="'+src+'" >');

$('.zoom-show').mouseenter(function(){
  
    $(this).mousemove(function(event){
      
        var offset = $(this).offset();
        var left = event.pageX - offset.left;
        //console.log(event.pageX+ " e " + event.pageY)
        var top = event.pageY - offset.top;
        
        $(this).find('.zoom').css({
          width: '200%',
          opacity: 1,
          left: -left,
          top: -top
        })
    });
});

$('.zoom-show').mouseleave(function(){                     
   $(this).find('.zoom').css({
     width: '100%',
     opacity: 0,
     left: 0,
     top: 0
   })                               
 });