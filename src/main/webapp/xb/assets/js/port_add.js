var iNow=0;
function tab(){
    $('.wizard_title li').eq(iNow).addClass('active').siblings('li').removeClass('active');
    $('.step').eq(iNow).show().siblings('.step').hide();
    if(iNow==3){
        $('.step_button .next').hide();
        $('.step_button .prev').show();
        $('#commit').show();
    }else{
        $('.step_button .next').show();
        $('.step_button .prev').show();
        $('#commit').hide();
    }
    if(iNow==0){
        $('.step_button .prev').hide();
    }
}
$('.wizard_title li').on('click',function(){
    iNow=$(this).index();
    tab();
});
$('.step_button .next').on('click',function(){
    iNow++;
    tab();
});
$('.step_button .prev').on('click',function(){
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
        /* console.log(data_json);*/
        $.ajax({
            url:"/XianBao/api/save",
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




