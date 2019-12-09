<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- 整理 -->
    <%--
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link href="<%=request.getContextPath()%>/css/plugin/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"
    />
    <link href="<%=request.getContextPath()%>/css/plugin/bootstrap-datepicker.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/css/plugin/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"
        media="all" />
    <link href="<%=request.getContextPath()%>/css/plugin/prism.css" rel="stylesheet" type="text/css" />

    <link href="<%=request.getContextPath()%>/css/plugin/article.css" rel="stylesheet" type="text/css" media="all" />
    <link href="<%=request.getContextPath()%>/css/plugin/datatable.css" rel="stylesheet" type="text/css" media="all" />
    <link href="<%=request.getContextPath()%>/css/plugin/select.css" rel="stylesheet" type="text/css" media="all" />
    <link href="<%=request.getContextPath()%>/css/plugin/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    --%>

    <%--<link href="<%=request.getContextPath()%>/css/plugin/jquery.smartmenus.bootstrap.css" rel="stylesheet"
    type="text/css" media="all" /> --%>
    <!-- END 整理 -->

    <!-- Bootstrap -->
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/slick.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/slick-theme.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/article.css" rel="stylesheet">
    <!--select-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/select.css">

    <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/popper.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/slick.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>

    <!--select-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>


    <script type="text/javascript">
        $(function () {
            $('.single-item').slick({
                arrows: false,
                dots: true,
                infinite: true,
                autoplay: true,
                autoplaySpeed: 2000,
            });
        });
    </script>

    <title>systalk</title>
</head>

<body>
    <div class="wrapper">
        <!-- header -->
        <script src="<%=request.getContextPath()%>/js/header.js"></script>
        <!-- 活動廣告管理 Modal -->
        <div class='modal fade' id='eventModal' tabindex='-1' role='dialog' aria-labelledby='eventModalTitle'
            aria-hidden='true'>
            <div class='modal-dialog modal-dialog-centered modal-lg' role='document'>
                <div class='modal-content modal-style'>
                    <div class='modal-header'>
                        <h5 class='modal-title' id='loginModalTitle'>活動廣告管理</h5>
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                            <span aria-hidden='true'>&times;</span>
                        </button>
                    </div>
                    <div class='modal-body'>
                        <div class="row">
                            <div class="col-12 col-md-4 mb-3 mb-md-0">
                                <div class="ad-img">
                                    <img src="images/ad@2x.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-12 col-md-8">
                                <div class="mb-3">
                                    <div class="justify-content-start mr-auto">1.上傳圖片</div>
                                    <div class="upload">
                                        <div class="form-row">
                                            <div class="col-8">
                                                <input type="text" class="form-control" id="" placeholder="" value="">
                                            </div>
                                            <div class="col-4">
                                                <input class="choose-file" type="file" id="choose-file" required="">
                                                <label for="choose-file" class="upload-file" data-text="filename">
                                                    <div class="btn btn-up" type="button">選擇檔案</div>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="ad-tip">建議尺寸：圖片寬度至少700px</div>
                                    </div>
                                </div>
                                <div class="mb-4">
                                    <div class="desc d-flex align-items-center">
                                        <div class="justify-content-start mr-auto">2.活動連結網址</div>
                                        <div class="modalFeatured">
                                            <div class="upload justify-content-end mb-1">
                                                <img src="images/icon-external-link@2x.png" alt="" border="0"
                                                    class="img-fluid">
                                            </div>
                                        </div>
                                    </div>
                                    <input class="form-control mb-3" type="text"
                                        placeholder="http://www.fakepage.com/tpu/category1/article01">
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <button type="submit" class="btn btn-primary btn-blue btn-block">活動下架</button>
                                    </div>
                                    <div class="col-6">
                                        <button type="submit"
                                            class="btn btn-secondary btn-green btn-block">確定儲存</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /活動廣告管理 Modal end -->
        <!-- 登入 Modal -->
        <div class='modal fade' id='loginModal' tabindex='-1' role='dialog' aria-labelledby='loginModalTitle'
            aria-hidden='true'>
            <div class='modal-dialog modal-dialog-centered' role='document'>
                <div class='modal-content modal-style'>
                    <div class='modal-header'>
                        <h5 class='modal-title' id='loginModalTitle'>會員登入</h5>
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                            <span aria-hidden='true'>&times;</span>
                        </button>
                    </div>
                    <div class='modal-body'>
                        <div class='caption'>透過社群帳號登入</div>
                        <div class='row'>
                            <div class='col-sm-6 col-xs-12 mb-3 mb-sm-0'>
                                <a href='' class='d-block'>
                                    <div class='btn-login-fb'>
                                        <span class='icon-login-fb'></span> facebook
                                    </div>
                                </a>
                            </div>
                            <div class='col-sm-6 col-xs-12'>
                                <a href='' class='d-block'>
                                    <div class='btn-login-tp'>
                                        <span class='icon-login-tp'></span> ThinkPower
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class='divider-box mt-4 mb-3'>
                            <div class='text-divider'>or</div>
                        </div>
                        <div class='caption'>昕力大學註冊帳號登入</div>
                        <form>
                            <input class='form-control mb-3' type='text' placeholder='帳號'>
                            <input type='password' class='form-control mb-3' type='text' placeholder='密碼'>
                            <a href='#' class='btn-login-password mb-3'><span class='icon-password'></span>忘記密碼</a>
                            <button type='submit' class='btn btn-primary btn-blue btn-block'>登入</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /登入 Modal end -->
        <!-- 註冊 Modal -->
        <div class='modal fade' id='registerModal' tabindex='-1' role='dialog' aria-labelledby='registerModalTitle'
            aria-hidden='true'>
            <div class='modal-dialog modal-dialog-centered' role='document'>
                <div class='modal-content modal-style'>
                    <div class='modal-header'>
                        <h5 class='modal-title' id='registerModalTitle'>會員註冊</h5>
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
                            <span aria-hidden='true'>&times;</span>
                        </button>
                    </div>
                    <div class='modal-body'>
                        <form>
                            <div class='form-group'>
                                <label for=''>電子郵件</label>
                                <input class='form-control mb-3' type='text' placeholder='您的電子郵件即為您的使用者帳號'>
                            </div>
                            <div class='form-group'>
                                <label for=''>暱稱</label>
                                <input class='form-control mb-3' type='text' placeholder='請輸入暱稱'>
                            </div>
                            <div class='form-group'>
                                <label for=''>公司名稱</label>
                                <input class='form-control mb-3' type='text' placeholder='請輸入公司名稱'>
                            </div>
                            <div class='form-group'>
                                <label for=''>職稱</label>
                                <input class='form-control mb-3' type='text' placeholder='請輸入職稱'>
                            </div>
                            <div class='form-group'>
                                <label for=''>訂閱類別 (新文章發佈時將寄送通知)</label>
                                <div class=''>
                                    <span class='tag-subscription active'>Xamarin與行動開發</span>
                                    <span class='tag-subscription'>網頁技術</span>
                                    <span class='tag-subscription'>Java</span>
                                    <span class='tag-subscription active'>.Net</span>
                                    <span class='tag-subscription active'>企業系統軟體</span>
                                    <span class='tag-subscription'>資料庫技術</span>
                                    <span class='tag-subscription'>專案實務</span>
                                </div>
                            </div>
                            <div class='row'>
                                <div class='col-6'>
                                    <button type='submit' class='btn btn-primary btn-blue btn-block'>確定</button>
                                </div>
                                <div class='col-6'>
                                    <button type='submit' class='btn btn-secondary btn-gray btn-block'>取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /註冊 Modal end -->
        <!-- /header end -->
        <!-- popular-article -->
        <section class="popular-article padding-header mt-3">
            <div class="container">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- 輪播文章 -->
                            <section class="single-item row slider slider-popular">
                                <div class="col-12">
                                    <a href="">
                                        <div class="card-slider">
                                            <div class="tag">Java</div>
                                            <div class="article-img d-flex align-items-center">
                                                <img src="images/demo-photo-3.jpg" alt="">
                                            </div>
                                            <div class="title">
                                                <div class="txt">11111 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的 Hibernate
                                                    謬誤，正威脅著你的系統效能，種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的 Hibernate
                                                    謬誤，正威脅著你的系統效能，</div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-12">
                                    <a href="">
                                        <div class="card-slider">
                                            <div class="tag">Java</div>
                                            <div class="article-img d-flex align-items-center">
                                                <img src="images/demo-photo-3.jpg" alt="">
                                            </div>
                                            <div class="title">
                                                <div class="txt">22222 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的 Hibernate
                                                    謬誤，正威脅著你的系統效能，種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的 Hibernate
                                                    謬誤，正威脅著你的系統效能，</div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-12">
                                    <a href="">
                                        <div class="card-slider">
                                            <div class="tag">Java</div>
                                            <div class="article-img d-flex align-items-center">
                                                <img src="images/demo-photo-3.jpg" alt="">
                                            </div>
                                            <div class="title">
                                                <div class="txt">333333 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                    Hibernate
                                                    謬誤，正威脅著你的系統效能，種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的 Hibernate
                                                    謬誤，正威脅著你的系統效能，</div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </section>
                            <!-- / 輪播文章 end -->
                        </div>
                    </div>
                </div>


            </div>
        </section>

        <section class="category-article mt-3">
            <div class="container">

                <div class="box">
                    <div class="row">
                        <div class="col-12" style="text-align:center">
                            <div class="title">SysTalk.ai</div>
                            <div class="txt">
                                文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字文字
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="category-article mt-3">
                <div class="container">
    
                    <div class="box">
                        <div class="row">
                            <div class="col-12" style="text-align:center">
                                <div class="title">SysTalk.Chat</div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        <!-- /popular-article end -->
        <!-- article -->
        <section class="category-article mt-3">
            <div class="container">
                <div class="row">
                    <!-- 主題文章 -->
                    <div class="col-12 col-lg-9">
                        <!-- Java -->
                        <div class="box mb-3">
                            <div class="at-title">Java</div>
                            <div class="tit-hr left mb-3"></div>
                            <div class="article-list">
                                <div class="row">
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex align-items-center">
                                        <div class="justify-content-start mr-auto num-tag">文章數量： <span>256</span></div>
                                        <div class="justify-content-end">
                                            <a href="#" class="btn-more">
                                                <span class="align-middle">更多文章</span>
                                                <div class="icon-more"></div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Java end -->
                        <!-- 企業系統軟體 -->
                        <div class="box mb-3">
                            <div class="at-title">企業系統軟體</div>
                            <div class="tit-hr left mb-3"></div>
                            <div class="article-list">
                                <div class="row">
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex align-items-center">
                                        <div class="justify-content-start mr-auto num-tag">文章數量： <span>256</span></div>
                                        <div class="justify-content-end">
                                            <a href="#" class="btn-more">
                                                <span class="align-middle">更多文章</span>
                                                <div class="icon-more"></div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /企業系統軟體 end -->
                        <!-- App開發 -->
                        <div class="box mb-3">
                            <div class="at-title">App開發</div>
                            <div class="tit-hr left mb-3"></div>
                            <div class="article-list">
                                <div class="row">
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex align-items-center">
                                        <div class="justify-content-start mr-auto num-tag">文章數量： <span>256</span></div>
                                        <div class="justify-content-end">
                                            <a href="#" class="btn-more">
                                                <span class="align-middle">更多文章</span>
                                                <div class="icon-more"></div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /App開發 end -->
                        <!-- 專案實務 -->
                        <div class="box mb-3">
                            <div class="at-title">專案實務</div>
                            <div class="tit-hr left mb-3"></div>
                            <div class="article-list">
                                <div class="row">
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex align-items-center">
                                        <div class="justify-content-start mr-auto num-tag">文章數量： <span>256</span></div>
                                        <div class="justify-content-end">
                                            <a href="#" class="btn-more">
                                                <span class="align-middle">更多文章</span>
                                                <div class="icon-more"></div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /專案實務 end -->
                        <!-- 網頁技術 -->
                        <div class="box mb-3">
                            <div class="at-title">網頁技術</div>
                            <div class="tit-hr left mb-3"></div>
                            <div class="article-list">
                                <div class="row">
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 col-md-4">
                                        <div class="card-article">
                                            <a href="">
                                                <div class="tag">次要類別</div>
                                                <div class="article-img">
                                                    <img src="images/demo-photo-3.jpg" alt="">
                                                </div>
                                                <div class="title">
                                                    <div class="txt">10 種常見的 Hibernate 謬誤，正威脅著你的系統效能，10 種常見種常見的
                                                        Hibernate
                                                        謬誤，正威脅著你的系統效能，</div>
                                                </div>
                                            </a>
                                            <div class="info">
                                                <div class="desc d-xl-flex align-items-center">
                                                    <div class="justify-content-start mr-auto">
                                                        <span class="date">2019/1/16</span>
                                                    </div>
                                                    <div class="justify-content-end">
                                                        <span class="click-like">
                                                            <span class="icon-article-like-default"></span>
                                                            <span class="like-count mr-2">65</span>
                                                        </span>
                                                        <span class="icon-article-view"></span>
                                                        <span class="view-count">588</span>
                                                    </div>
                                                </div>
                                                <div class="author">張朝銘</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 d-flex align-items-center">
                                        <div class="justify-content-start mr-auto num-tag">文章數量：<span>256</span></div>
                                        <div class="justify-content-end">
                                            <a href="#" class="btn-more">
                                                <span class="align-middle">更多文章</span>
                                                <div class="icon-more"></div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /網頁技術 end -->
                    </div>
                    <!-- /主題文章 end -->
                    <!-- right side -->
                    <div class="col-12 col-lg-3">
                        <!-- 技術分享 廣告區 -->
                        <div class="ad mb-3">
                            <a href="">
                                <img src="images/ad@2x.jpg" alt="">
                            </a>
                        </div>
                        <!-- /技術分享 廣告區 end -->
                        <!-- 熱門文章 -->
                        <div class="box side-article mb-3">
                            <div class="title">熱門文章</div>
                            <div class="tit-hr left mb-3"></div>
                            <div class="side-article-list">
                                <a href="">
                                    <div class="article-img"><img src="images/demo-photo.png" alt=""></div>
                                    <div class="title">
                                        <div class="txt">常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="side-article-list">
                                <a href="">
                                    <div class="article-img"><img src="images/bg-banner.jpg" alt=""></div>
                                    <div class="title">
                                        <div class="txt">常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="side-article-list">
                                <a href="">
                                    <div class="article-img"><img src="images/demo-photo.png" alt=""></div>
                                    <div class="title">
                                        <div class="txt">常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="side-article-list">
                                <a href="">
                                    <div class="article-img"><img src="images/demo-photo-2.png" alt=""></div>
                                    <div class="title">
                                        <div class="txt">常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對，以及常用 SQL 語法與 LINQ 比對
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <!-- /熱門文章 end -->
                    </div>
                    <!-- /right side end -->
                </div>
            </div>
        </section>
        <!-- /new article end -->

        <!-- footer -->
        <script src="<%=request.getContextPath()%>/js/footer.js"></script>
        <!-- /footer end -->

    </div>

    <p>
        <a href="?lang=en">en</a>
        <P>
            <a href="?lang=tw">tw</a>
            <br />

</body>

</html>