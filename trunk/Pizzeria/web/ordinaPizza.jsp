<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean  id="catalogoPizze" scope="request" class="myBeans.PizzeBean"></jsp:useBean>
<jsp:setProperty name="catalogoPizze" property="lista" value='<%=request.getAttribute("lista")%>'></jsp:setProperty>
<jsp:useBean  id="utenti" scope="request" class="myBeans.UtentiBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <script type="text/javascript" src="scripts.js"></script>
        <title>Ordine</title>
    </head>
    <body>
        <header>
            <h1 class="titolo">Ordina</h1>
        </header>
        <jsp:include page="/menu.jsp"></jsp:include>
            <div class="divMain" id="divMain">
                <h2 class="sottotitolo">Fai il tuo ordine</h2>
            </div>
            <form id="dati" action="Controller" method="post">
            <input type="hidden" id="action" name="action" value="validate"/>
                <table class="tblBordi">
                    <tr>
                        <td>Pizza:</td>
                        <td> <select id="cmbPizza">
                                <jsp:getProperty name="catalogoPizze" property="listaPizze"></jsp:getProperty>
                        </select></td>
                    </tr>
                    <tr>
                        <td>Quantità:</td>
                        <td> <input type="number" id="txtQuantita"></td>
                    </tr>
                </table>
                <table class="tblReg">
                    <tr>
                        <td> <input type="button" id="aggiungi" onclick="aggiungiPrenotazione();" value="Aggiungi"/> </td>
                        <td> <input type="button" id="conferma" onclick="confPrenotazione();" value="Prenota"/> </td>
                    </tr>
                </table>
            <hr>
            <textarea id="txtPrenota" readonly class="textArea"></textarea>
            </form>
        </article> 
        <aside>
                <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
    </body>
</html>
