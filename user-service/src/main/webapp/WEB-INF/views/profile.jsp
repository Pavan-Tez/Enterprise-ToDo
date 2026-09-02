
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>User Profile</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo — User Profile</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <h1 class="xp-heading">
                You are authenticated
            </h1>

            <p class="xp-subtitle">
                Your account information is shown below.
            </p>

            <dl class="detail-list">

                <div>
                    <dt>User ID</dt>
                    <dd>${user.id}</dd>
                </div>

                <div>
                    <dt>Username</dt>
                    <dd>${user.username}</dd>
                </div>

                <div>
                    <dt>Name</dt>
                    <dd>${user.name}</dd>
                </div>

                <div>
                    <dt>Email</dt>
                    <dd>${user.email}</dd>
                </div>

            </dl>

            <div class="page-actions">

                <a
                    class="xp-button primary-button"
                    href="${pageContext.request.contextPath}/todos"
                >
                    My Todos
                </a>

                <form
                    action="${pageContext.request.contextPath}/users/logout"
                    method="post"
                >
                    <button
                        class="xp-button"
                        type="submit"
                    >
                        Log Off
                    </button>
                </form>

            </div>

        </section>

        <footer class="xp-statusbar">
            Authenticated as ${user.username}
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
