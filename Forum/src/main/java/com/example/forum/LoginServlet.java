package com.example.forum;

import Models.User;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.*;
import java.io.*;
import java.util.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setCreatedAt(rs.getTimestamp("created_at"));

                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("topics");
                return;
            }
            req.setAttribute("error", "Неверное имя пользователя или пароль");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Ошибка базы данных");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
