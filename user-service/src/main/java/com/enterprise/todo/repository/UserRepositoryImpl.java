package com.enterprise.todo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enterprise.todo.exception.RepositoryException;
import com.enterprise.todo.model.User;

public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub

       String sql =
    "INSERT INTO users (username, name, email, password_hash) VALUES (?, ?, ?, ?)";

        try(java.sql.Connection connection = dataSource.getConnection();
            java.sql.PreparedStatement statement = connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS  
            )){

                statement.setString(1, user.getUsername());
                statement.setString(2, user.getName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPasswordHash());
                                
                statement.executeUpdate();

                try(ResultSet keys = statement.getGeneratedKeys()){
                    if(keys.next()){
                        user.setId(keys.getLong(1));
                    }else{
                        throw new RepositoryException("Failed to retrieve generated key for user");
                    }
                }

                LOGGER.info("Saving user with name={}", user.getUsername());
                return user;
            }catch (java.sql.SQLException e) {
                LOGGER.error("Failed to save user", e);
                throw new RepositoryException("Error saving user", e);
            }
    }

    @Override
    public User findById(Long id) {
        
        String sql = "SELECT id, username, name, email, password_hash FROM users WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setLong(1, id);
                LOGGER.info("Finding user by id={}", id);

                try(ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()) {
                        return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password_hash")
                        );
                    }
                    return null;
                }
            }catch (SQLException e) {
                LOGGER.error("Database error while finding user by id={}", id, e);
                throw new RepositoryException("Failed to find user by id", e);
            }
            
    }

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

   @Override
public User findByUsername(String username) {

    String sql =
        "SELECT id, username, name, email, password_hash " +
        "FROM users WHERE username = ?";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, username);

        try (ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return new User(
                    resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password_hash")
                );
            }

            return null;
        }

    } catch (SQLException e) {
        LOGGER.error("Database error while finding username={}", username, e);
        throw new RepositoryException("Failed to find user", e);
    }
}

   @Override
public boolean existsByUsername(String username) {

    String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, username);

        try (ResultSet rs = statement.executeQuery()) {
            rs.next();
            return rs.getInt(1) > 0;
        }

    } catch (SQLException e) {
        LOGGER.error("Database error checking username={}", username, e);
        throw new RepositoryException("Failed to check username", e);
    }
}
}
