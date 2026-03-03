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

@WebServlet("/create-topic")
public class TopicCreateServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }
        req.getRequestDispatcher("/createTopic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO topics (title, content, user_id, author_username) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, user.getId());
            stmt.setString(4, user.getUsername());
            stmt.executeUpdate();

            resp.sendRedirect("topics");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Ошибка при создании темы");
            req.getRequestDispatcher("/createTopic.jsp").forward(req, resp);
        }
    }
}
