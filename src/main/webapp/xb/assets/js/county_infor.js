
init();
var html='';
function  init(){
    $.ajax({
        type:'get',
        url:'/XianBao/api/platform?mode=0',
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
    function create(star,dis){
        for(var i=star;i<dis;i++){
            var dataList=$('<tr>' +
            '<td>'+json.data[i].id+'</td>' +
            '<td>'+json.data[i].name+'</td>' +
            '<td>'+json.data[i].code+'</td>' +
            '<td>'+json.data[i].apiName+'</td>' +
            '<td>'+json.data[i].apiUrl+'</td>' +
            '<td>'+json.data[i].createTime+'</td>' +
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
function Delete(AttrId){
    var res=confirm('您确定要删除吗？');
    if(res){
        $.ajax({
            type:'get',
            url:'/XianBao/api/platform/delete',
            dataType:'json',
            data:{id:AttrId},
            success:function(json){
                /*console.log(json);*/
                if(json.code==1){
                    alert("删除成功！");
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
        url:'/XianBao/api/platform/'+AttrId+'',
        dataType:'json',
        data:{mode:2},
        success:function(json){

            var data=json.data;
            var data2=data;
            for(key in data){
                if(key=="platformcacfg.inArgCfg"){
                    data2[key]=JSON.stringify(data[key]);
                }
            }
            var aForm=$('.form-control');

            aForm.each(function(index,element){
                //index 索引
                //element每一个元素
                var name=$(element).attr('name');

                $(element).val(data2[name]);


            });
        }
    })
}

$('.return').click(function(){
    $('#content').show();
    $('#content2').hide();
});

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
            url:"/XianBao/api/platform/update",
            type:"post",
            data:data_json,
            success:function(result){
                if(result.code==1){
                    alert('提交成功');
                    location.reload();
                }else if(result.code==200){
                    alert('提交失败');
                }else if(result.code==301){
                    alert('您已经提交成功，请勿重复提交');
                }
            }
        });
        return false;
    }


}