<%-- 
    Document   : storico
    Created on : 17-dic-2013, 15.08.06
    Author     : Francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <script type="text/javascript" src="scripts.js"></script>
        <title>Storico ordini</title>
    </head>
    <body>
        <header>
            <h1 class="titolo">Storico</h1>            
        </header>
        <jsp:include page="/menu.jsp"></jsp:include>
            <div class="divMain" id="divMain">
                <h2 class="sottotitolo"> Tutte le orinazioni:</h2>
            <hr>
                <!--elenco degli ordini-->
        </article>
        <aside>
                <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
        <footer>
            <jsp:include page="/createFooter.jsp"></jsp:include>
        </footer>
    </body>
</html>
