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
            <form id="dati" name="dati" action="Controller" method="post">
                <input type="hidden" id="action" name="action" value="add"/>
                <table class="tblBordi">
                    <tr>
                        <td>Pizza:</td>
                        <td> <select id="cmbPizza" name="cmbPizza">
                                <jsp:getProperty name="catalogoPizze" property="listaPizze"></jsp:getProperty>
                        </select></td>
                    </tr>
                    <tr>
                        <td>Quantità:</td>
                        <td> <input type="number" id="txtQuantita" name="txtQuantita"></td>
                    </tr>
                    <tr>
                        <td>Numero pizze ordinate:</td>
                        <td> <input type="text" id="txtNumero" readonly/></td>
                    </tr>
                </table>
             <input type="button" id="aggiungi" onclick="aggiungiPrenotazione();" value="Aggiungi" class="buttonSX"/>
        </form>
        <div class="buttonDX">
            <form id="aggiungi" name="aggiungi" action="Controller" method="post">
                <input type="button" id="conferma" onclick="confPrenotazione();" value="Prenota" class="buttonSX"/>
                <input type="hidden" id="action" name="action" value="validate"/>
            </form>
        </div>
        <table class="tblBordi">
            <tr>
                <td>Pizza scelta</td>
                <td>Numero di pizze</td>
            </tr>
            <%=request.getAttribute("tabella")%>
            <%request.setAttribute("tabella",request.getAttribute("tabella"));%>
        </table>
        </article> 
        <aside>
                <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
    </body>
</html>
