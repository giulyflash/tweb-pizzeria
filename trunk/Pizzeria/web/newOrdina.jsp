<%-- 
    Document   : newOrdina
    Created on : 12-dic-2013, 9.37.20
    Author     : Francesco
--%>

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
        <title>New Ordina</title>
    </head>
    <body>
        <header>
            <h1 class="titolo">Ordina</h1>
        </header>
        <jsp:include page="/menu.jsp"></jsp:include>
            <div class="divMain" id="divMain">
                <h2 class="sottotitolo">Fai il tuo ordine</h2>
            </div>
            <table class="tblBordi">
                <tr>
                    <th>Nome pizza</th>
                    <th>Quantita</th>
                </tr>
                <tr>
                <form id="dati" name="dati" action="Controller" method="post">
                    <input type="hidden" id="action" name="action" value="add"/>
                    <td> <select id="cmbPizza" name="cmbPizza">
                                <jsp:getProperty name="catalogoPizze" property="listaPizze"></jsp:getProperty>
                    </select></td>
                    <td> <input type="number" id="txtQuantita" name="txtQuantita"></td>
                </tr>
                <table class="tblReg">
                    <tr>
                        <td> <input type="button" id="aggiungi" onclick="aggiungiPrenotazione();" value="Aggiungi"/> </td>
                        <td> <input type="button" id="conferma" onlclick="confPrenotazione();" value="Conferma prenotazione" /> </td>
                    </tr>
                </table>
                </form>
            </table>
            <hr>
            <form id="pizze" name="pizze" action="Controller" method="post">
            <div class="divMain" id="divMain">
                    <h2 class="sottotitolo">Pizze ordinate</h2>
                        <table class="tblBordi" id="table">
                            <tr>
                                <th>Pizza</th>
                                <th>Quantita</th>
                                <th>Conferma</th>
                            </tr>
                        </table>
                </div>
                <div class="divMain" id="divMain">
                    <input type="hidden" id="action" name="action" value="validate"/>
                    <input type="button" id="prenota" onclick="confPrenotazione();" value="Prenota"/>
                </div>
            </form>
        </article>
        <aside>
            <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
    </body>
</html>
