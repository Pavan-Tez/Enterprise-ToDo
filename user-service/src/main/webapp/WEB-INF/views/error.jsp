
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Error</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo — Error</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <h1 class="xp-heading">
                Something went wrong
            </h1>

            <p class="notice error">
                ${message}
            </p>

            <p>
                Status: ${status}
            </p>

            <a
                class="xp-button"
                href="${pageContext.request.contextPath}/users/login"
            >
                Back to Log On
            </a>

        </section>

        <footer class="xp-statusbar">
            Error
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
