package com.enterprise.todo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enterprise.todo.exception.RepositoryException;
import com.enterprise.todo.model.Todo;
import com.enterprise.todo.model.TodoStatus;

public class TodoRepositoryImpl implements TodoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRepositoryImpl.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Todo save(Todo todo) {
       
        String sql = "INSERT INTO todos "+
                "(user_id, title, description, status) "+
                "VALUES (?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            )){
                statement.setLong(1, todo.getUserId());
                statement.setString(2, todo.getTitle());
                statement.setString(3, todo.getDescription());
                statement.setString(4, todo.getStatus().name());

                statement.executeUpdate();

                try(ResultSet resultSet = statement.getGeneratedKeys()){
                    if(resultSet.next()){
                        Long generatedId = resultSet.getLong(1);
                        todo.setId(generatedId);
                    }
                }
                LOGGER.info("Todo saved successfully with ID: {}", todo.getId());
                return todo;
            }catch(SQLException e){
                LOGGER.error("Error saving todo: {}", e.getMessage(), e);
                throw new RepositoryException("Error saving todo", e);
            }
    }

    @Override
    public Todo findByIdAndUserId(Long todoId, Long userId) {
       
        String sql = 
                    "SELECT id, user_id, title, description, status "+
                    "FROM todos "+
                    "WHERE id = ? AND user_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, todoId);
                statement.setLong(2, userId);

                try(ResultSet resultSet = statement.executeQuery()){
                    if(resultSet.next()){
                        Todo todo = new Todo();
                        todo.setId(resultSet.getLong("id"));
                        todo.setUserId(resultSet.getLong("user_id"));
                        todo.setTitle(resultSet.getString("title"));
                        todo.setDescription(resultSet.getString("description"));
                        todo.setStatus(TodoStatus.valueOf(resultSet.getString("status")));
                        LOGGER.info("Todo found with ID: {}", todo.getId());
                        return todo;
                    }else{
                        LOGGER.info("No todo found with ID: {} and User ID: {}", todoId, userId);
                        return null;
                    }
                }
            }catch(SQLException e){
                LOGGER.error("Error finding todo: {}", e.getMessage(), e);
                LOGGER.error("Error finding todo with ID: {} and User ID: {}", todoId, userId, e);
                throw new RepositoryException("Error finding todo", e);
            }   
    }

    @Override
    public List<Todo> findAllByUserId(Long userId) {
        
        String sql = 
                    "SELECT id, user_id, title, description, status "+
                    "FROM todos "+
                    "WHERE user_id = ?";

        List<Todo> todos = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, userId);

                try(ResultSet resultSet = statement.executeQuery()){
                    while(resultSet.next()){
                        Todo todo = new Todo();
                        todo.setId(resultSet.getLong("id"));
                        todo.setUserId(resultSet.getLong("user_id"));
                        todo.setTitle(resultSet.getString("title"));
                        todo.setDescription(resultSet.getString("description"));
                        todo.setStatus(TodoStatus.valueOf(resultSet.getString("status")));
                        todos.add(todo);
                    }
                }
                LOGGER.info("Found {} todos for User ID: {}", todos.size(), userId);
                return todos;
            }catch(SQLException e){
                LOGGER.error("Error finding todos for User ID: {}", userId, e);
                throw new RepositoryException("Error finding todos", e);
            }

    }

    @Override
    public void update(Todo todo) {

        String sql = 
                    "UPDATE todos "+
                    "SET title = ?, description = ?, status = ? "+
                    "WHERE id = ? AND user_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, todo.getTitle());
                statement.setString(2, todo.getDescription());
                statement.setString(3, todo.getStatus().name());
                statement.setLong(4, todo.getId());
                statement.setLong(5, todo.getUserId());

                int rowsAffected = statement.executeUpdate();
                if(rowsAffected == 0){
                    LOGGER.warn("No todo found to update with ID: {} and User ID: {}", todo.getId(), todo.getUserId());
                    throw new RepositoryException("No todo found to update");
                }
                LOGGER.info(
                            "Todo update completed. todoId={}, userId={}, rowsAffected={}",
                            todo.getId(),
                            todo.getUserId(),
                            rowsAffected
                            );
            }catch(SQLException e){
                LOGGER.error("Error updating todo: {}", e.getMessage(), e);
                throw new RepositoryException("Error updating todo", e);
            }
       
    }

    @Override
    public void deleteByIdAndUserId(Long todoId, Long userId) {
        String sql = 
                    "DELETE FROM todos "+
                    "WHERE id = ? AND user_id = ?";
        
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, todoId);
                statement.setLong(2, userId);

                int rowsAffected = statement.executeUpdate();
                if(rowsAffected == 0){
                    LOGGER.warn("No todo found to delete with ID: {} and User ID: {}", todoId, userId);
                    throw new RepositoryException("No todo found to delete");
                }
                LOGGER.info(
                            "Todo deletion completed. todoId={}, userId={}, rowsAffected={}",
                            todoId,
                            userId,
                            rowsAffected
                            );
            }catch(SQLException e){
                LOGGER.error("Error deleting todo: {}", e.getMessage(), e);
                throw new RepositoryException("Error deleting todo", e);
            }
    }

   
    
}
