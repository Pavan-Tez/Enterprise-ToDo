<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>My Todos</title>
</head>
<body>

<h1>My Todos</h1>

<a href="${pageContext.request.contextPath}/todos/create">
    Create Todo
</a>

<br><br>

<c:choose>

    <c:when test="${empty todos}">
        <p>You don't have any todos.</p>
    </c:when>

    <c:otherwise>

        <table border="1">
            <tr>
                <th>Title</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.title}</td>
                    <td>${todo.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/todos/${todo.id}">
                            View
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </c:otherwise>

</c:choose>

</body>
</html>