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

        var option = {
            title : {
                text: '各接口调用频数'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['社保','公积金','农保']
            },
            toolbox: {
                show : true
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'农保',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {color:'rgba(77,166,249,0.5)',areaStyle: {lineStyle:{
                        color:'rgb(77,166,249)'
                    },type: 'default'}}},
                    data:[10, 12, 21, 54, 260, 830, 710,10, 12, 21, 54, 260,11]
                },
                {
                    name:'公积金',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {color:'rgb(149,117,212)',areaStyle: {color:'rgba(149,117,212,0.4)',type: 'default'}}},
                    data:[30, 182, 434, 791, 390, 30, 10,30, 182, 434, 791, 390,22]
                },
                {
                    name:'社保',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {color:'rgb(89,230,232)',lineStyle:{
                        color:'rgb(117,189,186)'
                    },areaStyle: {color:'rgba(89,230,232,0.4)',type: 'default'}}},
                    data:[1320, 1132, 601, 234, 120, 90, 20,1320, 1132, 601, 234, 120,33]
                }
            ]
        };

        // 为echarts对象加载数据
        myChart.setOption(option);
    }
);
