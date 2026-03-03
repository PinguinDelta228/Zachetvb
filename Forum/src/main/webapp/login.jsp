<%--
  Created by IntelliJ IDEA.
  User: StartSoft
  Date: 03.03.2026
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Вход в форум</title>
</head>
<body>
<h2>Вход в систему</h2>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form method="post" action="login">
  <label for="username">Имя пользователя:</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Пароль:</label>
  <input type="password" id="password" name="password" required><br><br>

  <input type="submit" value="Войти">
</form>

<p>Нет аккаунта? <a href="register">Зарегистрироваться</a></p>
</body>
</html>
