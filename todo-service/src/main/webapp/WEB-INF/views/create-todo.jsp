<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Create Todo</title>
</head>
<body>

<h1>Create Todo</h1>

<form action="${pageContext.request.contextPath}/todos/create"
      method="post">

    <label>Title:</label>
    <input type="text" name="title" required>

    <br><br>

    <label>Description:</label>
    <textarea name="description"></textarea>

    <br><br>

    <button type="submit">Create Todo</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/todos">
    Back to Todos
</a>

</body>
</html>