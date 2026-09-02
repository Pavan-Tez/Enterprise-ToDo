
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Create Account</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo — Create Account</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <h1 class="xp-heading">
                Create a new account
            </h1>

            <p class="xp-subtitle">
                Set up your Enterprise Todo profile.
            </p>

            <form
                class="xp-form"
                method="post"
                action="${pageContext.request.contextPath}/users/register"
            >

                <div class="form-row">

                    <label for="name">
                        Name
                    </label>

                    <input
                        id="name"
                        type="text"
                        name="name"
                        required
                    >

                </div>

                <div class="form-row">

                    <label for="username">
                        Username
                    </label>

                    <input
                        id="username"
                        type="text"
                        name="username"
                        required
                    >

                </div>

                <div class="form-row">

                    <label for="email">
                        Email
                    </label>

                    <input
                        id="email"
                        type="email"
                        name="email"
                    >

                </div>

                <div class="form-row">

                    <label for="password">
                        Password
                    </label>

                    <input
                        id="password"
                        type="password"
                        name="password"
                        required
                    >

                </div>

                <div class="form-actions">

                    <button
                        class="xp-button primary-button"
                        type="submit"
                    >
                        Create Account
                    </button>

                    <a
                        class="text-link"
                        href="${pageContext.request.contextPath}/users/login"
                    >
                        Already have an account? Log on
                    </a>

                </div>

            </form>

        </section>

        <footer class="xp-statusbar">
            Create your profile.
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
