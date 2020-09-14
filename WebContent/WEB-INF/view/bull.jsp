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
<title>掲示板</title>
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

	<form action="/BulletinBoard/Bull" method="POST" enctype="multipart/form-data">
		投稿者名<br>
		<input type="text" name="human"><br>
		メールアドレス<br>
		<input type="email" name="mail"><br>
		内容<br>
		<textarea rows="10" cols="50" name="content"></textarea><br>
		<input type="text" name="id">
		<input type="file" name="file"/><br />
		<% Date date = new Date();
		   String time = String.valueOf(date);
		   time =  time.replace("GMT+09:00" , "");

       	   session.setAttribute("time", time);
       	 %>
		<input type="submit" value="投稿">
	</form><br>

	<form action="/BulletinBoard/EdiBull" method="post">
		<input type="submit" value="管理画面へ" class="btn1">
	</form>
	============================================================================<br>
	投稿一覧<br>
	<% ArrayList<Contents> list = BullDao.selectAllcon();%>
	<% if(BullDao.selectAllcon() == null){
		System.out.println("何もない");
	}else{
	%>
	<% for(Contents s : list){%>
			<%= s.getConts() %><br>
			投稿者名:<%= s.getName()%>
			メールアドレス:<%= s.getMail()%>
			投稿時間:<%= s.getTime() %>
			編集時間:<%= s.getEdit() %><br>
			<img alt="" src="/BulletinBoard/upload/<%= s.getFilen() %>" width="100" height="100"><br>
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