<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.Instant" %>

<html>
<head>
  <style>
    /* Estilos CSS para la apariencia de la página */
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-image: url("images/quizbk.jpg");
      background-size: cover;
      background-repeat: no-repeat;
    }
    .question-container {
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
      padding: 20px;
      margin-bottom: 30px;
      transition: border-color 0.3s ease-in-out;
      max-width: 600px;
      margin-left: auto;
      margin-right: auto;
    }
    .question-container:hover {
      border-color: purple;
    }
    .question {
      color: purple;
      font-weight: bold;
      margin-bottom: 10px;
    }
    .options {
      margin-left: 20px;
      margin-bottom: 10px;
    }
    .option-label {
      display: block;
      color: black;
      margin-bottom: 5px;
    }
    .question-number {
      color: green;
    }
    .done-button {
      font-size: 18px;
      background-color: #4287f5;
      color: #ffffff;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
    }
    .done-button:hover {
      transform: scale(1.05);
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
  </style>
</head>
<body>
<!-- Formulario para realizar el cuestionario -->
<% Instant start = Instant.now(); %>
<h2>WiseUp Quiz</h2>
<form action="/WiseUp/quiz-done-servlet" method="POST">
<%
ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
Question question = new Question();
for (int i = 0; i < 5; i++) {
    question = questions.get(i);
    %>
    <!-- Contenedor de la pregunta -->
    <div class="question-container">
      <div class="question">
        <span class="question-number">Question <%=i+1%>/5:</span> <br>
        <%=question.getQuestion()%>
      </div>
      <div class="options">
        <!-- Opciones de respuesta -->
        <label class="option-label"><input type="radio" name="question-<%=i+1%>" value="option-a"/> <%=question.getOptionA()%></label>
        <label class="option-label"><input type="radio" name="question-<%=i+1%>" value="option-b"/> <%=question.getOptionB()%></label>
        <label class="option-label"><input type="radio" name="question-<%=i+1%>" value="option-c"/> <%=question.getOptionC()%></label>
        <label class="option-label"><input type="radio" name="question-<%=i+1%>" value="option-d"/> <%=question.getOptionD()%></label>
      </div>
    </div>
<% } %>
    <input type="hidden" name="start" value="<%=start%>"/>
    <!-- Botón para enviar el cuestionario -->
    <button class="done-button" type="submit">Done!</button>
</form>
</body>
</html>
