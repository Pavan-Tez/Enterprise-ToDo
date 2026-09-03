<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Todos</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right"><c:out value="${loggedInUsername}" /></span>
    </div>
    <section class="xp-content">
        <div class="form-actions" style="margin-bottom: 4px;">
            <div>
                <h1 class="xp-heading">My Todos</h1>
                <p class="xp-subtitle" style="margin-bottom: 0;">Keep track of what needs doing.</p>
            </div>
            <div class="form-actions" style="margin-top: 0;">
                <a class="xp-button primary-button" href="${pageContext.request.contextPath}/todos/create">New Todo</a>
                <form id="logoutForm" action="${pageContext.request.contextPath}/users/logout" method="post">
                    <button class="xp-button" type="button" onclick="openLogoutModal()">Sign Out</button>
                </form>
            </div>
        </div>

        <c:choose>
            <c:when test="${empty todos}">
                <p class="notice">No todos yet. Create one to get started.</p>
            </c:when>
            <c:otherwise>
                <div class="table-scroll">
                <table class="xp-table">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Created</th>
                            <th>Modified</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="todo" items="${todos}">
                            <tr>
                                <td><strong>${todo.title}</strong></td>
                                <td>${todo.description}</td>
                                <td>
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
                                </td>
                                <td style="color: var(--text-muted); white-space: nowrap;">
                                    <fmt:timeZone value="Asia/Kolkata">
                                        <fmt:formatDate value="${todo.createdAt}" pattern="dd MMM yyyy, hh:mm a" />
                                    </fmt:timeZone>
                                </td>
                                <td style="color: var(--text-muted); white-space: nowrap;">
                                    <fmt:timeZone value="Asia/Kolkata">
                                        <fmt:formatDate value="${todo.modifiedAt}" pattern="dd MMM yyyy, hh:mm a" />
                                    </fmt:timeZone>
                                </td>
                                <td>
                                    <a class="text-link" href="${pageContext.request.contextPath}/todos/${todo.id}">Open</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
    <footer class="xp-statusbar">${empty todos ? 'No items' : 'Todo list loaded'}</footer>
</main>

<!-- Logout Confirmation Modal -->
<div id="logoutModal" class="logout-modal">
    <div class="logout-dialog">
        <div class="logout-dialog-titlebar">
            <span>Sign Out</span>
            <button type="button" class="logout-close" onclick="closeLogoutModal()">&times;</button>
        </div>
        <div class="logout-dialog-content">
            <div class="logout-icon">!</div>
            <div class="logout-message">
                <strong>Are you sure you want to sign out?</strong>
                <p>You will need to sign in again to access your todos.</p>
            </div>
        </div>
        <div class="logout-dialog-actions">
            <button type="button" class="xp-button danger-button" onclick="confirmLogout()">Yes, sign out</button>
            <button type="button" class="xp-button" onclick="closeLogoutModal()">Cancel</button>
        </div>
    </div>
</div>

<script>
    function openLogoutModal() {
        document.getElementById("logoutModal").style.display = "flex";
    }
    function closeLogoutModal() {
        document.getElementById("logoutModal").style.display = "none";
    }
    function confirmLogout() {
        document.getElementById("logoutForm").submit();
    }
</script>
</body>
</html>
