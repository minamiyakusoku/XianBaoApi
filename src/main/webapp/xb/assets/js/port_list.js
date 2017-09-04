var apiinfoId;

//接口筛选
$('.status').change(function(){
    if($(this).val()=='run'){
        $.ajax({
            type:'get',
            url:'/XianBao/api?mode=0',
            dataType:'json',
            contentType:'application/x-www-form-urlencoded;charset=utf-8',
            success:function(json){
                var NewJson=json;
                ajaxPage(NewJson);
            }
        });
    }else if($(this).val()=='disable'){
        $.ajax({
            type:'get',
            url:'/XianBao/api?mode=1',
            dataType:'json',
            contentType:'application/x-www-form-urlencoded;charset=utf-8',
            success:function(json){
                var NewJson=json;
                ajaxPage(NewJson);
            }
        });
    }else{
        init();
    }
});

init();
var html='';
//进入页面执行
function  init(){
    $.ajax({
        type:'get',
        url:'/XianBao/api?mode=3',
        dataType:'json',
        contentType:'application/x-www-form-urlencoded;charset=utf-8',
        success:function(json){
            var NewJson=json;
            ajaxPage(NewJson);

        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        }
    });;;
}
//根据ajax提取的json创建页面
function ajaxPage(json){
    var dataL=json.data.length;

    var pageL=Math.ceil(dataL/10);
    var pageNum=0;
    $('.pagination').html('');
    if(pageL>1){
        $('<li class="prev"><a href="javascript:;">&laquo;</a></li>').appendTo('.pagination');
        $('<li class="active pageNum"><a href="javascript:;">1</a></li>').appendTo('.pagination');
        $('<li class="next"><a href="javascript:;">&raquo;</a></li>').appendTo('.pagination');
        for(var j=2;j<pageL+1;j++){
            var txt=$('<li class="pageNum"><a href="javascript:;">'+j+'</a></li>');
            $(".next").before(txt);
        }
        //分页操作

        $('.pagination .pageNum').click(function(){
            $('.pagination .pageNum').removeClass('active');
            $(this).addClass('active');
            pageNum=$(this).index()-1;
            if(pageNum<$('.pagination .pageNum').length-1){
                create10(pageNum);
            }else{
                surplus(pageNum,dataL);
            };

        });

        $('.prev').click(function(){
            pageNum--;
            if(pageNum<=0){
                pageNum=0;
            };
            $('.pagination .pageNum').removeClass('active');
            $('.pagination .pageNum').eq(pageNum).addClass('active');
            if(pageNum<$('.pagination .pageNum').length-1){
                create10(pageNum);
            }else{
                surplus(pageNum,dataL);
            };

        });
        $('.next').click(function(){
            pageNum++;
            if(pageNum>=$('.pagination .pageNum').length-1){
                pageNum=$('.pagination .pageNum').length-1;
            };
            $('.pagination .pageNum').removeClass('active');
            $('.pagination .pageNum').eq(pageNum).addClass('active');
            if(pageNum<$('.pagination .pageNum').length-1){
                create10(pageNum);
            }else{
                surplus(pageNum,dataL);
            };
        });

        create10(0);
    }else{
        surplus(pageNum,dataL);
    }

    //获取10条
    function create10(pageNum){
        var star=pageNum*10;
        var dis=pageNum*10+10;
        $('#contents_list').html('');
        create(star,dis);
    }
    //获取剩余
    function surplus(pageNum,dataL){
        var star=pageNum*10;
        var dis=dataL;
        $('#contents_list').html('');
        create(star,dis);
    }
	//创建标签
    function create(star,dis){
        for(var i=star;i<dis;i++){
            if(json.data[i].state==0){
                html='运行中';
            }else if(json.data[i].state==1){
                html='已禁用';
            }else if(json.data[i].state==2){
                html="已删除"
            }
            var dataList=$('<tr>' +
            '<td>'+json.data[i].name+'</td>' +
            '<td>scIsvGateway.do?method='+json.data[i].apiName+'</td>' +
            ' <td>'+html+'</td>' +
            '<td id="'+json.data[i].id+'"><input type="button" class="change" value="修改"> ' +
            ' <input type="button" value=" 删除" class="delete"></td>'+
            '</tr>');

            $('#contents_list').append(dataList);
        }
        $('.delete').click(function(){
            var AttrId=$(this).parent().attr('id');
            Delete(AttrId);
        });
        $('.change').click(function(){
            var AttrId=$(this).parent().attr('id');
           change(AttrId);
        });
    }
}
//删除功能
function Delete(AttrId){
    var res=confirm('您确定要删除吗？');
    if(res){
        $.ajax({
            type:'get',
            url:'/XianBao/api/delete',
            dataType:'json',
            data:{id:AttrId},
            success:function(json){
                /*console.log(json);*/
                if(json.code==1){
                    alert("删除成功！");
                    //location.reload();
					init();
                }else{
                    alert("删除失败！");
                }
            }
        })
    }
}

//点击修改执行
function change(AttrId){
    alert(AttrId);
    $('#content').hide();
    $('#content2').show();
    $.ajax({
        type:'get',
        url:'/XianBao/api/'+AttrId+'',
        dataType:'json',
        data:{mode:2},
        success:function(json){
			
            var data3=json.data;
			var data2={};
			for(name in data3){
				if(name.indexOf('inParams')!=-1 || name.indexOf('outParams')!=-1 || name=='apiresult.cfgMap' || name=='apiresult.template'){
                    data2[name]=JSON.stringify(data3[name]);
                }else{
					data2[name]=data3[name];
				}
				
			}
			apiinfoId=data3['apiinfo.id'];
			var inCount=data3['apiinfo.inArgsCount'];
			console.log(inCount);
			
			if(inCount>=2){
				for(var i=1;i<inCount;i++){
					add_content1(i);
				}
			}
			var outCount=data3['apiinfo.outArgsCount'];
			if(outCount>=2){
				for(var i=1;i<outCount;i++){
					add_content2(i);
				}
			}
           
            var aForm=$('.form-control');

            aForm.each(function(index,element){
                var name=$(element).attr('name');
                
              
               	$(element).val(data2[name]);
				
				
            });
			var aRadio=$('.Radio');
			//data_json['apiinfo.inArgsCount']=num1+1;
			//data_json['apiinfo.outArgsCount']=num2+1;
			aRadio.each(function(index,element){
				//index 索引
				//element每一个元素
				var name=$(element).attr('name');
				//var val=$('input[type="radio"][name="'+name+'"]:checked').val();
				if(data2[name]==true){
					var val=1;
				}else{
					var val=0;
				}
				//$(element).find("input:radio").attr("checked","checked");
				$('input[type="radio"][value="'+val+'"]').attr("checked",true);
				//data_json[name]=parseInt(val);
			});
        }
    })
}


//点击修改后提交
function checkNull(){
    var num=0;
    /*var str="";*/
    $("form .form-control").each(function(n){
        if($(this).val()=="")
        {
            num++;
        }
    });
    if(num>0)
    {
        alert("请填写完整！");
        return false;
    }else{
        var data_json={};
        var aRadio=$('.Radio');
        data_json['apiinfo.inArgsCount']=num1+1;
        data_json['apiinfo.outArgsCount']=num2+1;
		data_json['apiinfo.id']=parseInt(apiinfoId);
        aRadio.each(function(index,element){
            //index 索引
            //element每一个元素
            var name=$(element).attr('name');
            var val=$('input[type="radio"][name="'+name+'"]:checked').val();
            data_json[name]=parseInt(val);
        });

        var aForm=$('.form-control');

        aForm.each(function(index,element){
            //index 索引
            //element每一个元素
            var name=$(element).attr('name');
            if(name.indexOf('inParams')!=-1 || name.indexOf('outParams')!=-1 || name=='apiresult.cfgMap'){
                data_json[name]=JSON.parse($(element).val());
            }else if(name=='apiinfo.versionId' || name=='apiinfo.pId' ){
                data_json[name]=parseInt($(element).val());
            }else if(name.indexOf('format')!=-1 && name.indexOf('format')<30 ){
                data_json[name]=parseInt($(element).val());
            }else{
                var val=$(element).val();
                data_json[name]=val;
            }

        });
		
		//changeJson    data_json  匹配
		/*
		for(name in data_json){
			changeJson[name]=data_json[name];
		}
		*/
		console.log(data_json);
        $.ajax({
            url:"/XianBao/api/updateall",
            type:"post",
            data:data_json,
            success:function(result){
                if(result.code==1){
                    alert('提交成功');
                    location.reload();
                }else if(result.code>=200 && result.code<300){
                    alert('提交失败');
                }else if(result.code==301){
                    alert('您已经提交成功，请勿重复提交');
                }
            }
        });
        return false;
    }


}

var iNow=0;
function tab(){
    $('.wizard_title li').eq(iNow).addClass('active').siblings('li').removeClass('active');
    $('.step').eq(iNow).show().siblings('.step').hide();
    if(iNow==3){
        $('.step_button .next_step').hide();
        $('.step_button .prev_step').show();
        $('#commit').show();
    }else{
        $('.step_button .next_step').show();
        $('.step_button .prev_step').show();
        $('#commit').hide();
    }
    if(iNow==0){
        $('.step_button .prev_step').hide();
    }
}
$('.wizard_title li').on('click',function(){
    iNow=$(this).index();
    tab();
});
$('.step_button .next_step').on('click',function(){
    iNow++;
    tab();
});
$('.step_button .prev_step').on('click',function(){
    iNow--;
    tab();
});
var num1=0;
add_content1(num1);
$('.add_list1').on('click',function(){
    num1+=1;
    add_content1(num1);
});
var num2=0;
add_content2(num2);
$('.add_list2').on('click',function(){
    num2+=1;
    add_content2(num2);
});

function add_content1(num){
    var main1=$('#main1');
    var copy_content1=$('<div class="content_list"><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">参数显示名称:</label><div class="col-sm-4"><input type="text" class="form-control"name="inargslist.inArgsList['+num+'].cloName"></div></div></div><div class="col-md-12"> <div class="form-group"><label class="col-sm-3 control-label">传参名称:</label><div class="col-sm-4"><input type="text" class="form-control"name="inargslist.inArgsList['+num+'].pyName"> </div> </div> </div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">数据类型:</label><div class="col-sm-4"><select class="form-control" name="inargslist.inArgsList['+num+'].type"><option value="STRING">STRING</option><option value="DATE">DATE</option><option value="MONEY">MONEY</option><option value="INT">INT</option><option value="DOUBLE">DOUBLE</option></select></div></div></div> <div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">是否为空:</label><div class="col-sm-4"><label class="checkbox-inline"><input type="radio" class="Radio" name="inargslist.inArgsList['+num+'].null"value="1">是</label><label class="checkbox-inline"><input type="radio" name="inargslist.inArgsList['+num+'].null"value="0">否</label></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">格式化方式:</label><div class="col-sm-4"><input type="text" class="form-control"name="inargslist.inArgsList['+num+'].format"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传入时加密方式:</label><div class="col-sm-4"><select class="form-control" name="inencodelist.encodeList['+num+'].inType"><option value="NONE">NONE</option><option value="DES">DES</option><option value="DES3_ECB">DES3_ECB</option><option value="DES3_CBC">DES3_CBC</option><option value="RSA">RSA</option><option value="MD5">MD5</option></select></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传入时加密配置列表:</label><div class="col-sm-4"><input type="text" class="form-control"name="inencodelist.encodeList['+num+'].inParams"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传出时加密配置列表:</label><div class="col-sm-4"><input type="text" class="form-control"name="inencodelist.encodeList['+num+'].outParams"></div></div></div><div class="col-md-12"> <div class="form-group"><label class="col-sm-3 control-label">传出时加密方式:</label><div class="col-sm-4"><select class="form-control" name="inencodelist.encodeList['+num+'].outType"><option value="NONE">NONE</option><option value="DES">DES</option><option value="DES3_ECB">DES3_ECB</option><option value="DES3_CBC">DES3_CBC</option><option value="RSA">RSA</option><option value="MD5">MD5</option></select></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">入参数据类型:</label><div class="col-sm-4">' +
    '<select class="form-control" name="inchecklist.checkList['+num+'].type"><option value="NONE">NONE</option><option value="IDCARD">IDCARD</option><option value="TIME">TIME</option><option value="NAME">NAME</option><option value="MOBILE">MOBILE</option><option value="EMAIL">EMAIL</option></select></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">出现为空报错时错误码:</label><div class="col-sm-4"><input type="text" class="form-control"name="inexceptionlist.exceptionList['+num+'].nullException"> </div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">出现加/解密失败报错时错误码:</label> <div class="col-sm-4"><input type="text" class="form-control"name="inexceptionlist.exceptionList['+num+'].encodeException"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">出现格式化失败时的错误码:</label><div class="col-sm-4"><input type="text" class="form-control"name="inexceptionlist.exceptionList['+num+'].formatException"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">出现类型检查失败是错误码:</label><div class="col-sm-4"><input type="text" class="form-control"name="inexceptionlist.exceptionList['+num+'].argCheckException"></div></div><hr></div></div>');
    main1.append(copy_content1);
}
function add_content2(num){
    var main2=$('#main2');
    var copy_content2=$('<div class="content_list"><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">参数显示名称:</label><div class="col-sm-4"><input type="text" class="form-control" name="outargslist.outArgsList['+num+'].cloName"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传参名称:</label><div class="col-sm-4"><input type="text" class="form-control" name="outargslist.outArgsList['+num+'].pyName"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">数据类型:</label><div class="col-sm-4"><select class="form-control" name="outargslist.outArgsList['+num+'].type"><option value="STRING">STRING</option><option value="DATE">DATE</option><option value="MONEY">MONEY</option> <option value="INT">INT</option> <option value="DOUBLE">DOUBLE</option></select></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">是否为空:</label><div class="col-sm-4"> <label class="checkbox-inline"><input type="radio" class="Radio" name="outargslist.outArgsList['+num+'].null" value="1">是 </label><label class="checkbox-inline"><input type="radio" name="outargslist.outArgsList['+num+'].null"value="0">否</label></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">格式化方式:</label><div class="col-sm-4"><input type="text" class="form-control"name="outargslist.outArgsList['+num+'].format"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传入时加密方式:</label><div class="col-sm-4"><select class="form-control" name="outencodelist.encodeList['+num+'].inType"><option value="NONE">NONE</option><option value="DES">DES</option><option value="DES3_ECB">DES3_ECB</option><option value="DES3_CBC">DES3_CBC</option><option value="RSA">RSA</option><option value="MD5">MD5</option></select></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传入时加密配置列表:</label><div class="col-sm-4"><input type="text" class="form-control" name="outencodelist.encodeList['+num+'].inParams"> </div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传出时加密配置列表:</label><div class="col-sm-4"><input type="text" class="form-control" name="outencodelist.encodeList['+num+'].outParams"></div></div></div><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">传出时加密方式:</label> <div class="col-sm-4"><select class="form-control" name="outencodelist.encodeList['+num+'].outType"><option value="NONE">NONE</option><option value="DES">DES</option><option value="DES3_ECB">DES3_ECB</option><option value="DES3_CBC">DES3_CBC</option><option value="RSA">RSA</option><option value="MD5">MD5</option></select></div></div><hr></div></div>');
    main2.append(copy_content2);
}
$('.return').click(function(){
    $('#content').show();
    $('#content2').hide();
});
