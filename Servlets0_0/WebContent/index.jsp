<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Hola Mundo!!</title>
</head>
<body>
<%
if (request.getParameter("NombreUsuario") != null) {
%>
<h1>Hola <%=request.getParameter("NombreUsuario")%>!</h1>
<br>
<%
}
%>
<h1>Bienvenido a mi primera p�gina web!</h1>

<br>

<%--
 Integer contador = (Integer)application.getAttribute("contador");
 if ( contador == null ){
 contador = new Integer(0);
 }
 application.setAttribute("contador",new Integer(contador.intValue()+1));
--%>

<jsp:useBean id="contador" class="com.tew.beans.Counter"
 scope="application"/>
<jsp:getProperty property="incrementedValue" name="contador"/> visitas


</body>
</html>
