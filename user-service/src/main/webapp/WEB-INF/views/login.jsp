<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Log On</title>

    <link
        rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/xp.css"
    >
</head>

<body class="xp-desktop xp-page">

    <main class="xp-window app-window">

        <div class="xp-titlebar">
            <span>Enterprise Todo - Log On</span>

            <span class="window-controls">
                <b>_</b>
                <b>□</b>
                <b>×</b>
            </span>
        </div>

        <section class="xp-content">

            <h1 class="xp-heading">
                Log on to Enterprise Todo
            </h1>

            <p class="xp-subtitle">
                Enter your account details to continue.
            </p>

            <!-- Login Error -->
            <c:if test="${not empty param.error}">
                <p class="notice error">
                    <c:out value="${param.error}" />
                </p>
            </c:if>

            <!-- Account Created Notification -->
            <c:if test="${param.notification == 'account-created'}">

                <p
                    id="account-created-notification"
                    class="notice success"
                >
                    User created successfully.
                    Your user ID is
                    <c:out value="${param.userId}" />.
                </p>

                <script>
                    window.alert(
                        document.getElementById(
                            'account-created-notification'
                        ).textContent
                    );
                </script>

            </c:if>

            <!-- Logged Out Notification -->
            <c:if test="${param.notification == 'logged-out'}">

                <p
                    id="logged-out-notification"
                    class="notice success"
                >
                    You have been logged out.
                </p>

                <!-- <script>
                    window.alert(
                        document.getElementById(
                            'logged-out-notification'
                        ).textContent
                    );
                </script> -->

            </c:if>

            <!-- Login Form -->
            <form
                class="xp-form"
                method="post"
                action="${pageContext.request.contextPath}/users/login"
            >

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
                        Log On
                    </button>

                    <a
                        class="xp-button"
                        href="${pageContext.request.contextPath}/"
                    >
                        Home
                    </a>

                    <a
                        class="text-link"
                        href="${pageContext.request.contextPath}/users/register"
                    >
                        Create a new account
                    </a>


                </div>

            </form>

        </section>

        <footer class="xp-statusbar">
            Please enter your username and password.
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
