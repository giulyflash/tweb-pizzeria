<%-- 
    Document   : createFooter
    Created on : 16-dic-2013, 17.04.22
    Author     : Francesco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="utente" scope="session" class="myBeans.UtentiBean"></jsp:useBean>
<!DOCTYPE html>
<jsp:getProperty name="utente" property="footer"></jsp:getProperty>
