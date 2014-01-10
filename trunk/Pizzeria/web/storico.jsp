<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean  id="catalogoPizze" scope="request" class="myBeans.PizzeBean"></jsp:useBean>
<jsp:setProperty name="catalogoPizze" property="ordini" value='<%=request.getAttribute("ordini")%>'></jsp:setProperty>
<jsp:useBean id="utente" scope="session" class="myBeans.UtentiBean"></jsp:useBean>
<jsp:setProperty name="utente" property="username" value='<%=session.getAttribute("username")%>'></jsp:setProperty>

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
                <h2 class="intestazione"> Tutte le ordinazioni:</h2>
                
            <hr>
                <jsp:getProperty name="catalogoPizze" property="tabellaOrdiniCompleta"></jsp:getProperty>
        </article>
        <aside>
                <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
        <footer>
            <jsp:include page="/createFooter.jsp"></jsp:include>
        </footer>
    </body>
</html>
