function select_open() {
    //화면 높이와 너비를 구합니다.
    let maskHeight = $(document).height();
    let maskWidth = window.document.body.clientWidth;
    //출력할 마스크를 설정해준다.
    let mask ="<div id='mask' style='position:absolute; z-index:90000; background-color:#000000; display:none; left:0; top:0;'></div>";
    // 로딩 이미지 주소 및 옵션
    let loadingImg ='';
    loadingImg += "<div class='warning_bar'>";
    loadingImg += "<div><span>여행일차를 정해주세요</span></div>";
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