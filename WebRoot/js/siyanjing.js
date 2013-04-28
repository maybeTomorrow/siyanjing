define(function(require, exports, module){
    require('jquery');
    /*
     * 图片渐隐效果
     */
    jQuery(function(){
        jQuery('.thumbnail img').hover(function(){
            jQuery(this).fadeTo("fast", 0.5);
        }, function(){
            jQuery(this).fadeTo("fast", 1);
        });
    });
    
    /*
     *  Slide Here
     */
    $(function(){
        $item_width = 800;
        $Interval_time = 3000;
        $animate_time = 800;
        var slide_next = function(){
            $current = $('#switchable-content .active');
            $index = 0;
            $('#switchable-content li:not(.active)').css({
                'left': '800px'
            });
            if ($current.next().length > 0) {
                $index = $current.next().index();
                $current.animate({
                    left: '-800'
                }, $animate_time).removeClass('active').next().animate({
                    left: '0'
                }, $animate_time).addClass('active');
            }
            else {
                $current.animate({
                    left: '-800'
                }, $animate_time).removeClass('active');
                $('#switchable-content .item').first().animate({
                    left: '0'
                }, $animate_time).addClass('active');
            }
            $('#switchable-nav .trans_cover').removeClass('active');
            $('#switchable-nav .trans_cover:eq(' + $index + ')').addClass('active');
        };
        
        var slide_prev = function(){
            $current = $('#switchable-content .active');
            $index = 0;
            $('#switchable-content li:not(.active)').css({
                'left': '-800px'
            });
            if ($current.prev().length > 0) {
                $index = $current.prev().index();
                $current.animate({
                    left: '800'
                }, $animate_time).removeClass('active').prev().animate({
                    left: '0'
                }, $animate_time).addClass('active');
            }
            else {
                $index = $('#switchable-nav .trans_cover').length - 1;
                $current.animate({
                    left: '800'
                }, $animate_time).removeClass('active');
                $('#switchable-content .item').last().animate({
                    left: '0'
                }, $animate_time).addClass('active');
            }
            $('#switchable-nav .trans_cover').removeClass('active');
            $('#switchable-nav .trans_cover:eq(' + $index + ')').addClass('active');
        };
        
        var slide_show = function(index){
            $current = $('#switchable-content .active');
            if ($current.index() == index) {
                return;
            }
            $('#switchable-content li:not(.active)').css({
                'left': '800px'
            });
            $current.animate({
                left: '-800'
            }, $animate_time).removeClass('active');
            $('#switchable-content .item:eq(' + index + ')').animate({
                left: '0'
            }, $animate_time).addClass('active');
            $('#switchable-nav .trans_cover').removeClass('active');
            $('#switchable-nav .trans_cover:eq(' + index + ')').addClass('active');
        };
        
        var MyInterval = setInterval(slide_next, $Interval_time);
        $('#prev').click(function(){
            slide_prev();
            return false;
        });
        
        $('#next').click(function(){
            slide_next();
            return false;
        });
        $('#slider').mouseenter(function(){
            clearInterval(MyInterval);
        });
        $('#slider').mouseleave(function(){
            MyInterval = setInterval(slide_next, $Interval_time);
        });
        $('#switchable-nav li').click(function(){
            slide_show($(this).index());
            return false;
        });
    });
    
    /*
     *
     * goto top
     *
     */
    $(function(){
        backTop = function(btnId){
            var btn = document.getElementById(btnId);
            var d = document.documentElement;
            var b = document.body;
            window.onscroll = set;
            btn.onclick = function(){
                btn.style.display = "none";
                window.onscroll = null;
                this.timer = setInterval(function(){
                    d.scrollTop -= Math.ceil((d.scrollTop + b.scrollTop) * 0.1);
                    b.scrollTop -= Math.ceil((d.scrollTop + b.scrollTop) * 0.1);
                    if ((d.scrollTop + b.scrollTop) == 0) 
                        clearInterval(btn.timer, window.onscroll = set);
                }, 10);
            };
            function set(){
                btn.style.display = (d.scrollTop + b.scrollTop > 100) ? 'block' : "none";
            }
        };
        backTop('gotop');
    });
    
    /*
     * nav fix
     */
    $(function(){
        var index = 100;
        $(window).scroll(function(){
            var top = $(document).scrollTop();
            if (top > index) {
                $('#topNav .logo').hide();
                $('#header').addClass('ui-header-scroll');
                $('.ui-top-nav').addClass('ui-top-nav-scroll');
            }
            else {
                $('#topNav .logo').show();
                $('#header').removeClass('ui-header-scroll');
                $('.ui-top-nav').removeClass('ui-top-nav-scroll');
            }
        });
    });
    
    
    /* * * 
     *
     *comment
     *
     */
    $(document).ready(function(){
        if ($('div #comment_display').length > 0) {
            (function(){
                var com_sc = document.createElement('script');
                com_sc.type = 'text/javascript';
                com_sc.async = true;
                com_sc.src = '/js/comment.js';
                (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(com_sc);
            })();
        }
        
    });
    
    
    /*
     *display
     */
    var type = {
        "1": "NET程序员",
        "2": "PHP程序员",
        "3": "Java程序员",
        "4": "C/C++程序员",
        "19": "Python程序员",
        "20": "Ruby程序员",
        "5": "JavaScript程序员",
        "6": "Flash程序员",
        "7": "Delphi程序员",
        "8": "前端开发工程师",
        "9": "项目经理",
        "10": "技术主管",
        "11": "架构师",
        "12": "技术总监",
        "13": "测试工程师",
        "14": "系统管理员",
        "15": "数据库管理员",
        "16": "售前工程师",
        "17": "手机应用开发工程师",
        "18": "其他"
    };
    var salary = {
        "0000001000": "1000元以下",
        "0100002000": "1000-2000元",
        "0200104000": "2001-4000元",
        "0400106000": "4001-6000元",
        "0600108000": "6001-8000元",
        "0800110000": "8001-10000元",
        "1000115000": "10001-15000元",
        "1500125000": "15000-25000元",
        "2500199999": "25000元以上"
    };
    function distype(a){
        document.write(type[a]);
    }
    
    function dissalary(a){
        document.write(salary[a]);
    }
});
