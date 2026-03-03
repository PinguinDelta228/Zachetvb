package Models;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private String content;
    private int userId;
    private String authorUsername;
    private int topicId;
    private java.sql.Timestamp createdAt;

    public int getId() {
        return id;
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

    public int getTopicId() {
        return topicId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
