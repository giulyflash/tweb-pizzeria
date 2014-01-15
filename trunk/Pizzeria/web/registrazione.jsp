<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <script type="text/javascript" src="scripts.js" ></script>
        <title>Registrazione - Pizzeria</title>
    </head>
    <body>
        <div id="wrapper" >
            <header>
                <h1 class="titolo">Registrazione</h1>
            </header>
            <nav>
               <h3 class=intestazione> Menu </h3>
                <p>
                <ul>
                <li> <a class=liColored href="Controller?action=home">Home</a> </li>
                </ul>
                </p>
            </nav>
            <article>
                <h3 class="intestazione">
                    Inserisci i tuoi dati
                </h3>
                <form id="frmReg" action="Controller" method="POST">
                    <input type="hidden" name="action" id="action" value="confreg">
                    <div class="divMain" id="divMain">
                        <h3 class="error"><%=request.getAttribute("messaggio")%></h3>
                        <%
                            if(session.getAttribute("user")!=null) {%>
                            Sei già registrato
                        <%}%>
                        <table id="tblReg" class="tlbSelect">
                            <tr>
                                <td class="tdLabel">Username:</td>
                                <td class="tdInput"><input class="inputText" type="text" id="txtUsername" name="txtUsername" /></td>
                            </tr>
                            <tr>
                                <td class="tdLabel">Password:</td>
                                <td class="tdInput"><input class="inputText" type="password" id="txtPassword" name="txtPassword" /></td>
                            </tr>
                        </table>
                        <table class="tblReg">
                            <tr>
                                <td><input type="button" id="btnOk" value="Conferma" onclick="validaReg();"/></td>
                            </tr>                        
                        </table>
                </form>
            </article>
        </div>
        <footer>
            <jsp:include page="/createFooter.jsp"></jsp:include>
        </footer>
    </body>
</html>
