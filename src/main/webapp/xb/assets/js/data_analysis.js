var data=['80','70','60','50','40','30','20'];
var date=['周一','周二','周三','周四','周五','周六','周日'];
//var date=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
var typeName=['粉丝数量'];
var typename='粉丝数量';
var typeColor='rgb(125,201,198)';

$('.week').click(function(){
	$('.month').removeClass('nowActive');
	$(this).addClass('nowActive');
	date=['周一','周二','周三','周四','周五','周六','周日'];
	data=['80','70','60','50','40','30','20'];
	var nowType=$('.fans input[type="radio"]:checked').val();
	if(nowType){
		typeName=['粉丝数量'];
		typename='粉丝数量';
	}
	else{
		typeName=['消息发送统计'];
		typename='消息发送统计';
	}
	
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
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					data:typeName
				},
				toolbox: {
					show : true
				},
				calculable : true,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data :date
					}
				],
				yAxis : [
					{
						type : 'value'
					}
				],
				series : [
					{
						name:typename,
						type:'line',
						stack: '总量',
						data:data,
						itemStyle:{
							normal:{
								color:typeColor,
								lineStyle:{
									color:typeColor
								}
							}
						}
					}
				]
			};
			// 为echarts对象加载数据
			myChart.setOption(option);
		}
	);
});
$('.month').click(function(){
	$('.week').removeClass('nowActive');
	$(this).addClass('nowActive');	
	date=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
	data=['110','270','630','540','450','306','270','11','22','111','1111','444'];
	var nowType=$('.fans input[type="radio"]:checked').val();
	if(nowType){
		typeName=['粉丝数量'];
		typename='粉丝数量';
	}
	else{
		typeName=['消息发送统计'];
		typename='消息发送统计';
	}
	
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
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					data:typeName
				},
				toolbox: {
					show : true
				},
				calculable : true,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data :date
					}
				],
				yAxis : [
					{
						type : 'value'
					}
				],
				series : [
					{
						name:typename,
						type:'line',
						stack: '总量',
						data:data,
						itemStyle:{
							normal:{
								color:typeColor,
								lineStyle:{
									color:typeColor
								}
							}
						}
					}
				]
			};
			// 为echarts对象加载数据
			myChart.setOption(option);
		}
	);
});


$('.fans').click(function(){
	
	typeColor='rgb(125,201,198)';
	if(date.length>7){
		data=['110','270','630','540','450','306','270','11','22','111','1111','444']
	}
	else{
		data=['80','70','60','50','40','30','20'];
	}
	
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
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					data:['粉丝数量']
				},
				toolbox: {
					show : true
				},
				calculable : true,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data :date
					}
				],
				yAxis : [
					{
						type : 'value'
					}
				],
				series : [
					{
						name:'粉丝数量',
						type:'line',
						stack: '总量',
						data:data,
						itemStyle:{
							normal:{
								color:typeColor,
								lineStyle:{
									color:typeColor
								}
							}
						}
					}
				]
			};
			// 为echarts对象加载数据
			myChart.setOption(option);
		}
	);
});
$('.message').click(function(){
	typeColor='rgb(166,47,54)';
		
	if(date.length>7){
		data=['110','270','630','540','450','306','270','11','22','111','1111','444']
	}
	else{
		data=['80','70','60','50','40','30','20'];
	}
	
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
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					data:['消息发送统计']
				},
				toolbox: {
					show : true
				},
				calculable : true,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data :date
					}
				],
				yAxis : [
					{
						type : 'value'
					}
				],
				series : [
					{
						name:'消息发送统计',
						type:'line',
						stack: '总量',
						data:data,
						itemStyle:{
							normal:{
								color:typeColor,
								lineStyle:{
									color:typeColor
								}
							}
						}
					}
				]
			};
			// 为echarts对象加载数据
			myChart.setOption(option);
		}
	);
});

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
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					data:typeName
				},
				toolbox: {
					show : true
				},
				calculable : true,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data :date
					}
				],
				yAxis : [
					{
						type : 'value'
					}
				],
				series : [
					{
						name:typename,
						type:'line',
						stack: '总量',
						data:data,
						itemStyle:{
							normal:{
								color:typeColor,
								lineStyle:{
									color:typeColor
								}
							}
						}
					}
				]
			};
			// 为echarts对象加载数据
			myChart.setOption(option);
		}
	);