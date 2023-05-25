<%@ page pageEncoding="UTF-8" %>

<!--
    Página JSP para la confirmación de inicio de sesión correcto.
    Muestra un mensaje de confirmación y un botón de aceptar para redirigir al usuario a la página de perfil.
-->

<html>
<head>
    <title>Correct login</title>
    <style>
        body {
            /* Estilos para el fondo y la fuente */
            background-image: url("images/profilebk.jpg");
            background-size: cover;
            font-family: Arial, sans-serif;
            background-repeat: no-repeat;
        }

        .container {
            /* Estilos para el contenedor principal */
            width: 300px;
            margin: 100px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h2 {
            /* Estilos para el encabezado */
            text-align: center;
        }

        .button {
            /* Estilos para el botón */
            display: inline-block;
            width: 120px;
            padding: 10px;
            margin-top: 20px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: box-shadow 0.3s;
            text-decoration: none;
        }

        .button:hover {
            /* Estilos para el efecto hover del botón */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Logged in correctly!</h2>
        <a class="button" href="/WiseUp/profile-page/index.html">Accept</a>
    </div>
</body>
</html>
