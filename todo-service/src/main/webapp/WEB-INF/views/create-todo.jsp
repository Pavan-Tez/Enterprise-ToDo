<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Todo</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/xp.css">
</head>
<body class="xp-desktop xp-page">
<main class="xp-window app-window">
    <div class="xp-titlebar">
        <span>Enterprise Todo</span>
        <span class="title-right">New Todo</span>
    </div>
    <section class="xp-content">
        <h1 class="xp-heading">Create a new todo</h1>
        <p class="xp-subtitle">Add a task to your list.</p>

        <form class="xp-form" action="${pageContext.request.contextPath}/todos/create" method="post">
            <div class="form-row">
                <label for="title">Title</label>
                <input id="title" type="text" name="title" placeholder="What needs to be done?" required>
            </div>
            <div class="form-row">
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="Add some details (optional)"></textarea>
            </div>
            <div class="form-actions">
                <button class="xp-button primary-button" type="submit">Create Todo</button>
                <a class="text-link" href="${pageContext.request.contextPath}/todos">Back to Todos</a>
            </div>
        </form>
    </section>
    <footer class="xp-statusbar">New todo</footer>
</main>
</body>
</html>
