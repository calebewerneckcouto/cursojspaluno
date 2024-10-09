<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Curso JSP</title>
</head>
<body>

<h1>Bem-vindo ao curso de JSP</h1>

<form action="ServletLogin" method="post">
<input type="hidden" value = "<%= request.getParameter("url") %>" name="url">	
    <table>
        <tr>
            <td>Login</td>    
            <td><input name="login" type="text"></td>
        </tr>
        <tr>
            <td>Senha</td>
            <td><input name="senha" type="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Enviar"></td>
        </tr>
    </table>
</form>
<h4>${msg}</h4>

<script>
function checkForm() {
    const login = document.getElementsByName('login')[0].value;
    const senha = document.getElementsByName('senha')[0].value;
    console.log("Login:", login);
    console.log("Senha:", senha);
    return true; // Permite o envio do formulário
}
</script>

</body>
</html>
