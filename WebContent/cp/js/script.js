/*
 //彩票
 */
//红球数量
var rball_nums = 0;
//蓝球数量
var bball_nums = 0;
/*
 *点击红球，变红，再点击变回原色
 */
$("#redBall dd p").click(function () {
    //    点击红球动画效果
    $(this).toggleClass("active1");
    ballNums();
});

/*
 *点击蓝球，变蓝，再点击变回原色
 */
$("#blueBall li p").click(function () {
//    点击篮球动画效果
    $(this).toggleClass("active2");
    ballNums();
});
/*
 *点击蓝球的全选，选中所有蓝球
 */
var select_all = true;
$("#selectallBox").click(function () {
    if (select_all) {
        //点击全选，全选蓝球
        $("#blueBall li p").addClass('active2');
        $(this).text('全清');
        select_all = false;
    } else {
        //点击全清，零选蓝球
        $(".active2").removeClass('active2');
        $(this).text('全选');
        select_all = true;
    }
    ballNums();
});
/*
 //输出选择的红球和蓝球数量
 */
function ballNums() {
    //    得出选出的红球数量
    rball_nums = $('.active1').length;
//    console.log(rball_nums);
    //    得出选出的蓝球数量
    bball_nums = $('.active2').length;
    var ball_text = "您选了" + rball_nums + "个红球，" + bball_nums + "个蓝球，共0注,0元";
    $("#confirmBallnums").text(ball_text);
}
/*
 //选择随机选红球的数量
 */
$("#machineRBnums dt").click(function () {
    $(this).css('border-color', 'blue');
    $("#machineRBnums dd").toggle();
    $("#machineRBnums dd").css('border-color', 'blue');
});
$("#machineRBnums dd p").click(function () {
    var red_num = $(this).text();
    $("#machineRBnums dt em").text(red_num);
    $("#machineRBnums dd").hide();
});
/*
 //点击机选红球
 */
$("#machineSelectedred").click(function () {
    arr_red = new Array();
//    选择随机红球个数
    maxrnums = parseInt($("#machineRBnums dt em").text());
    redRand();
    ballNums();
});
//取机选红球数量函数
function redRand() {
    $('#redBall dd p').removeClass('active1');
    //输出0～33之间的随机整数
    var rrandom_integer = parseInt(Math.random() * 33 + 1);
    if (rrandom_integer < 10) {
        rrandom_integer = '0' + rrandom_integer;
    }
    arr_red.push(rrandom_integer);
    $.unique(arr_red);
//      console.log(rrandom_integer);
    if (arr_red.length < maxrnums) {
        redRand();
    } else {
        $.each(arr_red, function (i, n) {
            $("#redBall p").each(function (k, v) {
                if ($(v).text() == n) {
                    $(this).addClass('active1');
                }
            });
        });
    }
}
//    清空红球
function emptyRedballs() {
    $('#redBall dd p').removeClass('active1');
    ballNums();
}

/*
 //选择随机选蓝球的数量
 */
$("#machineBBnums dt").click(function () {
    $(this).css('border-color', 'blue');
    $("#machineBBnums dd").toggle();
    $("#machineBBnums dd").css('border-color', 'blue');
});
$("#machineBBnums dd p").click(function () {
    var red_num = $(this).text();
    $("#machineBBnums dt em").text(red_num);
    $("#machineBBnums dd").hide();
});

/*
 //点击机选蓝球
 */
$("#machineSelectedblue").click(function () {
    arr_blue = new Array();
//    取机选篮球个数
    maxbnums = parseInt($("#machineBBnums dt em").text());
    blueRan();
    ballNums();
});
function blueRan() {
    $('#blueBall p').removeClass('active2');
    obj = Math.floor(Math.random() * 16 + 1);
    if (obj < 10) {
        obj = '0' + obj;
    }
    arr_blue.push(obj);
    $.unique(arr_blue);
    if (arr_blue.length < maxbnums) {
        blueRan();
    } else {
        $(arr_blue).each(function (k, v) {
            $('#blueBall p').each(function () {
                if ($(this).text() == v) {
                    $(this).addClass('active2');
                }
            });
        });
    }
}
//    清空蓝球
function emptyBlueballs() {
    $('#blueBall p').removeClass('active2');
    ballNums();
}
/*
 //确定选号
 */

$("#confirmBox").click(function () {
    if (rball_nums >= 6 && bball_nums != 0) {
        red_text = $(".active1").text();
        redBallnums();
//        console.log(arr_r);
        blue_text = $(".active2").text();
        blueBallnums();
//        console.log(arr_b);
        $(".revise_area").remove();
        var str = "<li><span><b>单式 &nbsp;</b><b class=\"red_balls\">" + arr_r.sort().join(' ') + "</b><b>+</b> <b class=\"blue_balls\">" + arr_b.sort().join(' ') + "</b> [1注,2元]</span> <strong>修改</strong> <em>删除</em></li>";
        $("#endBallbox").prepend(str);
        $("#redBall dd p").removeClass('active1');
        $('#blueBall p').removeClass('active2');
        ballNums();
    } else {
        if (rball_nums < 6 && bball_nums == 0) {
            var error = "您选了（" + rball_nums + "红 +" + 0 + "蓝）,请至少再选" + (6 - rball_nums) + "个红球+" + 1 + "个蓝球";
            alert(error);
        }
        if (rball_nums < 6 && bball_nums != 0) {
            var error = "您选了（" + rball_nums + "红 +" + bball_nums + "蓝）,请至少再选" + (6 - rball_nums) + "个红球";
            alert(error);
        }
        if (rball_nums >= 6 && bball_nums == 0) {
            var error = "您选了（" + rball_nums + "红 +" + 0 + "蓝）,请至少再选" + 1 + "个蓝球";
            alert(error);
        }
    }
});
function redBallnums() {
    arr_r = new Array();
    var i = 0;
    while (i <= red_text.length - 1) {
        var r = red_text.substr(i, 2);
        arr_r.push(r);
        i += 2;
    }
}
function blueBallnums() {
    arr_b = new Array();
    var n = 0;
    while (n <= blue_text.length - 1) {
        var b = blue_text.substr(n, 2);
        arr_b.push(b);
        n += 2;
    }
}
//点击删除
$("#endBallbox").delegate('li em', 'click', function () {
    //alert(1);
    $(this).parent().remove();
});
//点击修改
$("#endBallbox").delegate('strong', 'click', function () {
    emptyRedballs();
    emptyBlueballs();
    $(this).parent().siblings().removeClass("revise_area");
    $(this).parent().addClass("revise_area");
//    console.log(rebb);
    var rball_text = $(this).siblings("span").find(".red_balls").text();
//    字符串分割成字符串数组
    rball_text = rball_text.split(' ');
    var bball_text = $(this).siblings("span").find(".blue_balls").text();
//    字符串分割成字符串数组
    bball_text = bball_text.split(' ');
//    alert(bball_text);
    $(rball_text).each(function (k, v) {
        $('#redBall dd p').each(function () {
            if ($(this).text() == v) {
                $(this).addClass('active1');
            }
        });
    });
    $(bball_text).each(function (k, v) {
        $('#blueBall li p').each(function () {
            if ($(this).text() == v) {
                $(this).addClass('active2');
            }
        });
    });
});
//清空选号
function emptyBox() {
    $("#redBall dd p").removeClass('active1');
    $('#blueBall p').removeClass('active2');
    ballNums();
}
//机选n注
function jiXuan(num) {
//    console.log(num);
    for (i = 1; i <= num; i++) {
        arr_red = new Array();
        maxrnums = 6;
        redRand();
        arr_blue = new Array();
        maxbnums = 1;
        blueRan();
        console.log(arr_red)
        var str = "<li><span><b>单式 &nbsp;</b><b class=\"red_balls\">" + arr_red.sort().join(' ') + "</b><b>+</b> <b class=\"blue_balls\">" + arr_blue.sort().join(' ') + "</b> [1注,2元]</span> <strong>修改</strong> <em>删除</em></li>";
        $("#endBallbox").prepend(str);
    }
    $("#redBall dd p").removeClass('active1');
    $('#blueBall p').removeClass('active2');
}

//清空列表
$("#clearList").click(function () {
    $("#endBallbox").find("li").remove();
});