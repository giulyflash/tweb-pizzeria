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
            <article class="articleCenter">
                <h3 class="intestazione">
                    Inserisci i tuoi dati
                </h3>
                <form id="frmReg" action="Registrazione" method="POST">                    
                    <div class="divMain" id="divMain" >
                        <%=request.getAttribute("error")%>
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
                            <tr>
                                <td class="tdLabel">Ruolo:</td>
                                <td class="tdInput">
                                    <select class="inputSelect" id="cmbRuolo" name="cmbRuolo">
                                        <option id="optCliente" value="Cliente">Cliente</option>
                                        <option id="optAmministratore" value="Amministratore">Amministratore</option>
                                    </select>                        
                                </td>
                            </tr>
                        </table>
                        <input type="button" id="btnOk" value="Conferma" onclick="validaReg();"/>
                </form>
            </article>
        </div>
        <footer>
            <jsp:include page="/createFooter.jsp"></jsp:include>
        </footer>
    </body>
</html>
