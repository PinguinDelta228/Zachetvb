package com.example.forum;

import Models.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.*;
import java.io.*;
import java.util.*;

@WebServlet("/add-comment")
public class CommentAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }

        int topicId = Integer.parseInt(req.getParameter("topicId"));
        String content = req.getParameter("content");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO comments (content, user_id, author_username, topic_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, content);
            stmt.setInt(2, user.getId());
            stmt.setString(3, user.getUsername());
            stmt.setInt(4, topicId);
            stmt.executeUpdate();

            resp.sendRedirect("topic?id=" + topicId);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("topic?id=" + topicId + "&error=1");
        }
    }
}
