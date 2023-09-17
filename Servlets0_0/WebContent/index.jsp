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
<h1>Bienvenido a mi primera página web!</h1>
</body>
</html>
