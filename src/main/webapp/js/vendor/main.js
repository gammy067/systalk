$(document).ready(function() {
    // Get IE or Edge browser version
    var version = detectIE();

    if (version === false) {
        // Not IE/Edge, do nothing
    } else if (version >= 12) {
        // Edge
        $('html').addClass(' edge');
    } else {
        // IE*
        $('html').addClass(' ie' + version);
    }

    // detectIE()
    function detectIE() {
        var ua = window.navigator.userAgent;

        var msie = ua.indexOf('MSIE ');
        if (msie > 0) {
            // IE 10 or older => return version number
            return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
        }

        var trident = ua.indexOf('Trident/');
        if (trident > 0) {
            // IE 11 => return version number
            var rv = ua.indexOf('rv:');
            return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
        }

        var edge = ua.indexOf('Edge/');
        if (edge > 0) {
            // Edge (IE 12+) => return version number
            return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
        }

        // other browser
        return false;
    }


    // Detect Touch Device
    if ('ontouchstart' in window || 'ontouch' in window) {
        document.body.classList.add('touch-device');
    } else {
        document.body.classList.add('non-touch-device');
    }

    // Custom RWD Menu - Open/Close
    $('.mgop__menu a, .mgop__menu span').click(function(e) {
        $('.mgop__menu').toggleClass('open');
    });

    // Custom RWD Menu
    var $root = $('html, body');
    $('.mgop__menu a').click(function(e) {
        e.preventDefault();
        $('.mgop__menu a').removeClass('active');
        $(this).addClass('active');

        var navTxt = $(this).text();
        $('.mgop__menu span').text(navTxt);

        $root.animate({
            scrollTop: $($.attr(this, 'href')).offset().top
        }, 500);
        return false;
    });

    // Close Custom RWD Menu When Click On Other Elements
    $(document).click(function(e) {
        if ($(e.target).closest('.mgop__menu').length == 0) {
            $('.mgop__menu').removeClass('open');
        }
    })

    // 防呆用，視窗大小變化時自動關閉 Custom RWD Menu
    $(window).resize(function() {
        $('.mgop__menu').removeClass('open');
    });

    // Toggle .hover Class, instead of :hover on touch device
    var event = ('ontouchstart' in window) ? 'click' : 'mouseenter mouseleave';
    $('.mgop__card-view li').on(event, function() {
        $(this).toggleClass('hover');
    });
});

$(window).load(function() {
    // Fancybox.js
    $('[data-fancybox]').fancybox({
        padding: '0',
        minWidth: '280',
        transitionEffect: 'fade',
        transitionDuration: 366
    });

    // Custom RWD Menu - Open/Close
    $('.chatF__menu a, .chatF__menu span').click(function(e) {
        $('.chatF__menu').toggleClass('open');
    });

    // Custom RWD Menu
    var $root = $('html, body');
    $('.chatF__menu a').click(function(e) {
        e.preventDefault();
        $('.chatF__menu a').removeClass('active');
        $(this).addClass('active');

        var navTxt = $(this).text();
        $('.chatF__menu span').text(navTxt);

        $root.animate({
            scrollTop: $($.attr(this, 'href')).offset().top
        }, 500);
        return false;
    });

    // 防呆用，視窗大小變化時自動關閉 Custom RWD Menu
    $(window).resize(function() {
        $('.chatF__menu').removeClass('open');
    });

    // Custom Tabs
    $('.chatF__tabs__tab li').click(function() {
        $('[class*="chatF__tabs__"]').find('.active').removeClass("active");
        $(this).addClass('active');

        var tabTriggerIndex = $(this).index();
        var tabTarget = $('.chatF__tabs__content img');
        tabTarget.each(function() {
            if ($(this).index() == tabTriggerIndex) {
                $(this).addClass('active');
            }
        });
    });


    var $root = $('html, body');
    $('.menubar a').click(function(e) {
        var width = $(window).width();
        e.preventDefault();
        $('.menubar a').removeClass('active');
        $(this).addClass('active');

        $('.menubar').toggleClass('open');

        if (!$(".menubar").hasClass("open")) {
            $root.animate({
                scrollTop: $("#SYS-rpa-" + ($(this).index() + 1)).offset().top - 50
            }, 500);
            return false;
            $('.menubar').removeClass('open');
        }
        if (width > 767) {
            $root.animate({
                scrollTop: $("#SYS-rpa-" + ($(this).index() + 1)).offset().top - 75
            }, 500);
            return false;
        }



    });





    $(window).resize(function() {
        $('.menubar').removeClass('open');
    });


    // face-ai ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
    // Custom RWD Menu - Open/Close
    $('.face-ai__menu a, .face-ai__menu span ').click(function(e) {
        $('.face-ai__menu').toggleClass('open');
    });

    // Custom RWD Menu
    var $root = $('html, body');
    $('.face-ai__menu a').click(function(e) {
        e.preventDefault();
        $('.face-ai__menu a').removeClass('active');
        $(this).addClass('active');

        var navTxt = $(this).text();
        $('.face-ai__menu span').text(navTxt);

        $root.animate({
            scrollTop: $($.attr(this, 'href')).offset().top
        }, 500);
        return false;
    });

    // 防呆用，視窗大小變化時自動關閉 Custom RWD Menu
    $(window).resize(function() {
        $('.face-ai__menu').removeClass('open');
    });

    // Custom Tabs
    $('.face-ai__tabs__tab li').click(function() {
        $('[class*="face-ai__tabs__"]').find('.active').removeClass("active");
        $(this).addClass('active');

        var tabTriggerIndex = $(this).index();
        var tabTarget = $('.face-ai__tabs__content img');
        tabTarget.each(function() {
            if ($(this).index() == tabTriggerIndex) {
                $(this).addClass('active');
            }
        });
    });


});