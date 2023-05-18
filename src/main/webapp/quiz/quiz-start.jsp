<html>
<head>
    <title>Quiz Instructions</title>
    <style>
        body {
              font-family: Arial, sans-serif;
              margin: 20px;
              background-image: url("images/startQuizbk.jpg");
              background-size: cover;
              background-repeat: no-repeat;
            }

        .container {
            width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            text-align: center; /* Se agrega la alineación centrada */
        }

        h2 {
            margin-bottom: 20px;
        }

        ul {
            list-style-type: disc;
            padding-left: 20px;
            text-align: left;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
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
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Instructions:</h2>
        <ul>
            <li>Have fun!</li>
            <li>The faster you finish, the higher your score is.</li>
            <li>You can see your score at the end of the quiz.</li>
        </ul>
        <form action="/WiseUp/quiz-servlet" method="POST">
            <button class="button" type="submit">Start quiz</button>
        </form>
    </div>
</body>
</html>
