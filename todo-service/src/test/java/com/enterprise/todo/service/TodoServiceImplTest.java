package com.enterprise.todo.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.enterprise.todo.dto.request.CreateTodoRequest;
import com.enterprise.todo.dto.request.UpdateTodoRequest;
import com.enterprise.todo.dto.response.TodoResponse;
import com.enterprise.todo.exception.RepositoryException;
import com.enterprise.todo.exception.ServiceException;
import com.enterprise.todo.exception.TodoNotFoundException;
import com.enterprise.todo.model.Todo;
import com.enterprise.todo.model.TodoStatus;
import com.enterprise.todo.repository.TodoRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoServiceImpl todoService;

    @Before
    public void setUp() {
        todoService = new TodoServiceImpl();
        todoService.setTodoRepository(todoRepository);
    }

    // =========================================================
    // CREATE TODO
    // =========================================================

    @Test
    public void createTodo_validRequest_shouldCreateTodo() {

        CreateTodoRequest request = new CreateTodoRequest();
        request.setTitle("Learn testing");
        request.setDescription("Write unit tests");

        Todo savedTodo = new Todo();
        savedTodo.setId(1L);
        savedTodo.setUserId(10L);
        savedTodo.setTitle("Learn testing");
        savedTodo.setDescription("Write unit tests");
        savedTodo.setStatus(TodoStatus.TODO);

        when(todoRepository.save(any(Todo.class)))
                .thenReturn(savedTodo);

        TodoResponse response =
                todoService.createTodo(request, 10L);

        assertNotNull(response);
        assertEquals(Long.valueOf(1L), response.getId());
        assertEquals("Learn testing", response.getTitle());
        assertEquals("Write unit tests", response.getDescription());
        assertEquals(TodoStatus.TODO, response.getStatus());

        verify(todoRepository).save(any(Todo.class));
    }

    @Test(expected = ServiceException.class)
    public void createTodo_repositoryFailure_shouldThrowServiceException() {

        CreateTodoRequest request = new CreateTodoRequest();
        request.setTitle("Learn testing");
        request.setDescription("Write unit tests");

        when(todoRepository.save(any(Todo.class)))
                .thenThrow(new RepositoryException("Database error"));

        todoService.createTodo(request, 10L);
    }

    // =========================================================
    // GET TODO
    // =========================================================

    @Test
    public void getTodo_existingTodo_shouldReturnTodo() {

        Todo todo = new Todo();
        todo.setId(1L);
        todo.setUserId(10L);
        todo.setTitle("Learn testing");
        todo.setDescription("Write unit tests");
        todo.setStatus(TodoStatus.TODO);

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenReturn(todo);

        TodoResponse response =
                todoService.getTodo(1L, 10L);

        assertNotNull(response);
        assertEquals(Long.valueOf(1L), response.getId());
        assertEquals("Learn testing", response.getTitle());
        assertEquals("Write unit tests", response.getDescription());
        assertEquals(TodoStatus.TODO, response.getStatus());

        verify(todoRepository).findByIdAndUserId(1L, 10L);
    }

    @Test(expected = TodoNotFoundException.class)
    public void getTodo_notFound_shouldThrowTodoNotFoundException() {

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenReturn(null);

        todoService.getTodo(1L, 10L);
    }

    @Test(expected = ServiceException.class)
    public void getTodo_repositoryFailure_shouldThrowServiceException() {

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenThrow(new RepositoryException("Database error"));

        todoService.getTodo(1L, 10L);
    }

    // =========================================================
    // GET ALL TODOS
    // =========================================================

    @Test
    public void getTodos_existingTodos_shouldReturnTodos() {

        Todo todo1 = new Todo();
        todo1.setId(1L);
        todo1.setUserId(10L);
        todo1.setTitle("Todo 1");
        todo1.setDescription("Description 1");
        todo1.setStatus(TodoStatus.TODO);

        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setUserId(10L);
        todo2.setTitle("Todo 2");
        todo2.setDescription("Description 2");
        todo2.setStatus(TodoStatus.COMPLETED);

        when(todoRepository.findAllByUserId(10L))
                .thenReturn(Arrays.asList(todo1, todo2));

        List<TodoResponse> responses =
                todoService.getTodos(10L);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        assertEquals(Long.valueOf(1L), responses.get(0).getId());
        assertEquals("Todo 1", responses.get(0).getTitle());

        assertEquals(Long.valueOf(2L), responses.get(1).getId());
        assertEquals("Todo 2", responses.get(1).getTitle());

        verify(todoRepository).findAllByUserId(10L);
    }

    @Test
    public void getTodos_noTodos_shouldReturnEmptyList() {

        when(todoRepository.findAllByUserId(10L))
                .thenReturn(Arrays.asList());

        List<TodoResponse> responses =
                todoService.getTodos(10L);

        assertNotNull(responses);
        assertTrue(responses.isEmpty());

        verify(todoRepository).findAllByUserId(10L);
    }

    @Test(expected = ServiceException.class)
    public void getTodos_repositoryFailure_shouldThrowServiceException() {

        when(todoRepository.findAllByUserId(10L))
                .thenThrow(new RepositoryException("Database error"));

        todoService.getTodos(10L);
    }

    // =========================================================
    // UPDATE TODO
    // =========================================================

    @Test
    public void updateTodo_existingTodo_shouldUpdateTodo() {

        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setTitle("Updated title");
        request.setDescription("Updated description");
        request.setStatus(TodoStatus.COMPLETED);

        Todo todo = new Todo();
        todo.setId(1L);
        todo.setUserId(10L);
        todo.setTitle("Old title");
        todo.setDescription("Old description");
        todo.setStatus(TodoStatus.TODO);

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenReturn(todo);

        TodoResponse response =
                todoService.updateTodo(1L, 10L, request);

        assertNotNull(response);
        assertEquals("Updated title", response.getTitle());
        assertEquals("Updated description", response.getDescription());
        assertEquals(TodoStatus.COMPLETED, response.getStatus());

        verify(todoRepository).findByIdAndUserId(1L, 10L);
        verify(todoRepository).update(todo);
    }

    @Test(expected = TodoNotFoundException.class)
    public void updateTodo_notFound_shouldThrowTodoNotFoundException() {

        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setTitle("Updated title");
        request.setDescription("Updated description");
        request.setStatus(TodoStatus.COMPLETED);

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenReturn(null);

        todoService.updateTodo(1L, 10L, request);
    }

    @Test(expected = ServiceException.class)
    public void updateTodo_repositoryFailure_shouldThrowServiceException() {

        UpdateTodoRequest request = new UpdateTodoRequest();
        request.setTitle("Updated title");
        request.setDescription("Updated description");
        request.setStatus(TodoStatus.COMPLETED);

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenThrow(new RepositoryException("Database error"));

        todoService.updateTodo(1L, 10L, request);
    }

    // =========================================================
    // DELETE TODO
    // =========================================================

    @Test
    public void deleteTodo_existingTodo_shouldDeleteTodo() {

        Todo todo = new Todo();
        todo.setId(1L);
        todo.setUserId(10L);

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenReturn(todo);

        todoService.deleteTodo(1L, 10L);

        verify(todoRepository).findByIdAndUserId(1L, 10L);
        verify(todoRepository).deleteByIdAndUserId(1L, 10L);
    }

    @Test(expected = TodoNotFoundException.class)
    public void deleteTodo_notFound_shouldThrowTodoNotFoundException() {

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenReturn(null);

        todoService.deleteTodo(1L, 10L);
    }

    @Test(expected = ServiceException.class)
    public void deleteTodo_repositoryFailure_shouldThrowServiceException() {

        when(todoRepository.findByIdAndUserId(1L, 10L))
                .thenThrow(new RepositoryException("Database error"));

        todoService.deleteTodo(1L, 10L);
    }
}