<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculadora JSP</title>
</head>
<body>

<h1>Calculadora</h1>
<form action="calculadora.jsp" method="post">
    Numero 1: <input type="text" name="num1"><br><br>
    Numero 2: <input type="text" name="num2"><br><br>
    Operación: 
    <select name="operacion">
        <option value="suma">SUMA</option>
        <option value="resta">RESTA</option>
        <option value="producto">PRODUCTO</option>
        <option value="division">DIVISION</option>
    </select>
    <br><br>
    <input type="submit" value="Calcular">
</form>

<%
    // Procesamiento del formulario
    String num1Str = request.getParameter("num1");
    String num2Str = request.getParameter("num2");
    String operacion = request.getParameter("operacion");

    if(num1Str != null && num2Str != null && operacion != null) {
        try {
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);
            int resultado = 0;
            
            switch(operacion) {
                case "suma":
                    resultado = num1 + num2;
                    break;
                case "resta":
                    resultado = num1 - num2;
                    break;
                case "producto":
                    resultado = num1 * num2;
                    break;
                case "division":
                    if(num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        out.println("No se puede dividir entre cero.");
                        return;
                    }
                    break;
            }
            out.println("Resultado: " + resultado);
        } catch(NumberFormatException e) {
            out.println("Por favor, ingresa números válidos.");
        }
    }
%>

</body>
</html>
