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

        var aForm=$('.form-control');

        aForm.each(function(index,element){
            //index 索引
            //element每一个元素
            var name=$(element).attr('name');
            if(name.indexOf('inArgCfg')!=-1){
                data_json[name]=JSON.parse($(element).val());
            }else{
                var val=$(element).val();
                data_json[name]=val;
            }

        });
        /* console.log(data_json);*/
        $.ajax({
            url:"/XianBao/api/platform/save",
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