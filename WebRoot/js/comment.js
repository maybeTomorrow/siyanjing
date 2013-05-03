/*
 * comment
 */
$com_dis = $('#comment_display');
$com_id = $com_dis.attr('rel');
$locked = false;

var reply = function(uname, nickname){
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

var submit_form = function(){
    if ($locked) {
        return;
    }
    $locked = true;
    $clone = $('#comment_add .editor').clone();
    $button = $clone.children('button.metion');
    $name = $button.attr('name');
    $button.remove();
    $.ajax({
        type: "post",
        url: "/comment/add/",
        data: {
            'target': $com_id,
            'motion': $name,
            'comment': $clone.html()
        },
        beforeSend: function(XMLHttpRequest){
            $('#submit_info').html("").append('<img src="/images/loading.gif"/>');
        },
        success: function(data, status){
            $('#submit_info').html("").append(data);
            comment_load(false, $('#comment_page .selected').html());
            $locked = false;
        },
        error: function(){
            $('#submit_info').html("").append("评论系统出错");
        }
    });
}

var comment_load = function(cache, page){
    $url = cache ? "/comment/" + $com_id + "-" + page : "/comment/update/" + $com_id + "-" + page;
    $.ajax({
        type: "get",
        url: $url,
        beforeSend: function(XMLHttpRequest){
            $com_dis.append('<div id="com_loading"><img src="/images/loading.gif"/></div>');
        },
        success: function(data, status){
            $com_dis.html(data);
            $('#comment_add .submit').click(function(){
                submit_form();
            });
            $('#comment_page a').click(function(e){
                comment_load(true, $(this).attr('href').split('#')[1]);
            });
        },
        error: function(){
            $com_dis.html('加载评论错误');
        }
    });
}

comment_load(true, 1);// 初始化加载评论
/*
 * 光标最后 END
 */

