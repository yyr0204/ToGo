var img_L = 0;
var img_T = 0;
var targetObj;
var num = 0;
let numbers = ['one', 'two', 'three', 'four', 'five']
let save_dmvy=0
let ct2
let list = {}

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
        for (let num2= 0; num2 < $('#select_place_list').children().eq(ct2).find('.day_info_list').children().length; num2++) {
            if (num2 >= num) {
                continue
            }
            let top = $('#select_place_list').children().eq(ct2).find('.day_info_list').children().eq(num2).css('top').replace('px', '')
            if (parseInt(top)+20 > dmvy) {
                list = get_sch()
                var temp = list[(ct2+1)+'일차'][num2]
                list[(ct2+1)+'일차'][num2] = list[(ct2+1)+'일차'][num]
                list[(ct2+1)+'일차'][num]=temp
                set_sch(list)
                let className = $('#select_place_list').children().eq(ct2).find('.day_info_list').children().eq(num2).attr('class').split(' ')[1]
                let className2 = $('#select_place_list').children().eq(ct2).find('.day_info_list').children().eq(num).attr('class').split(' ')[1]
                console.log(className)
                console.log(className2)
                $('.'+className).css('top', num * 100 + 'px')
                $('.'+className2).insertBefore('.'+className)
                num = num2
            }
        }
    } else if (dmvy > save_dmvy) {
        for (let num2 = 0; num2 < $('#select_place_list').children().eq(ct2).find('.day_info_list').children().length; num2++) {
            if (num2 <= num) {
                continue
            }
            let top = $('#select_place_list').children().eq(ct2).find('.day_info_list').children().eq(num2).css('top').replace('px', '')
            if (parseInt(top)-20 < dmvy) {
                list = get_sch()
                var temp = list[(ct2+1)+'일차'][num2]
                list[(ct2+1)+'일차'][num2] = list[(ct2+1)+'일차'][num]
                list[(ct2+1)+'일차'][num]=temp
                set_sch(list)
                let className = $('#select_place_list').children().eq(ct2).find('.day_info_list').children().eq(num2).attr('class').split(' ')
                let className2 = $('#select_place_list').children().eq(ct2).find('.day_info_list').children().eq(num).attr('class').split(' ')
                console.log(className[1])
                console.log(className2[1])
                $('.'+className[1]).css('top', num * 100 + 'px')
                $('.'+className2[1]).insertAfter('.'+className[1])
                num = num2
                console.log(list)
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
    ct2 = $(obj).parent().parent().index()
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
    save_dmvy=0;
    targetObj.style.top = num * 100 + "px";
    document.onmousemove = null;
    document.onmouseup = null;
}
// $(function () {
//     for(let ct=0;ct<5;ct++) {
//         let ul = '<ul></ul>'
//         $('div[class=test2]').append(ul)
//         for (let num = 0; num < 5; num++) {
//             let div = '<li style="position: absolute;left: 0;top: ' + num * 50 + 'px" onmousedown="startDrag(event, this)" style="cursor:hand" class="item ' + numbers[num] +' _'+ct+ '">' + num+ct + '</li> '
//             $('div').children().eq(ct+1).append(div)
//             $('ul>li').children().eq(num).css('position', 'absolute').css('left', 0).css('top', 0)
//         }
//     }
// })
