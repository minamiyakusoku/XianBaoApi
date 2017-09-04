require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});
require(
    [
        'echarts',
        'echarts/chart/line'
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('main'));

        option = {
            title: {
                text: "xx县社保公积金缴费统计",
                x: "center"
            },
            tooltip: {
                trigger: "item",
                formatter: "{a} <br/>{b} : {c}"
            },
            legend: {
                x: 'left',
                data: ["社保", "公积金"]
            },
            xAxis: [
                {
                    type: "category",
                    name: "时间/月",
                    splitLine: {show: false},
                    data: ["一", "二", "三", "四", "五", "六", "七", "八", "九","十","十一","十二"]
                }
            ],
            yAxis: [
                {
                    type: "log",
                    name: "金额/万元"
                }
            ],
            toolbox: {
                show: true
            },
            calculable: true,
            series: [
                {
                    name: "社保",
                    type: "line",
                    data: [1, 3, 9, 27, 81, 47, 71, 33,23,22,11,1]

                },
                {
                    name: "公积金",
                    type: "line",
                    data: [1, 2, 4, 8, 16, 32, 64, 18, 25,22,33,1]

                }
            ]
        };


        // 为echarts对象加载数据
        myChart.setOption(option);
    }
);
