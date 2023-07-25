
$(document).on('mouseover','#adList_box',function (){
    console.log($(event.target))
    let isPress = false,
        prevPosX = 0,
        prevPosY = 0;

    $(event.target).onmouseup = end;

    // 상위 영역
    window.onmousemove = move;

    $(document).on('mousedown','#adList_box',function start() {
        console.log('start')
        prevPosX = $(event.target).clientX;
        prevPosY = $(event.target).clientY;

        isPress = true;
    })

    $(document).on('mousemove','#adList_box',function move(e) {
        if (!isPress) return;
        console.log('move')
        const posX = prevPosX - e.clientX;
        const posY = prevPosY - e.clientY;

        prevPosX = e.clientX;
        prevPosY = e.clientY;

        $(event.target).style.left = ($(event.target).offsetLeft - posX) + "px";
        $(event.target).style.top = ($(event.target).offsetTop - posY) + "px";
    })

    function end() {
        console.log('end')
        isPress = false;
    }
})