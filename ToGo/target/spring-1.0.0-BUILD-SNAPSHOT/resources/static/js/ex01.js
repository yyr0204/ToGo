try {
    $.ajax({
        data: {area: tourInfo.area, str: 1, end: 20},
        type: "POST",
        url: '/ToGo/map/place_list',
        success: function (data) {
            console.log(data)
            for (let num in data) {
                let div =
                    "<div class='recommendPlaceDiv' id='placeDiv" + num + "'>\n" +
                    " <div class='item' title='img_area'><img \n" +
                    "src=\"${pageContext.request.contextPath}/resources/static/img/attr.png\"></div>\n" +
                    "<div class=\"item recommendPlace_name\">\n" +
                    "<div class=\"name_area\">\n" +
                    "<span class=\"place_name\" title=\"" + data[num].name + "\"><h7>" + data[num].name + "</h7></span> </div>\n" +
                    "<div class=\"address_area\">\n" +
                    "<span class=\"place_name\" title=\"" + data[num].adress + "\"><h7>" + data[num].adress + "</h7></span>\n" +
                    "</div> </div> <div> <input type=\"radio\" value=\"" + data[num].name + "\" class=\"city_add_button\">\n </div> </div>"
                $('#cityList').append(div)
            }
        }, error: function () {
            alert('에러')
        }
    })
} catch (e) {
    console.log(e)
}
let div = "    <c:forEach var=\"item\" items=\"${lodgingList}\" varStatus=\"str\">\n" +
    "                <div class=\"recommendLodgingDiv\" id=\"placeDiv${str.count}\">\n" +
    "                    <div class=\"item\" title=\"img_area\"><img\n" +
    "                            src=\"${pageContext.request.contextPath}/resources/static/img/hotel.png\"></div>\n" +
    "                    <div class=\"item recommendLodging_name\">\n" +
    "                        <div class=\"name_area\">\n" +
    "                            <span class=\"lodging_name\" title=\"${item.name}\"><h7>${item.name}</h7></span>\n" +
    "                        </div>\n" +
    "                        <div class=\"address_area\">\n" +
    "                            <span class=\"lodging_name\" title=\"${item.adress}\"><h7>${item.adress}</h7></span>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div>\n" +
    "                        <input type=\"radio\" value=\"${item.name}\" class=\"lodging_add_button\">\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </c:forEach>"

$(document).off('change').on('change','.city_add_button', function () {
    if ($(document).find('.active').length >= 1) {
        $(event.target).parent().parent().hide()
        let name = $(event.target).attr('value')
        var newDiv = '<li>\n<div class="placeDiv">\n<div>\n<img src="${pageContext.request.contextPath}/resources/static/img2/20201230173806551_JRT8E1VC.png">\n' +
            '</div>\n<div style="display: grid;grid-template-rows: 2fr 3fr">\n' +
            '<div>\n<span>' + name + '</span>\n</div>\n<div></div>\n</div>\n</div>\n</li>'
        $('.active').parent().find('ul').append(newDiv)
        // add_marker(name, attrList[name].center)
    } else {
        alert('여행일차를 정해주세요')
        $(this).prop('checked', false)
    }
})