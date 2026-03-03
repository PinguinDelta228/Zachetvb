<%--
  Created by IntelliJ IDEA.
  User: StartSoft
  Date: 03.03.2026
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Topic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Форум</title>
</head>
<body>
<h1>Темы форума</h1>
<% if (session.getAttribute("user") != null) { %>
<p>Привет, <%= ((Models.User) session.getAttribute("user")).getUsername() %>! <a href="logout">Выйти</a></p>
<a href="create-topic">Создать новую тему</a>
<% } else { %>
<p><a href="login">Войдите</a> чтобы создавать темы и комментировать.</p>
<% } %>

<hr>
<%
  List<Topic> topics = (List<Topic>) request.getAttribute("topics");
  if (topics != null && !topics.isEmpty()) {
    for (Topic t : topics) {
%>
<div>
  <h3><a href="topic?id=<%= t.getId() %>"><%= t.getTitle() %></a></h3>
  <p><%= t.getContent() %></p>
  <small>Автор: <%= t.getAuthorUsername() %>, дата: <%= t.getCreatedAt() %></small>
</div>
<%
  }
} else {
%>
<p>Пока нет тем. Будьте первым!</p>
<% } %>
</body>
</html>