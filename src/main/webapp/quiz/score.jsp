<%@ page pageEncoding="UTF-8" %>

<html>
<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-image: url("images/scorebk.jpg");
      background-size: cover;
      background-repeat: no-repeat;
      padding: 100px;
    }
    .score-container {
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
      padding: 20px;
      margin: 20px auto;
      max-width: 300px;
      text-align: center;
    }
    .score-title {
      color: purple;
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 20px;
    }
    .score-message {
      color: #000000;
      font-size: 18px;
      margin-bottom: 20px;
    }
    .submit-button {
      font-size: 18px;
      background-color: #4287f5;
      color: #ffffff;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
    }
    .submit-button:hover {
      transform: scale(1.05);
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
  </style>
</head>
<body>

<div class="score-container">
  <div class="score-title">Score: <%=session.getAttribute("score")%></div>
  <div class="score-message">
    <b>Correct answers: <%=session.getAttribute("correctAnswers")%>/5</b> <br>
    <b>Time taken: <%=session.getAttribute("timeTaken")%>s</b>
  </div>
  <div class="score-message">
    <%
    int score = (int) session.getAttribute("score");
    String message = "";
    if (score == 0) {
      message = "Need practice!";
    } else if (score < 100) {
      message = "You can do better!";
    } else if (score < 200) {
      message = "Not bad!";
    } else if (score < 300) {
      message = "Average!";
    } else if (score < 400) {
      message = "Great job!";
    } else if (score < 500) {
      message = "Wow, can't be better!";
    } else if (score == 500) {
      message = "Just perfect.";
    }
    out.println(message);
    %>
  </div>
  <form action="/WiseUp/submit-score-servlet" method="POST">
    <input type="hidden" name="score" value="<%=session.getAttribute("score")%>"/>
    <%
    session.removeAttribute("score");
    session.removeAttribute("questions");
    %>
    <button class="submit-button" type="submit">Submit score to ranking</button>
  </form>
</div>

</body>
</html>