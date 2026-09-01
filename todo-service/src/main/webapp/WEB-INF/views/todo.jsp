<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Todo</title>
</head>
<body>

<h1>Todo</h1>

<p><strong>Title:</strong> ${todo.title}</p>

<p><strong>Description:</strong> ${todo.description}</p>

<p><strong>Status:</strong> ${todo.status}</p>

<hr>

<h2>Update Todo</h2>

<form action="${pageContext.request.contextPath}/todos/${todo.id}/update"
      method="post">

    <label>Title:</label>
    <input type="text" name="title" value="${todo.title}" required>

    <br><br>

    <label>Description:</label>
    <textarea name="description">${todo.description}</textarea>

    <br><br>

    <label>Status:</label>

    <select name="status">

        <option value="TODO"
            ${todo.status == 'TODO' ? 'selected' : ''}>
            TODO
        </option>

        <option value="IN_PROGRESS"
            ${todo.status == 'IN_PROGRESS' ? 'selected' : ''}>
            IN PROGRESS
        </option>

        <option value="COMPLETED"
            ${todo.status == 'COMPLETED' ? 'selected' : ''}>
            COMPLETED
        </option>

    </select>

    <br><br>

    <button type="submit">Update</button>

</form>

<br>

<form action="${pageContext.request.contextPath}/todos/${todo.id}/delete"
      method="post">

    <button type="submit">Delete</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/todos">
    Back to Todos
</a>

</body>
</html>