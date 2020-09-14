<%@ page import = "java.util.Date" %>
<%@ page import = "dao.BullDao" %>
<%@ page import = "dto.Contents" %>
<%@ page import = "java.util.ArrayList" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<style>
body { width: 640px; }

.btn1 {
  display: block;
  margin: 0 0 0 auto;
}

</style>
</head>
<body>

	<%
	request.setCharacterEncoding("UTF-8");
	%>

	<form action="/BulletinBoard/Bull" method="post">
		<input type="submit" value="投稿画面へ" class="btn1">

	</form>
	============================================================================<br>
	投稿一覧<br>
	<% ArrayList<Contents> list = BullDao.selectAllcon();%>
	<%int i = 1; %>
	<% if(BullDao.selectAllcon() == null){
		System.out.println("何もない");
	 }else{
	 %>
	 <% for(Contents s : list){%>
			<%= s.getConts() %><br>
			投稿者名:<%= s.getName() %>
			メールアドレス:<%= s.getMail() %>
			投稿時間:<%= s.getTime() %>
			編集時間:<%= s.getEdit() %><br>

			<form action="/BulletinBoard/EdiBull">
				<input type="hidden" value=<%= s.getId() %> name = "id">
				<input type="submit" value="削除">
			</form>
			<%i++; %>
	-----------------------<br>
	  <%}%>
  <%} %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script>

$(function(){
	$('#submit').click(function(){
	});
});

</script>

</body>
</html>