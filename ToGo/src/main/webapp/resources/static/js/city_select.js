function select_open() {
    //화면 높이와 너비를 구합니다.
    let maskHeight = $(document).height();
    let maskWidth = window.document.body.clientWidth;
    //출력할 마스크를 설정해준다.
    let mask ="<div id='mask' style='position:absolute; z-index:90000; background-color:#000000; display:none; left:0; top:0;'></div>";
    // 로딩 이미지 주소 및 옵션
    let loadingImg ='';
    loadingImg += "<div class='select_cityList_div'>";
    loadingImg += "<div class='item'><span>지역 선택</span><a class='close' href='#'></a></div>"
    loadingImg += "<div class='item city_list'>"
    loadingImg += "<div class='item cityName'>서울</div>"
    loadingImg += "<div class='item cityName'>부산</div>"
    loadingImg += "<div class='item cityName'>충청도</div>"
    loadingImg += "<div class='item cityName'>대구</div>"
    loadingImg += "<div class='item cityName'>대전</div>"
    loadingImg += "<div class='item cityName'>강원도</div>"
    loadingImg += "<div class='item cityName'>광주</div>"
    loadingImg += "<div class='item cityName'>경기도</div>"
    loadingImg += "<div class='item cityName'>경상도</div>"
    loadingImg += "<div class='item cityName'>인천</div>"
    loadingImg += "<div class='item cityName'>전라도</div>"
    loadingImg += "<div class='item cityName'>세종</div>"
    loadingImg += "<div class='item cityName'>울산</div>"
    loadingImg += "<div class='item cityName'>제주</div>"
    loadingImg += "</div>";
    loadingImg += "</div>";
    //레이어 추가
    $('body')
        .append(mask)
        .append(loadingImg)
    //마스크의 높이와 너비로 전체 화면을 채운다.
    $('#mask').css({
        'width' : maskWidth,
        'height': maskHeight,
        'opacity' :'0.3'
    });
    //마스크 표시
    $('#mask').show();
    //로딩 이미지 표시
    $('.loadingImg').show();
}
function select_close(){
    $('#mask, .select_cityList_div').hide();
    $('#mask, .select_cityList_div').empty();
}