<%--
  Created by IntelliJ IDEA.
  User: StartSoft
  Date: 03.03.2026
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Models.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title><%= ((Topic) request.getAttribute("topic")).getTitle() %></title>
</head>
<body>
<%
  Topic topic = (Topic) request.getAttribute("topic");
  List<Comment> comments = (List<Comment>) request.getAttribute("comments");
%>

<h1><%= topic.getTitle() %></h1>
<p><%= topic.getContent() %></p>
<small>Автор: <%= topic.getAuthorUsername() %>, опубликовано: <%= topic.getCreatedAt() %></small>

<hr>
<h3>Комментарии</h3>
<%
  if (comments != null && !comments.isEmpty()) {
    for (Comment c : comments) {
%>
<div>
  <p><%= c.getContent() %></p>
  <small>— <%= c.getAuthorUsername() %>, <%= c.getCreatedAt() %></small>
</div>
<%
  }
} else {
%>
<p>Пока нет комментариев. Будьте первым!</p>
<% } %>

<% if (session.getAttribute("user") != null) { %>
<h4>Добавить комментарий</h4>
<form method="post" action="add-comment">
  <input type="hidden" name="topicId" value="<%= topic.getId() %>">
  <textarea name="content" rows="4" cols="50" required></textarea><br>
  <input type="submit" value="Отправить">
</form>
<% } else { %>
<p><a href="login">Войдите</a>, чтобы оставить комментарий.</p>
<% } %>

<p><a href="topics">Назад к списку тем</a></p>
</body>
</html>
