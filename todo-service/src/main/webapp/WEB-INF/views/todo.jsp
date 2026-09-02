
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Todo</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo — Todo Details</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <h1 class="xp-heading">
                ${todo.title}
            </h1>

            <dl class="detail-list">

                <div>
                    <dt>Description</dt>
                    <dd>${todo.description}</dd>
                </div>

                <div>
                    <dt>Status</dt>
                    <dd>${todo.status}</dd>
                </div>

            </dl>

            <h2 class="xp-heading">
                Update Todo
            </h2>

            <form
                class="xp-form"
                action="${pageContext.request.contextPath}/todos/${todo.id}/update"
                method="post"
            >

                <div class="form-row">

                    <label for="title">
                        Title
                    </label>

                    <input
                        id="title"
                        type="text"
                        name="title"
                        value="${todo.title}"
                        required
                    >

                </div>

                <div class="form-row">

                    <label for="description">
                        Description
                    </label>

                    <textarea
                        id="description"
                        name="description"
                    >${todo.description}</textarea>

                </div>

                <div class="form-row">

                    <label for="status">
                        Status
                    </label>

                    <select id="status" name="status">

                        <option
                            value="TODO"
                            ${todo.status == 'TODO' ? 'selected' : ''}
                        >
                            TODO
                        </option>

                        <option
                            value="IN_PROGRESS"
                            ${todo.status == 'IN_PROGRESS' ? 'selected' : ''}
                        >
                            IN PROGRESS
                        </option>

                        <option
                            value="COMPLETED"
                            ${todo.status == 'COMPLETED' ? 'selected' : ''}
                        >
                            COMPLETED
                        </option>

                    </select>

                </div>

                <div class="form-actions">

                    <button
                        class="xp-button primary-button"
                        type="submit"
                    >
                        Update
                    </button>

                    <a
                        class="text-link"
                        href="${pageContext.request.contextPath}/todos"
                    >
                        Back to Todos
                    </a>

                </div>

            </form>

            <form
                class="page-actions"
                action="${pageContext.request.contextPath}/todos/${todo.id}/delete"
                method="post"
            >

                <button
                    class="xp-button danger-button"
                    type="submit"
                >
                    Delete Todo
                </button>

            </form>

        </section>

        <footer class="xp-statusbar">
            Todo #${todo.id}
        </footer>

    </main>

    <div class="xp-taskbar">

        <span class="start-button">
            <span>⊞</span>
            start
        </span>

    </div>

</body>

</html>

