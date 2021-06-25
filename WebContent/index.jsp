<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>図書管理システム | メインメニュー</title>
<!--
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<style type="text/css">
.slider{
    width: 60%;
    margin: 0 auto;
    overflow: hidden;
    display: flex;
    flex-flow: row nowrap;
    justify-content: flex-start;
    align-items: center;
}

.slider__content{
    min-width: 100%;
    animation: sliderAnime 12s ease-in-out infinite;
}

@keyframes sliderAnime{
    0%{

    }
    16.6%{
        transform: translateX(0);
    }
    33.3%{
        transform: translateX(-100%);
    }
    50%{
        transform: translateX(-100%);
    }
    66.6%{
        transform: translateX(-200%);
    }
    83.2%{
        transform: translateX(-200%);
    }
    100%{
        transform: translateX(0);
    }
}

.slider__img{
    max-width: 100%;
    min-width: 100%;
}

#menu li{
  border-bottom:1px dotted #000;
  list-style:none;
  padding:10px;
  width:400px;
}
a{
  color:#000;
  text-decoration:none;
}
span{
  background-color:#2388b8;
  border-radius:3px;
  color:#fff;
  display:inline-block;
  margin-right:20px;
  padding:5px;
}
time{
  display: inline-block;
  font-weight: bold;
  margin-right:40px;
}
p{
  color:#1c448d;
  display: inline-block;
  text-decoration:underline;
}
.new::after{
  content:"NEW";
  color: #d10606;
  font-size: 1.1rem;
  border: 1px solid #d10606;
  padding: 4px 8px;
  margin: 0 0 0 20px;
  display: inline-block;
  line-height: 1;
}
	</style>
	-->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<style type="text/css">
	.split{
    display: table;
    width: 100%;
}
.split-item{
    display: table-cell;
    padding: 80px;
    width: 50%;
}
.split-left{
    background: #EEE;
   color: #000;
    position: relative;
}
.split-left__inner{
    height: 100%;
    position: fixed;
    width: 50%;
}
.split-right__inner{
    height: 8000px;
}
@media screen and (max-width:1024px) {
    .split{
        display: block;
    }
    .split-item{
        display: block;
        width: auto;
    }
    .split-left__inner{
        position: inherit;
        width: auto;
    }
}
#tete p{
    font-size: 25px;
    font-weight:bold;
    display: block;
    margin-left: auto;
    margin-right: auto;
    }
    .slider{
    width: 100%;
    margin: 0 auto;
    overflow: hidden;
    display: flex;
    flex-flow: row nowrap;
    justify-content: flex-start;
    align-items: center;
}

.slider__content{
    min-width: 100%;
    animation: sliderAnime 12s ease-in-out infinite;
}

@keyframes sliderAnime{
    0%{

    }
    16.6%{
        transform: translateX(0);
    }
    33.3%{
        transform: translateX(-100%);
    }
    50%{
        transform: translateX(-100%);
    }
    66.6%{
        transform: translateX(-200%);
    }
    83.2%{
        transform: translateX(-200%);
    }
    100%{
        transform: translateX(0);
    }
}

.slider__img{
    max-width: 100%;
    min-width: 100%;
}

#menu li{
  border-bottom:1px dotted #000;
  list-style:none;
  padding:10px;
  width:400px;
}
a{
  color:#000;
  text-decoration:none;
}
span{
  background-color:#2388b8;
  border-radius:3px;
  color:#fff;
  display:inline-block;
  margin-right:20px;
  padding:5px;
}
time{
  display: inline-block;
  font-weight: bold;
  margin-right:40px;
}
#ttt p{
  color:#1c448d;
  display: inline-block;
  text-decoration:underline;
}
#new::after{
  content:"NEW";
  color: #d10606;
  font-size: 1.1rem;
  border: 1px solid #d10606;
  padding: 4px 8px;
  margin: 0 0 0 20px;
  display: inline-block;
  line-height: 1;
}
	</style>
</head>

<body>
<jsp:include page="/menu.jsp" />

<div class="split">
    <div class="split-item split-left">
        <div class="split-left__inner">
             <img src="/0608_problem/facebook_profile_image.png" width="400px">
             <br>
             <div id="tete">
             <p>ようこそ、<br>
             新宿図書館図書管理システムへ<br>
             <p>
             </div>

              システムに関するお問い合わせは<br>下記連絡先までお願い致します。<br>
              -----------------------------------------------------------------------------<br>
             〒111-2222 東京都新宿区西新宿1-2-3 XXXビル13階 <br>
             株式会社ABCシステム システム開発部<br>
             Tel: 00-1111-2222<br>
             E-mail: ABCsystem@aaa.com<br>
       -----------------------------------------------------------------------------<br>
        </div><!--split-left__inner-->
    </div><!--split-item split-left-->
    <div class="split-item split-right">
        <div class="split-right__inner">
          <div id="tete"><p>新宿図書館の風景</p></div>
            <div class="slider">
    <div class="slider__content"><img src="/0608_problem/library.jpg" alt="slider_image1" class="slider__img"></div>
    <div class="slider__content"><img src="/0608_problem/library2.jpg" alt="slider_image2" class="slider__img"></div>
    <div class="slider__content"><img src="/0608_problem/uwaa.PNG" alt="slider_image3" class="slider__img"></div>
</div>
<br><br><br>
<div id="tete"><p>業務に関するお知らせ</p></div>
<ul id="menu">
  <li>
    <a href="#">
      <span style="background-color:#FF0000">重要</span>
      <time datetime="2019-01-01">[ 2021.06.23 ]</time>
      <p id="new">石田、パチンコをやめる</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span>周知</span>
      <time datetime="2019-01-01">[ 2021.06.21 ]</time>
      <p id="new">近隣図書館司書一名にコロナ陽性反応</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span style="background-color:#00d400">連絡</span>
      <time datetime="2019-01-01">[ 2021.05.30 ]</time>
      <p>ニュースニュースニュースニュースニュース</p>
    </a>
  </li>
</ul>

<br><br><br>
<div id="tete"><p>図書館カレンダー</p></div>
<iframe src="https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23ffffff&amp;ctz=Asia%2FTokyo&amp;src=eS5ha2lub2kwMTI1QGdtYWlsLmNvbQ&amp;src=amEuamFwYW5lc2UjaG9saWRheUBncm91cC52LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;color=%237986CB&amp;color=%230B8043" style="border:solid 1px #777" width="500" height="500" frameborder="0" scrolling="no"></iframe>
        </div><!--split-right__inner-->
    </div><!--split-item split-right-->
</div><!--split-->

<!--
<div class="slider">
    <div class="slider__content"><img src="/0608_problem/library.jpg" alt="slider_image1" class="slider__img"></div>
    <div class="slider__content"><img src="/0608_problem/library2.jpg" alt="slider_image2" class="slider__img"></div>
    <div class="slider__content"><img src="/0608_problem/uwaa.PNG" alt="slider_image3" class="slider__img"></div>
</div>
<br>


<table>
<tr>
<td>
<center><h2><b>お知らせ</b></h2></center>
<ul id="menu">
  <li>
    <a href="#">
      <span style="background-color:#FF0000">重要</span>
      <time datetime="2019-01-01">[ 2021.06.23 ]</time>
      <p class="new">石田、パチンコをやめる</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span>周知</span>
      <time datetime="2019-01-01">[ 2021.06.21 ]</time>
      <p class="new">近隣図書館司書一名にコロナ陽性反応</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span style="background-color:#00d400">連絡</span>
      <time datetime="2019-01-01">[ 2021.05.30 ]</time>
      <p>ニュースニュースニュースニュースニュース</p>
    </a>
  </li>
</ul>
</td>
<td>
<iframe src="https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23ffffff&amp;ctz=Asia%2FTokyo&amp;src=eS5ha2lub2kwMTI1QGdtYWlsLmNvbQ&amp;src=amEuamFwYW5lc2UjaG9saWRheUBncm91cC52LmNhbGVuZGFyLmdvb2dsZS5jb20&amp;color=%237986CB&amp;color=%230B8043" style="border:solid 1px #777" width="500" height="500" frameborder="0" scrolling="no"></iframe>
<td>
<tr>
</table>
 -->

</body>
</html>