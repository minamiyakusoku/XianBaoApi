<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/jquery/jquery-1.10.2.min_65682a2.js"></script>
	
	
	<script>
		function on(){
			$.ajax({
		           url:'/XianBao/api/save',
		            type:'POST',
		            dataType:'json',
		            data: {
		                "apiinfo.versionId":8,
		                "apiinfo.name":"Q-Q",
		                "apiinfo.apiName":"testsaveapi",
		                "apiinfo.inArgsCount":2,
		                "apiinfo.outArgsCount":9,
		                "apiinfo.type":"type",
		                "apiinfo.pId":8,
		                "inargslist.inArgsList[0].cloName":"idcard",
		                "inargslist.inArgsList[0].pyName":"idcard",
		                "inargslist.inArgsList[0].type":"STRING",
		                "inargslist.inArgsList[0].format":0,
		                "inargslist.inArgsList[0].isNull":false,
		                "inargslist.inArgsList[1].cloName":"name",
		                "inargslist.inArgsList[1].pyName":"name",
		                "inargslist.inArgsList[1].type":"STRING",
		                "inargslist.inArgsList[1].format":0,
		                "inargslist.inArgsList[1].isNull":false,
		                "inencodelist.encodeList[0].inType":"DES3_ECB",
		                "inencodelist.encodeList[0].inParams":{
		                    "KEY":"akkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakku"
		                },
		                "inencodelist.encodeList[0].outType":"NONE",
		                "inencodelist.encodeList[0].outParams":{

		                },
		                "inencodelist.encodeList[1].inType":"DES3_ECB",
		                "inencodelist.encodeList[1].inParams":{

		                },
		                "inencodelist.encodeList[1].outType":"DES3_ECB",
		                "inencodelist.encodeList[1].outParams":{
		                    "KEY":"akkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakkuakku"
		                },
		                "inchecklist.checkList[0].type":"CHINESE",
		                "inchecklist.checkList[1].type":"EMAIL",
		                "inexceptionlist.exceptionList[0].nullException":"isv.invalid-idcard",
		                "inexceptionlist.exceptionList[0].encodeException":"isv.invalid-idcard",
		                "inexceptionlist.exceptionList[0].formatException":"isv.invalid-idcard",
		                "inexceptionlist.exceptionList[0].argCheckException":"isv.invalid-idcard",
		                "inexceptionlist.exceptionList[1].nullException":"isv.invalid-name",
		                "inexceptionlist.exceptionList[1].encodeException":"isv.invalid-name",
		                "inexceptionlist.exceptionList[1].formatException":"isv.invalid-name",
		                "inexceptionlist.exceptionList[1].argCheckException":"isv.invalid-name",
		                "outargslist.outArgsList[0].cloName":"accu_found_no",
		                "outargslist.outArgsList[0].pyName":"accu_found_no",
		                "outargslist.outArgsList[0].type":"STRING",
		                "outargslist.outArgsList[0].format":0,
		                "outargslist.outArgsList[0].isNull":false,
		                "outargslist.outArgsList[1].cloName":"deposit_unit",
		                "outargslist.outArgsList[1].pyName":"deposit_unit",
		       		    "outargslist.outArgsList[1].type":"STRING",
		                "outargslist.outArgsList[1].format":0,
		                "outargslist.outArgsList[1].isNull":false,
		                "outargslist.outArgsList[2].cloName":"last_deposit_date",
		                "outargslist.outArgsList[2].pyName":"last_deposit_date",
		                "outargslist.outArgsList[2].type":"DATE",
		                "outargslist.outArgsList[2].encode":0,
		                "outargslist.outArgsList[2].format":0,
		                "outargslist.outArgsList[2].isNull":false,
		                "outargslist.outArgsList[3].cloName":"unit_deposit",
		                "outargslist.outArgsList[3].pyName":"unit_deposit",
		                "outargslist.outArgsList[3].type":"MONEY",
		                "outargslist.outArgsList[3].format":0,
		                "outargslist.outArgsList[3].isNull":false,
		                "outencodelist.encodeList[0].inType":"NONE",
		                "outencodelist.encodeList[0].inParams":{

		                },
		                "outencodelist.encodeList[0].outType":"NONE",
		                "outencodelist.encodeList[0].outParams":{

		                },
		                "outencodelist.encodeList[1].inType":"NONE",
		                "outencodelist.encodeList[1].inParams":{

		                },
		                "outencodelist.encodeList[1].outType":"NONE",
		                "outencodelist.encodeList[1].outParams":{

		                },
		                "outencodelist.encodeList[2].inType":"NONE",
		                "outencodelist.encodeList[2].inParams":{

		                },
		                "outencodelist.encodeList[2].outType":"NONE",
		                "outencodelist.encodeList[2].outParams":{

		                },
		                "outencodelist.encodeList[3].inType":"NONE",
		                "outencodelist.encodeList[3].inParams":{

		                },
		                "outencodelist.encodeList[3].outType":"NONE",
		                "outencodelist.encodeList[3].outParams":{

		                },
		                "apisql.sqlInfo":"SELECT name,idcard,accu_found_no,deposit_unit,last_deposit_date,unit_deposit,person_deposit,deposit_status,total as sum FROM af_info WHERE af_info.NAME='{name}' AND af_info.idcard = '{idcard}'",
		                "apiresult.resultName":"isv.af.deposit.info.query",
		                "apiresult.exceptionCode":"isv.unknow-error",
		                "apiresult.template":{

		                },
		                "apiresult.cfgMap":{
		                    "EXCEPTION_CODE":"sub_code",
		                    "DATA":"base",
		                    "API_CODE":"code",
		                    "INARGS":"base"
		                }
		            },
		            success: function(result) {
		                console.log(result);
		            }
		        });
		}
	</script>
  </head>
  
  <body>
    <input type = "button" value="akku" onclick="on()"/>
  </body>
</html>
