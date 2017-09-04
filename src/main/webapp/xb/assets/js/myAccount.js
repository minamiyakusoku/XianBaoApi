$(function(){
    var iNow=0;
    function tab(){
        $('.tab_list a').eq(iNow).addClass('active').siblings('a').removeClass('active');
        $('.tab_count').eq(iNow).show().siblings('.tab_count').hide();
    }
    $('.tab_list a').on('click',function(){
       iNow=$(this).index();
        tab();
    });
});
