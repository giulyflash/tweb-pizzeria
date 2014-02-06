<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean  id="catalogoPizze" scope="request" class="myBeans.PizzeBean"></jsp:useBean>
<jsp:setProperty name="catalogoPizze" property="pizze" value='<%=request.getAttribute("pizze")%>'></jsp:setProperty>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="extra/style.css" />
        <script type="text/javascript" src="extra/scripts.js"></script>
        <title>Homepage - Pizzeria</title>
    </head>
    <body>
        <header>
            <h1 class="titolo">Homepage</h1>            
        </header>
        <jsp:include page="/menu.jsp"></jsp:include>
            <div class="divMain" id="divMain">
                <h2 class="sottotitolo">
                <%=request.getAttribute("messaggio")%>
                </h2>
            <hr>
            <h3 class="intestazione">
                Elenco delle nostre pizze
            </h3>
                <jsp:getProperty name="catalogoPizze" property="tabellaPizze"></jsp:getProperty>
            </div>                
        </article> 
        <aside>
                <jsp:include page="/frmLogin.jsp"></jsp:include>
        </aside>
        <footer>
            <jsp:include page="/createFooter.jsp"></jsp:include>
        </footer>
    </body>
</html>
