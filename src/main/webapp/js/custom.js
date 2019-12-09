$(function () {
  /* 按下GoTop按鈕時的事件 */
  $('#GoTop').click(function () {
      $('html,body').animate({
          scrollTop: 0
      }, 'slow'); /* 返回到最頂上 */
      return false;
  });

  /* 偵測卷軸滑動時，往下滑超過400px就讓GoTop按鈕出現 */
  $(window).scroll(function () {
      if ($(this).scrollTop() > 400) {
          $('#GoTop').fadeIn();
      } else {
          $('#GoTop').fadeOut();
      }
  });
  /* 按下聯絡我們的事件 */
  $('#m-contact').click(function () {
      $('html,body').animate({
          scrollTop: $('#contact').offset().top
      }, 800);
  });
});



/** 2019.10.21 有點問題先註解 by Richard
$(document).ready(function () {
  $('#menu-hamburger').click(function () {
      $(this).toggleClass('open');
  });
})
*/
  