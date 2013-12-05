<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <script type="text/javascript" src="scripts.js"></script>
        <title>Login - Pizzeria</title>
    </head>
    <body>
        <div id="wrapper">
            <header>
                <h1 class="titolo">Login</h1>
            </header>
            <hr class="separatore" />
            <article>
                <div class="divMain" id="divMain">
                    <form id="frmLogin" action="Controller" method="POST">
                        <input type="hidden" id="action" name="action" value="login" />
                        <table class="tblReg" >
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" id="txtUsername" name="txtUsername" required/></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" id="txtPassword" name="txtPassword" required/></td>
                            </tr>
                        </table>
                        <input id="btnOk" name="btnOk" type="submit" value="Conferma" />
                    </form>
                </div>
            </article>
        </div>
    </body>
</html>
