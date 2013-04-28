/*
 *  Slide Here
 */
define(function(require, exports, module){
	require('jquery');
    exports.slider = function(){
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
    }
});
