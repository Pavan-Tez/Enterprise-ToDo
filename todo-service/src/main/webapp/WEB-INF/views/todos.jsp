<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>My Todos</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">

            <span>
                Enterprise Todo
            </span>

            <span>
                
                <c:out value="${loggedInUsername}" />
            </span>

        </div>

        <section class="xp-content">

            <div class="page-actions">

                <div>

                    <h1 class="xp-heading">
                        My Todos
                    </h1>

                    <p class="xp-subtitle">
                        Keep track of what needs doing.
                    </p>

                </div>

                <a
                    class="xp-button primary-button"
                    href="${pageContext.request.contextPath}/todos/create"
                >
                    Create Todo
                </a>

             <form
                id="logoutForm"
                action="${pageContext.request.contextPath}/users/logout"
                method="post"
            >
                <button
                    class="xp-button"
                    type="button"
                    onclick="openLogoutModal()"
                >
                    Log Off
                </button>
            </form>

            </div>

            <c:choose>

                <c:when test="${empty todos}">

                    <p class="notice">
                        You don't have any todos yet.
                        Create one to get started.
                    </p>

                </c:when>

                <c:otherwise>

                    <table class="xp-table">

                        <tr>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Created</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach var="todo" items="${todos}">

                            <tr>

                                <td>
                                    ${todo.title}
                                </td>

                                <td>
                                    ${todo.description}
                                </td>

                                <td>
                                    ${todo.status}
                                </td>

                                <td>
                                    <fmt:formatDate
                                        value="${todo.createdAt}"
                                        pattern="dd MMM yyyy, hh:mm a"
                                    />
                                </td>

                                <td>

                                    <a
                                        class="text-link"
                                        href="${pageContext.request.contextPath}/todos/${todo.id}"
                                    >
                                        Open
                                    </a>

                                </td>

                            </tr>

                        </c:forEach>

                    </table>

                </c:otherwise>

            </c:choose>

        </section>

        <footer class="xp-statusbar">
            ${empty todos ? 'No items' : 'Todo list loaded'}
        </footer>

    </main>

    <!-- Logout Confirmation Modal -->
        <div id="logoutModal" class="logout-modal">

            <div class="logout-dialog">

                <div class="logout-dialog-titlebar">
                    <span>Enterprise Todo — Log Off</span>

                    <button
                        type="button"
                        class="logout-close"
                        onclick="closeLogoutModal()"
                    >
                        ×
                    </button>
                </div>

                <div class="logout-dialog-content">

                    <div class="logout-icon">
                        !
                    </div>

                    <div class="logout-message">
                        <strong>Are you sure you want to log off?</strong>

                        <p>
                            You will need to log on again to access your todos.
                        </p>
                    </div>

                </div>

                <div class="logout-dialog-actions">

                    <button
                        type="button"
                        class="xp-button primary-button"
                        onclick="confirmLogout()"
                    >
                        Yes
                    </button>

                    <button
                        type="button"
                        class="xp-button"
                        onclick="closeLogoutModal()"
                    >
                        No
                    </button>

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

</body>

</html>