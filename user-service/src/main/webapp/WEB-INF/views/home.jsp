
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Enterprise Todo</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <p class="eyebrow">
                HOME
            </p>

            <h1 class="xp-heading">
                Welcome to Enterprise Todo
            </h1>

            <p class="xp-subtitle">
                Your workspace is ready.
            </p>

            <div class="page-actions">

                <a
                    class="xp-button primary-button"
                    href="${pageContext.request.contextPath}/users/login"
                >
                    Log On
                </a>

                <a
                    class="text-link"
                    href="${pageContext.request.contextPath}/users/register"
                >
                    Create account
                </a>

            </div>

        </section>

        <footer class="xp-statusbar">
            Ready
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

