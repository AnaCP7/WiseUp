<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Quiz Instructions</title>
    <style>
        /* Estilos CSS para la apariencia de la página */
        body {
            font-family: 'Inconsolata', monospace;
            margin: 20px;
            background-image: url("images/startQuizbk.jpg");
            background-size: cover;
            background-repeat: no-repeat;
        }

        /* Estilos para el contenedor principal */
        .container {
            width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h2, h3 {
            margin-bottom: 20px;
        }

        /* Estilos para la lista de instrucciones */
        ul {
            font-size: 1.2em;
            list-style-type: disc;
            padding-left: 20px;
            text-align: left;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        ul li {
            margin-bottom: 10px;
        }

        /* Estilos para el botón de inicio del cuestionario */
        .button {
            display: inline-block;
            padding: 15px 30px;
            margin-top: 20px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: box-shadow 0.3s;
            text-decoration: none;
            font-size: 18px; /* Ajusta el tamaño de fuente según tus preferencias */
        }


        .button:hover {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        /* Estilos para los títulos de categoría */
        .category-women {
            background-color: #E83EF5;
            color: #ffffff;
            display: inline-block;
            padding: 15px;
            border-radius: 25px;
            margin-bottom: 10px;
            font-weight: normal; /* Quita el estilo negrita */
        }

        .category-all {
            background-color: #DD356E;
            color: #ffffff;
            display: inline-block;
            padding: 15px;
            border-radius: 25px;
            margin-bottom: 10px;
            font-weight: normal; /* Quita el estilo negrita */
        }

        .category-art,
        .category-art-history {
            background-color: #fee856;
            color: #000000;
            display: inline-block;
            padding: 15px;
            border-radius: 25px;
            margin-bottom: 10px;
            font-weight: normal; /* Quita el estilo negrita */
        }

         .category-technology,
         .category-literature,
         .category-science {
            background-color: #FFAA80;
            color: #000000;
            display: inline-block;
            padding: 15px;
            border-radius: 25px;
            margin-bottom: 10px;
            font-weight: normal;
        }

    </style>
</head>
<body>
<% String category = request.getParameter("category");
    String categoryTitle;
    String categoryClass;

    if (category == null || category.equals("")) {
        category = "all";
        categoryTitle = "All Questions";
        categoryClass = "category-all";
    }
    if (category.equals("women")) {
        categoryTitle = "Lost Women";
        categoryClass = "category-women";
    } else if (category.equals("art")) {
        categoryTitle = "Art";
        categoryClass = "category-art";
    } else if (category.equals("art_history")) {
        categoryTitle = "Art History";
        categoryClass = "category-art-history";
    } else if (category.equals("technology")) {
        categoryTitle = "Technology";
        categoryClass = "category-technology";
    } else if (category.equals("literature")) {
        categoryTitle = "Literature";
        categoryClass = "category-literature";
    } else if (category.equals("science")) {
        categoryTitle = "Science";
        categoryClass = "category-science";
    } else {
        categoryTitle = "All Questions";
        categoryClass = "category-all";
    }

    session.setAttribute("category", category);%>

    <div class="container">
        <h2>Welcome to the Quiz!</h2>
        <h3 class="<%= categoryClass %>"><%= categoryTitle %></h3>
        <ul>
            <li>Have fun!</li>
            <li>The faster you finish, the higher your score is.</li>
            <li>You can see your score at the end of the quiz.</li>
        </ul>
         <form action="/WiseUp/quiz-servlet" method="POST">
             <!-- Botón para iniciar el cuestionario -->
             <button class="button" type="submit">Start quiz</button>
         </form>
    </div>
</body>
</html>
