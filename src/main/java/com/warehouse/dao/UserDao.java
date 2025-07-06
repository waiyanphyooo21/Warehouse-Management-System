package com.warehouse.dao;

import com.warehouse.beans.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    private JdbcTemplate template;

    // BCrypt encoder instance
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Save user with hashed password
    public int save(User user) {
        String hashedPassword = encoder.encode(user.getPassword());
        String sql = "INSERT INTO users(username, password, full_name, email, is_admin) VALUES (?, ?, ?, ?, ?)";
        return template.update(sql,
                user.getUsername(),
                hashedPassword,
                user.getFullName(),
                user.getEmail(),
                user.isAdmin());
    }

    // Update user with hashed password
    public int update(User user) {
        String hashedPassword = encoder.encode(user.getPassword());
        String sql = "UPDATE users SET username = ?, password = ?, full_name = ?, email = ?, is_admin = ? WHERE user_id = ?";
        return template.update(sql,
                user.getUsername(),
                hashedPassword,
                user.getFullName(),
                user.getEmail(),
                user.isAdmin(),
                user.getId());
    }

    // Delete user by id
    public int delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        return template.update(sql, id);
    }

    // Get user by id
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return template.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

    // Get user by username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return template.queryForObject(sql, new Object[]{username}, new UserMapper());
    }

    // Get all users (with pagination)
    public List<User> getUsersByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM users LIMIT ?, ?";
        return template.query(sql, new Object[]{offset, pageSize}, new UserMapper());
    }

    // Count all users
    public int countUsers() {
        String sql = "SELECT COUNT(*) FROM users";
        return template.queryForObject(sql, Integer.class);
    }

    // Search users with pagination
    public List<User> searchUsers(String query, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = "SELECT * FROM users WHERE username LIKE ? OR full_name LIKE ? LIMIT ?, ?";
        String searchTerm = "%" + query + "%";
        return template.query(sql, new Object[]{searchTerm, searchTerm, offset, pageSize}, new UserMapper());
    }

    // Count users matching search query
    public int countSearchResults(String query) {
        String sql = "SELECT COUNT(*) FROM users WHERE username LIKE ? OR full_name LIKE ?";
        String searchTerm = "%" + query + "%";
        return template.queryForObject(sql, new Object[]{searchTerm, searchTerm}, Integer.class);
    }

    // Password verification utility (can be used in your service/login layer)
    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    // RowMapper to map ResultSet rows to User objects
    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setAdmin(rs.getBoolean("is_admin"));
            user.setCreatedAt(rs.getTimestamp("created_at"));
            return user;
        }
    }
}
