<script src="${pageContext.request.contextPath}/resources/static/js/jquery.js"></script>
<ul style="list-style: none">
    <li>a</li>
    <li>a</li>
    <li>a</li>
    <li>a</li>
</ul>
<ul style="list-style: none">
    <li>a</li>
    <li>a</li>
    <li>a</li>
    <li>a</li>
</ul>
<div>
    <a><div style="width: 100px;height: 30px;border: none;background-color:ghostwhite;border-radius: 5px;cursor: pointer;}">list</div></a>
    <ul style="list-style: none">
        <li>a</li>
        <li>a</li>
        <li>a</li>
        <li>a</li>
    </ul>
</div>
<script>
    $('div').click(() => {
        console.log($(event.target).parent().find('ul').css('display'))
        if ($(event.target).parent().find('ul').css('display') === 'block') {
            $(event.target).parent().find('ul').hide()
        }
    })
</script>