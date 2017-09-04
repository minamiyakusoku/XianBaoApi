    var iNow=0;
    function tab(){
        $('.tab_list a').eq(iNow).addClass('active').siblings('a').removeClass('active');
        $('.tab_count').eq(iNow).show().siblings('.tab_count').hide();
    }
    $('.tab_list a').on('click',function(){
        iNow=$(this).index();
        tab();
    });
    $('.details').on('click',function(){
        $('#mask').css({
            "width":"100%",
            "height":$(window).height()
        });
        $("#mask").show();
    });
    var new_input=$('<div class="new-menu-wrap input_name"><input class="editing" maxlength="36"><a class="newNameAction saveNewName"></a><a href="javascript:;" id="cancel">È¡Ïû</a></div>');
    $('.tab_count .new-menu-main').on('click',function(){
        $('.add').before(new_input);
        $('.add').hide();
    });
    $('.custom').on('click','#cancel',function(){
        $('.input_name').remove();
        $('.add').show();
    });
    $('.custom').on('click','.saveNewName',function(){
        $('.input_name').hide();
        var new_mainMenu=$('<div class="menu-list"><div class="list-item list-parent"><span class="name">'+$('.editing').val()+'</span></div><div class="subMenu-list"><div class="list-item list-sub"><span class="name">aaaa</span></div></div>');
        $('.add').before(new_mainMenu);
    });
