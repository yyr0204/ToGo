<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form method = "post" name = "planform" action = "place" >
	<table board="1">
		지역 : <input type="text" name="area"/> <br />
		<input type="date" name="startDay"/> ~ <input type="date" name="endDay"/>
		<input type="submit" value="확인" />
	</table>
</form>