<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<body>
<div class="row">

    <div class="col-sm-3">

    </div>
    <div class="col-sm-6" id="main" style="width: 800px;height:400px;"></div>

    <div class="col-sm-3">

    </div>
</div>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->


<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户注册七天走势图'
        },
        tooltip: {},
        legend: {
            data: ['注册数量']
        },

        xAxis: {
            data: ["2019-10-11", "2019-10-10", "2019-10-09", "2019-10-08", "2019-10-07", "2019-10-06", "2019-10-05"]
        },
        yAxis: {},
        series: [{
            name: '注册数量',
            type: 'line',
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url: "${pageContext.request.contextPath}/user/findAll2",
        datatype: "json",
        type: "POST",
        success: function (da) {
            myChart.setOption({
                series: [{data: da}]
            });
        }
    });


    var goEasy = new GoEasy({
        appkey: "BC-41105467e17d4745869fc7051df656ee"
    });
    goEasy.subscribe({
        channel: "164channel",
        onMessage: function (message) {
            var data = JSON.parse(message.content);
            myChart.setOption({
                series: [{data: data}]
            });
        }
    });


</script>

</body>
</html>