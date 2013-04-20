/*
 * comment
 */
$com_dis = $('#comment_display');
$com_id = $com_dis.attr('rel');
$.ajax({
    type: "get",
    url: "/comment/" + $com_id,
    beforeSend: function(XMLHttpRequest){
        $com_dis.append('<div id="com_loading"><img src="/images/loading.gif"/></div>');
    },
    success: function(data, status){
        $com_dis.html(data);
    },
    complete: function(XMLHttpRequest, textStatus){
        //HideLoading();
    },
    error: function(){
        $com_dis.html('加载评论粗无');
    }
});

function reply(uname, nickname){
    $txtarea = $('#comment_add_form textarea');
    $txtarea.html('<a href="my.siyanjing.com/'+uname+'" target="_blank">@'+nickname + '</a> ' + $txtarea.html());
}
