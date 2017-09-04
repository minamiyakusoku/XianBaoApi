<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加api</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    var innum = 1;
    chengeTextIn(innum);
    $("#addin").click(function() {
        $("#tablein").before(addTextIn);
        innum = innum + 1;
        chengeTextIn(innum);
    });
    function chengeTextIn(innum) {
    	addTextIn = '<tr><td><input name="inargslist.inArgsList[' + innum + '].cloName" value="" /></td><td><input name="inargslist.inArgsList[' + innum + '].pyName" value="" /></td><td><input name="inargslist.inArgsList[' + innum + '].type" value="" /></td><td><label><input name="inargslist.inArgsList[' + innum + '].isNull" type="radio" value="1" />是</label><label><input name="inargslist.inArgsList[' + innum + '].isNull" type="radio" value="0" checked="checked"/>否</label></td><td><input name="inargslist.inArgsList[' + innum + '].isEncode" value="1" /></td><td><input name="inargslist.inArgsList[' + innum + '].format" value="0" /></td></tr>';
    }
    var outnum =1;
    chengeTextOut(outnum);
    $("#addout").click(function() {
        $("#tableout").before(addTextOut);
        outnum = outnum + 1;
        chengeTextOut(outnum);
    });
    function chengeTextOut(outnum) {
    	addTextOut = '<tr><td><input name="outargslist.outArgsList[' + outnum + '].cloName" value="" /></td><td><input name="outargslist.outArgsList[' + outnum + '].pyName" value="" /></td><td><input name="outargslist.outArgsList[' + outnum + '].type" value="" /></td><td><label><input name="outargslist.outArgsList[' + outnum + '].isNull" type="radio" value="1" />是</label><label><input name="outargslist.outArgsList[' + outnum + '].isNull" type="radio" value="0" checked="checked"/>否</label></td><td><input name="outargslist.outArgsList[' + outnum + '].isEncode"  value="0" /></td><td><input name="outargslist.outArgsList[' + outnum + '].format" value="0" /></td></tr>';
    }
    
});
</script>
<body>
	<form action="/XianBao/saveapi" method="get">
		<table>
			<thead>

				<tr>
					<th colspan="4">---------------------api ---------------------</th>
				</tr>
				<tr>
					<th>Api编号</th>
					<th>VersionID</th>
					<th>Api名字</th>
					<th>Api标识</th>
					<th>类型</th>
					<th>是否加密</th>
					<th>平台ID</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<td colspan="2"><input type="submit" value="Save" /></td>
				</tr>
			</tfoot>
			<tbody>
				<tr>
					<td><input name="apiinfo.apiId" value="2" /></td>
					<td><input name="apiinfo.versionId" value="2" /></td>
					<td><input name="apiinfo.name" value="ceshi" /></td>
					<td><input name="apiinfo.apiName" value="/ceshi" /></td>
					<td><input name="apiinfo.type" value="type" /></td>
					<td><label><input name="apiinfo.isEnCode" type="radio" value="1" />是</label><label><input name="apiinfo.isEnCode" type="radio" value="0" checked="checked"/>否</label></td>
					<td><input name="apiinfo.pId" value="1" /></td>
				</tr>
				<tr>
					<th colspan="4">---------------------入参---------------------</th>
				</tr>
					<tr>
						<th>入参别名</th>
						<th>入参字段名</th>
						<th>数据类型</th>
						<th>是否为空</th>
						<th>是否加密</th>	  
						<th>格式化方法</th>	
					</tr>
					<tr><td><input name="inargslist.inArgsList[0].cloName" value="" /></td><td><input name="inargslist.inArgsList[0].pyName" value="" /></td><td><input name="inargslist.inArgsList[0].type" value="" /></td><td><label><input name="inargslist.inArgsList[0].isNull" type="radio" value="1" />是</label><label><input name="inargslist.inArgsList[0].isNull" type="radio" value="0" checked="checked"/>否</label></td><td><input name="inargslist.inArgsList[0].isEncode"  value="0" /></td><td><input name="inargslist.inArgsList[0].format" value="0" /></td></tr>
				<tr id="tablein"><tr>
					<td><input type="button" value="+" id="addin" /></td>
				</tr>
				</tr>
				<tr>
					<th colspan="4">---------------------出參---------------------</th>
				</tr>
				<tr>
					<tr>
						<th>出参别名</th>
						<th>出参字段名</th>
						<th>数据类型</th>
						<th>是否为空</th>
						<th>是否加密</th>	
						<th>格式化方法</th>
					</tr>
				</tr>
			<tbody>
				<tr><td><input name="outargslist.outArgsList[0].cloName" value="" /></td><td><input name="outargslist.outArgsList[0].pyName" value="" /></td><td><input name="outargslist.outArgsList[0].type" value="" /></td><td><label><input name="outargslist.outArgsList[0].isNull" type="radio" value="1" />是</label><label><input name="outargslist.outArgsList[0].isNull" type="radio" value="0" checked="checked"/>否</label></td><td><input name="outargslist.outArgsList[0].isEncode"  value="0" /></td><td><input name="outargslist.outArgsList[0].format" value="0" /></td></tr>
				<tr id="tableout"><tr>
					<td><input type="button" value="+" id="addout" /></td>
				</tr>
				<tr>
					<th colspan="4">---------------------SQL ---------------------</th>
				</tr>
			<tbody>
					<td colspan="4"><textarea name="apisql.sqlInfo" width="500px"></textarea></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
