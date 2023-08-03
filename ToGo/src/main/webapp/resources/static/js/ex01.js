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