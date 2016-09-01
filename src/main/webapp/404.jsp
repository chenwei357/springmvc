<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>404错误页面</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/404.css">
    <link rel="stylesheet" href="http://dreamsky.github.io/main/blog/common/init.css">
        <script src="http://dreamsky.github.io/main/blog/common/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scriptalizer.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            $('#parallax').jparallax({});
        });
    </script>
</head>
<body>
    <div id="content" class="narrowcolumn">

<div id="parallax">
    <div class="error1">
        <img src="${pageContext.request.contextPath}/assets/wand.jpg" alt="Mauer" />
    </div>
    <div class="error2">
        <img src="${pageContext.request.contextPath}/assets/licht.png" alt="Licht" />
    </div>
    <div class="error3">
        <img src="${pageContext.request.contextPath}/assets/halo1.png" alt="Halo1" />
    </div>
    <div class="error4">
        <img src="${pageContext.request.contextPath}/assets/halo2.png" alt="Halo2" />
    </div>
    <div class="error5">
        <img src="${pageContext.request.contextPath}/assets/batman-404.png" alt="Batcave 404" />
    </div>
</div>
<div class="footer-banner" style="width:728px; margin:0 auto"></div>
</div>
    <script src="http://dreamsky.github.io/main/blog/common/init.js"></script>
</body>
</html>
