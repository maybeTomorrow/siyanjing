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
    $editor = $('#comment_add .editor');
    var at = document.createElement('button');
    at.name = '@{uname:' + uname + ',unickname:' + nickname + '}';
    at.onclick = 'return false';
    at.tabIndex = '-1';
    at.contentEditable = 'false';
    at.className = 'metion';
    at.innerHTML = '@' + nickname + ': ';
    $editor.empty().append(at).append('&nbsp;');
    $editor.focus();
}

/*

 * 光标最后 END

 */

