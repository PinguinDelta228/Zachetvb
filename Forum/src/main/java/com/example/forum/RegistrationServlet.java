package com.example.forum;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.sql.*;
import java.io.*;
import java.util.*;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");


        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.executeUpdate();
            resp.sendRedirect("login");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Ошибка регистрации. Возможно, имя или email уже заняты. или ошибка бд");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

    }

}
