<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="utente" scope="session" class="myBeans.UtentiBean"></jsp:useBean>
<jsp:setProperty name="utente" property="username" value='<%=session.getAttribute("username")%>'></jsp:setProperty>
<jsp:setProperty name="utente" property="ruolo" value='<%=session.getAttribute("ruolo")%>'></jsp:setProperty>
<link rel="stylesheet" href="extra/style.css" />
            <div id="login">
                <jsp:getProperty name="utente" property="userInterface"></jsp:getProperty>
            </div>
