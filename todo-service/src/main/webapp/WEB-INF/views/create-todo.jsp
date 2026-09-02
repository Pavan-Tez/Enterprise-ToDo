
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Create Todo</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo — Create Todo</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <h1 class="xp-heading">
                Create Todo
            </h1>

            <p class="xp-subtitle">
                Add a task to your list.
            </p>

            <form
                class="xp-form"
                action="${pageContext.request.contextPath}/todos/create"
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
                    ></textarea>

                </div>

                <div class="form-actions">

                    <button
                        class="xp-button primary-button"
                        type="submit"
                    >
                        Create Todo
                    </button>

                    <a
                        class="text-link"
                        href="${pageContext.request.contextPath}/todos"
                    >
                        Back to Todos
                    </a>

                </div>

            </form>

        </section>

        <footer class="xp-statusbar">
            New todo
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
