package com.example.forum;

import Models.Topic;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.*;
import java.io.*;
import java.util.*;

@WebServlet("/topics")
public class TopicListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Topic> topics = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM topics ORDER BY created_at DESC");

            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setTitle(rs.getString("title"));
                topic.setContent(rs.getString("content"));
                topic.setUserId(rs.getInt("user_id"));
                topic.setAuthorUsername(rs.getString("author_username"));
                topic.setCreatedAt(rs.getTimestamp("created_at"));
                topics.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("topics", topics);
        req.getRequestDispatcher("/topics.jsp").forward(req, resp);
    }
}
