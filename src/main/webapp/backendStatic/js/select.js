/*
Reference: http://jsfiddle.net/BB3JK/47/
*/



$('select').each(function(){
    var $this = $(this), numberOfOptions = $(this).children('option').length;

    $this.addClass('select-hidden');
    $this.wrap('<div class="select"></div>');
    $this.after('<div class="select-styled"></div>');

    var $styledSelect = $this.next('div.select-styled');
    
    // 預設值永遠為第一個
    //$styledSelect.text($this.children('option').eq(0).text());
    
    // 改為選中的那個選項 modify by richard 2019.10.03
    $styledSelect.text($this.children('option:selected').text());

    var $list = $('<ul />', {
        'class': 'select-options'
    }).insertAfter($styledSelect);

    for (var i = 0; i < numberOfOptions; i++) {
        $('<li />', {
            text: $this.children('option').eq(i).text(),
            rel: $this.children('option').eq(i).val()
        }).appendTo($list);
    }

    var $listItems = $list.children('li');

    $styledSelect.click(function(e) {
        e.stopPropagation();
        $('div.select-styled.active').not(this).each(function(){
            $(this).removeClass('active').next('ul.select-options').hide();
        });
        $(this).toggleClass('active').next('ul.select-options').toggle();
    });

    $listItems.click(function(e) {
        e.stopPropagation();
        $styledSelect.text($(this).text()).removeClass('active');
        $this.val($(this).attr('rel'));
        $list.hide();
        //console.log($this.val());
    });

    $(document).click(function() {
        $styledSelect.removeClass('active');
        $list.hide();
    });

})

/*----- 輪播 -----*/
// var banner_now = 0,$banner_now;
// // var CarouselBanner = setInterval('ChangeBanner()',1000);
// // ChangeBanner();
// function ChangeBanner(){
//     $banner_now = $('.slick-dots li').eq(banner_now);
//     //console.log($banner_now.length);
//     $banner_now.trigger('click');
//     $banner_now.next().length > 0 ? banner_now ++ : banner_now = 0;

// }



$('.select-options li').click(function(){
    console.log($(this).attr('rel'));
    $('#status').html(($(this).html()));
    //$(this).attr('rel') === 'red' ?
    $('#status_light').removeAttr('class').addClass('light_'+$(this).attr('rel'));
});
$('body')
.on('click','.menu ul li.btn',function(){
    $('.ui-blackCover').show();
    $('.ui-contextop').show();
    $('.'+$(this).attr('id')).show();
})
.on('click','.wind button',function(){
    $('.ui-blackCover').hide();
    $('.ui-contextop').hide();
    $('.wind>div').hide();
})
.on('click','.tabs li i',function(){
    var $li = $(this).parents('li');

    if($li.hasClass('active')){
        if($li.next().length){
            $li.next().trigger('click');
        }
        else if($li.prev().length){
            $li.prev().trigger('click');
        }
        else{
            $('.bookmark').fadeOut('fast');
        }
    }
    $li.remove();
})
.on('click','.detail a',function(){
    var tab = $(this).attr('href').split('#')[1];
    if(tab){
        $('.bookmark').fadeIn('fast');
        if(!$('.tabs a[href="#'+tab+'"]').length){//沒有標籤
            $('.tabs').append('<li><a href="#'+tab+'">'+$(this).html()+'</a><i>×</i></li>');
            // /
        }
        $('.tabs a[href="#'+tab+'"]').trigger('click');
    }
})
.on('click','.clear_col',function(){
    $(this).parent().siblings().find('input').each(function(){
        $(this).val('');
    })
})
.on('click','#search',function(){
    $('.search_box').slideDown('slow');
})
.on('click','.table_two tbody tr',function(){
    $('.rec_box').slideUp('slow');
    $('.rec_box2').slideDown('slow');

})
.on('click','#BackToRecord',function(){
    $('.rec_box2').slideUp('slow');
    $('.rec_box').slideDown('slow');
})
// .on('click','.tabs li:nth-child(2)',function(){
//     console.log('tab click');
//     ssb.scrollbar('container2');
// }) // scrollbar initialization
.on('click','input.allselect',function(){
      if($(this).attr("checked")){
        $(this).removeAttr("checked");
        $(this).parents('thead').next().find("input[type='checkbox']").prop("checked", false);
      }
      else{
        $(this).attr("checked","checked");
        $(this).parents('thead').next().find("input[type='checkbox']").prop("checked", true);
      }
});



$(function(){
        // 預設顯示第一個 Tab
        var _showTab = 0;
        var $defaultLi = $('ul.tabs li').eq(_showTab).addClass('active');
        $($defaultLi.find('a').attr('href')).siblings().hide();

        // 當 li 頁籤被點擊時...
        // 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
        $('body').on('click','ul.tabs li',function() {
        //$('ul.tabs li').click(function() {
            //console.log($(this));
            // 找出 li 中的超連結 href(#id)
            var $this = $(this),
                _clickTab = $this.find('a').attr('href');
            // 把目前點擊到的 li 頁籤加上 .active
            // 並把兄弟元素中有 .active 的都移除 class
            $this.addClass('active').siblings('.active').removeClass('active');
            // 淡入相對應的內容並隱藏兄弟元素
            $(_clickTab).stop(false, true).fadeIn().siblings().hide();

            return false;
        }).find('a').focus(function(){
            this.blur();
        });
});
