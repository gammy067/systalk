jQuery("#carousel").owlCarousel({
  // 自動播放關閉
  // autoplay: true,
  lazyLoad: true,
  loop: true,
  margin: 40,
   /*
  animateOut: 'fadeOut',
  animateIn: 'fadeIn',
  */
  responsiveClass: true,
  autoHeight: true,
  autoplayTimeout: 7000,
  smartSpeed: 800,
  nav: true,
  responsive: {
    0: {
      center: true,
      items: 1.3
    },

    600: {
      center: true,
      items: 1.3,
    },
    769: {
      center: true,
      items: 2.3
    },

    1024: {
      center: true,
      items: 3.5
    },

    1366: {
      items: 4
    }
  }
});