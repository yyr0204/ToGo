function set_course_box(data){
    let newDiv = '<div class="course_box PlaceDiv '+data.day+'day_'+data.num+'" title="' + data.name + '"  style="position:absolute; left:0px; top:'+(data.num*100)+'px; cursor:pointer; cursor:hand" onmousedown="startDrag(event, this)"> <div class="img_div"> ' +
        '<img src="https://cdn.pixabay.com/photo/2023/08/02/18/21/yoga-8165759_1280.jpg" alt="# "></div>' +
        '<div class="info_div"><div><span>' + data.name + '</span></div><div><span>' + data.adress + '</span></div>' +
        '</div><div><button class="listRemove button" title="일정에서 삭제">X</button></div></div>'
    return newDiv
}