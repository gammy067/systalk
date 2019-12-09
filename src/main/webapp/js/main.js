var $root = $('html, body');
$('.nav-menu li').click(function(e) {
    var width = $(window).width();
    $('.nav-menu li').removeClass('active');
    $(this).addClass('active');
    
    if($(this).index()!=$('.nav-menu li:last-child').index()){
        if (width < 992) {
            $('.navbar-toggler').trigger('click');
            $root.animate({
                scrollTop: $("#systalk-section-" + ($(this).index() + 1)).offset().top - 74
            }, 500);
        }
        else{
            $root.animate({
                scrollTop: $("#systalk-section-" + ($(this).index() + 1)).offset().top - 86
            }, 500);

        }
    }else{
        if (width < 992) {
            $('.navbar-toggler').trigger('click');
            
        }
    }
    
});
$('.godown').click(function(e) {
    var width = $(window).width();
    if (width < 992) {
        $root.animate({
            scrollTop: $("#systalk-section-1").offset().top - 74
        }, 500);
    }
    else{
        $root.animate({
            scrollTop: $("#systalk-section-1").offset().top - 86
        }, 500);

    }
});

function delay(classname,i){
    $(classname).eq(i).addClass('animate');
}
$(window).scroll(function(event){

    var y_cordinate = $(this).scrollTop();
    var systalkai = $('section.systalk-ai').position();
    var nlu = $('section.nlu').position();

    var systalkaiht = $('section.systalk-ai').height();
    var nluht = $('section.nlu').height();

    if(systalkai != undefined && systalkaiht != undefined) {
        if(y_cordinate >= (systalkai.top - systalkaiht) && y_cordinate <= (systalkai.top + systalkaiht)){
            $('.systalk-ai .circles').addClass('animate');
            $('.systalk-ai .circles .tag').each(function(){
                $index = $(this).index();
                setTimeout("delay('.systalk-ai .circles .tag',"+($index-1)+")",($index)*300);
            });

        }else{
            $('.systalk-ai .circles').removeClass('animate');
            $('.systalk-ai .circles .tag').removeClass('animate');
        };
    }

    if(nlu != undefined ) {
        if(y_cordinate >= (nlu.top - nluht) && y_cordinate <= (nlu.top + nluht)){

            $('.left-title .item').each(function(){
                $index = $(this).index();
                setTimeout("delay('.left-title .item',"+$index+")",($index)*300);
            });

        }else{
            $('.left-title .item').removeClass('animate');
        };
    }

});

