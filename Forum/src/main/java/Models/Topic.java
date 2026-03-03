package Models;

import java.sql.Timestamp;

public class Topic {
    private int id;
    private String title;
    private String content;
    private int userId;
    private String authorUsername;
    private java.sql.Timestamp createdAt;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

