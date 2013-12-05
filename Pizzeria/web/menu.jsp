<%-- 
    Document   : menu
    Created on : 5-dic-2013, 12.31.17
    Author     : Francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="utente" scope="session" class="myBeans.UtentiBean"></jsp:useBean>
<jsp:setProperty name="utente" property="username" value='<%=session.getAttribute("username")%>'></jsp:setProperty>
<jsp:setProperty name="utente" property="ruolo" value='<%=session.getAttribute("ruolo")%>'></jsp:setProperty>
<link rel="stylesheet" href="style.css" />
<jsp:getProperty name="utente" property="menu"></jsp:getProperty>
