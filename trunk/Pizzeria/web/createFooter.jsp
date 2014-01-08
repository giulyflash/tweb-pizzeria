<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="utente" scope="session" class="myBeans.UtentiBean"></jsp:useBean>
<!DOCTYPE html>
<jsp:getProperty name="utente" property="footer"></jsp:getProperty>
