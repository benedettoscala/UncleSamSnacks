
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="css/login.css">
</head>
<body class="mannaggia">
    <section>
        <div class="imgBox">
            <img src="images/immaginelogin.jpg" alt="bella">
        </div>

        <div class="contentBox">
            <div class="formBox">
                <h2>Accedi</h2>
                <form action="LoginControl" method="Post">
                    <div class="inputBox">
                        <span>Username</span>
                        <input type="text" name = "username">
                    </div>
                    <div class="inputBox">
                        <span>Password</span>
                        <input type="password" name="password">
                    </div>
                    <div class="inputBox">
                        <input type="submit" value="Invia">
                    </div>
                    <div class="inputBox">
                        <p>Non hai un account? <a href="Registrazione.jsp">Registrati</a></p>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>