<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>図書管理システム | メインメニュー</title>
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

li{
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
</head>

<body>
<jsp:include page="/menu.jsp" />


<div class="slider">
    <div class="slider__content"><img src="https://dubdesign.net/wp-content/uploads/2020/05/0515_illustrator_eyecatch2.jpg" alt="slider_image1" class="slider__img"></div>
    <div class="slider__content"><img src="https://dubdesign.net/wp-content/uploads/2020/05/0514_inhouse_designereyecatch.jpg" alt="slider_image2" class="slider__img"></div>
    <div class="slider__content"><img src="https://dubdesign.net/wp-content/uploads/2020/05/0510_personaeyecatch.jpg" alt="slider_image3" class="slider__img"></div>
</div>
<br>


<table>
<tr>
<td>
<center><h2><b>お知らせ</b></h2></center>
<ul>
  <li>
    <a href="#">
      <span>重要</span>
      <time datetime="2019-01-01">[ 2021.06.23 ]</time>
      <p class="new">石田、パチンコをやめる</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span>周知</span>
      <time datetime="2019-01-01">[ 2019.01.01 ]</time>
      <p class="new">ニュースニュースニュースニュースニュース</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span>連絡</span>
      <time datetime="2019-01-01">[ 2019.01.01 ]</time>
      <p>ニュースニュースニュースニュースニュース</p>
    </a>
  </li>
  <li>
    <a href="#">
      <span>重要</span>
      <time datetime="2019-01-01">[ 2019.01.01 ]</time>
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


</body>
</html>