<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../echarts/echarts.min.js"></script>
    <script src="../echarts/china.js"></script>
    <script src=" http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            appkey: "BC-ad158fc1bc604d6cb74008b24def505e"
        });
        goEasy.subscribe({
            channel: "show",
            onMessage: function (message) {
                var data = JSON.parse(message.content);
                $("#accept").append("<h1>" + data + "</h1>")
            }
        });

        function send() {
            $("#btn").click(function () {
                var val = $("#inp").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/send?information=" + val,
                    datatype: "json",
                    type: "POST",
                    success: function (da) {
                    }
                })
            })
        }
    </script>
</head>
<body>
<input type="text" id="inp" onclick="send()">
<button id="btn">发送</button>
<div id="accept"></div>
</body>
</html>