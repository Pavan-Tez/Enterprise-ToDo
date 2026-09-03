<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo Details</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">Todo #${todo.id}</span>
    </div>
    <section class="xp-content">
        <h1 class="xp-heading">${todo.title}</h1>

        <dl class="detail-list">
            <div>
                <dt>Description</dt>
                <dd>${todo.description}</dd>
            </div>
            <div>
                <dt>Status</dt>
                <dd>
                    <c:choose>
                        <c:when test="${todo.status == 'TODO'}">
                            <span class="badge badge-todo">To Do</span>
                        </c:when>
                        <c:when test="${todo.status == 'IN_PROGRESS'}">
                            <span class="badge badge-progress">In Progress</span>
                        </c:when>
                        <c:when test="${todo.status == 'COMPLETED'}">
                            <span class="badge badge-done">Completed</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-todo">${todo.status}</span>
                        </c:otherwise>
                    </c:choose>
                </dd>
            </div>
        </dl>

        <div class="divider"></div>

        <h2 class="xp-heading" style="font-size: 18px; margin-bottom: 16px;">Update Todo</h2>

        <form class="xp-form" action="${pageContext.request.contextPath}/todos/${todo.id}/update" method="post">
            <div class="form-row">
                <label for="title">Title</label>
                <input id="title" type="text" name="title" value="${todo.title}" required>
            </div>
            <div class="form-row">
                <label for="description">Description</label>
                <textarea id="description" name="description">${todo.description}</textarea>
            </div>
            <div class="form-row">
                <label for="status">Status</label>
                <select id="status" name="status">
                    <option value="TODO" ${todo.status == 'TODO' ? 'selected' : ''}>To Do</option>
                    <option value="IN_PROGRESS" ${todo.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                    <option value="COMPLETED" ${todo.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                </select>
            </div>
            <div class="form-actions">
                <button class="xp-button primary-button" type="submit">Save Changes</button>
                <a class="text-link" href="${pageContext.request.contextPath}/todos">Back to Todos</a>
            </div>
        </form>

        <div class="divider"></div>

        <form action="${pageContext.request.contextPath}/todos/${todo.id}/delete" method="post">
            <button class="xp-button danger-button" type="submit">Delete Todo</button>
        </form>
    </section>
    <footer class="xp-statusbar">Todo #${todo.id}</footer>
</main>
</body>
</html>
