var img_L = 0;
var img_T = 0;
var targetObj;
var num = 0;
let numbers = ['one', 'two', 'three', 'four', 'five']
let save_dmvy
let ct2
let list = []

function getLeft(o) {
    return parseInt(o.style.left.replace('px', ''));
}

function getTop(o) {
    return parseInt(o.style.top.replace('px', ''));
}

// 이미지 움직이기
function moveDrag(e) {
    var e_obj = window.event ? window.event : e;
    var dmvy = parseInt(e_obj.clientY + img_T);
    if (dmvy < save_dmvy) {
        for (let num2 = 0; num2 < $('.test2').children().eq(ct2).children().length; num2++) {
            if (num2 >= num) {
                continue
            }
            let top = $('.test2').children().eq(ct2).children().eq(num2).css('top').replace('px', '')
            if (parseInt(top)+20 > dmvy) {
                let className = $('.test2').children().eq(ct2).children().eq(num2).attr('class').split(' ')
                $('.test2').children().eq(ct2).children().eq(num2).css('top', num * 50 + 'px')
                $('.test2').children().eq(ct2).children().eq(num).insertBefore('.'+className[0]+'.'+className[1]+'.'+className[2])
                num = num2
                list=[]
                for(let count=0;count<$('.test2').children().length;count++){
                    list.push($('.test2').children().eq(count).text().split(' '))
                }
            }
        }
    } else if (dmvy > save_dmvy) {
        for (let num2 = 0; num2 < $('.test2').children().eq(ct2).children().length; num2++) {
            if (num2 <= num) {
                continue
            }
            let top = $('.test2').children().eq(ct2).children().eq(num2).css('top').replace('px', '')
            if (parseInt(top)-20 < dmvy) {
                let className = $('.test2').children().eq(ct2).children().eq(num2).attr('class').split(' ')
                $('.test2').children().eq(ct2).children().eq(num2).css('top', num * 50 + 'px')
                $('.test2').children().eq(ct2).children().eq(num).insertAfter('.'+className[0]+'.'+className[1]+'.'+className[2])
                num = num2
                list=[]
                for(let count=0;count<$('.test2').children().length;count++){
                    list.push($('.test2').children().eq(count).text().split(' '))
                }
            }
        }
    }
    targetObj.style.top = dmvy + "px";
    save_dmvy = dmvy
    return false;
}

// 드래그 시작
function startDrag(e, obj) {
    num = $(obj).index()
    ct2 = $(obj).parent().index()
    targetObj = obj;
    var e_obj = window.event ? window.event : e;
    img_L = getLeft(obj) - e_obj.clientX;
    img_T = getTop(obj) - e_obj.clientY;

    document.onmousemove = moveDrag;
    document.onmouseup = stopDrag;
    if (e_obj.preventDefault) e_obj.preventDefault();
}

// 드래그 멈추기
function stopDrag() {
    targetObj.style.top = num * 50 + "px";
    document.onmousemove = null;
    document.onmouseup = null;
}
$(function () {
    for(let ct=0;ct<5;ct++) {
        let ul = '<ul></ul>'
        $('div[class=test2]').append(ul)
        for (let num = 0; num < 5; num++) {
            let div = '<li style="position: absolute;left: 0;top: ' + num * 50 + 'px" onmousedown="startDrag(event, this)" style="cursor:hand" class="item ' + numbers[num] +' _'+ct+ '">' + num+ct + '</li> '
            $('div').children().eq(ct+1).append(div)
            $('ul>li').children().eq(num).css('position', 'absolute').css('left', 0).css('top', 0)
        }
    }
})
