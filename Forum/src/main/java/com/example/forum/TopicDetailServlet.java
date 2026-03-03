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

@WebServlet("/topic")
public class TopicDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int topicId = Integer.parseInt(req.getParameter("id"));
        Topic topic = null;
        List<Comment> comments = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // Получаем тему
            String topicSql = "SELECT * FROM topics WHERE id = ?";
            PreparedStatement topicStmt = conn.prepareStatement(topicSql);
            topicStmt.setInt(1, topicId);
            ResultSet rsTopic = topicStmt.executeQuery();

            if (rsTopic.next()) {
                topic = new Topic();
                topic.setId(rsTopic.getInt("id"));
                topic.setTitle(rsTopic.getString("title"));
                topic.setContent(rsTopic.getString("content"));
                topic.setUserId(rsTopic.getInt("user_id"));
                topic.setAuthorUsername(rsTopic.getString("author_username"));
                topic.setCreatedAt(rsTopic.getTimestamp("created_at"));
            }

            // Получаем комментарии к теме
            String commentSql = "SELECT * FROM comments WHERE topic_id = ? ORDER BY created_at ASC";
            PreparedStatement commentStmt = conn.prepareStatement(commentSql);
            commentStmt.setInt(1, topicId);
            ResultSet rsComment = commentStmt.executeQuery();

            while (rsComment.next()) {
                Comment comment = new Comment();
                comment.setId(rsComment.getInt("id"));
                comment.setContent(rsComment.getString("content"));
                comment.setUserId(rsComment.getInt("user_id"));
                comment.setAuthorUsername(rsComment.getString("author_username"));
                comment.setTopicId(rsComment.getInt("topic_id"));
                comment.setCreatedAt(rsComment.getTimestamp("created_at"));
                comments.add(comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("topic", topic);
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/topic.jsp").forward(req, resp);
    }
}
