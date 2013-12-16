<%-- 
    Document   : modificaPizze
    Created on : 16-dic-2013, 10.25.17
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
        <title>Modifica elenco pizze</title>
    </head>
    <body>
        <header>
            <h1 class="titolo">Modifica l'elenco delle pizze</h1>
        </header>
        <jsp:include page="/menu.jsp"></jsp:include>
            <div class="divMain" id="divMain">
                <h2 class="sottotitolo">Esegui una modifica</h2>
            </div>
            <div class="divMain" id="divMain">
                <h3 class="error"><%=request.getAttribute("messaggio")%></h3>
            </div>
            <hr>
            <div class='divMain' id='insersci'>
                <div class="divMain">
                    <h3 class="sottotitolo">Inserisci</h3>
                </div>
                <form nome='insert' id='insert' action='Controller' method='post'>
                    <input type="hidden" id="action" name="action" value="insert"/>
                    <table class="tblBordi">
                        <tr>
                            <th>Nome</th>
                            <th>Ingredieti</th>
                            <th>Prezzo</th>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" id="txtNome" name="txtNome">
                            </td>
                            <td>
                                <input type="text" id="txtIngr" name="txtIngr">
                            </td>
                            <td>
                                <input type="text" id="txtPrezzo" name="txtPrezzo">
                            </td> 
                        </tr>
                    </table>
                    <table class="tblReg">
                    <tr>
                        <td> <input type="button" id="btnInserisci" name="btnInserisci" value='Inserisci'> </td>
                    </tr>
                </table>
                </form>
            </div>
            <hr>
            <div class='divMain' id='modifica'>
                <div class="divMain">
                    <h3 class="sottotitolo">Modifica</h3>
                </div>
                <form nome='update' id='update' action='Controller' method='post'>
                    <input type="hidden" id="action" name="action" value="update"/>
                    <table class="tblBordi">
                        <tr>
                            <th>Pizza</th>
                            <th>Ingredieti</th>
                            <th>Prezzo</th>
                            <th>Conferma</th>
                        </tr>
                        <tr>
                            <td>
                                <select id="cmbPizza" name="cmbPizza" selected="Scegli la pizza">
                                    <jsp:getProperty name="catalogoPizze" property="listaPizze"></jsp:getProperty>
                                </select>
                            </td>
                            <td>
                                <input type="text" id="txtIngrU" name="txtIngrU">
                            </td>
                            <td>
                                <input type="text" id="txtPrezzoU" name="txtPrezzoU">
                            </td>
                            <td>
                                <input type="button" id="btnModifica" name="btnModifica" value='Modifica'>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <hr>
            <div class='divMain' id='cancella'>
                <div class="divMain">
                    <h3 class="sottotitolo">Cancella</h3>
                </div>
                <form nome='delete' id='delete' action='Controller' method='post'>
                    <input type="hidden" id="action" name="action" value="delete"/>
                    <table class="tblBordi">
                        <tr>
                            <th>Pizza</th>
                            <th>Conferma</th>
                        </tr>
                        <tr>
                            <td>
                                <select id="cmbPizzaD" name="cmbPizzaD" selected="Scegli la pizza">
                                    <jsp:getProperty name="catalogoPizze" property="listaPizze"></jsp:getProperty>
                                </select>
                            </td>
                            <td>
                                <input type="button" id="btnCancella" name="btnCancella" value='Cancella'>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </article>
        <aside>
            <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
    </body>
</html>
