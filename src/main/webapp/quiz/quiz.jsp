<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Question" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <script>
        var n = 0;
        var l = document.getElementById("number");
        window.setInterval(function(){
          l.innerHTML = n;
          n++;
        },1000);
    </script>
</head>

<body>
Contador:
<div id="number" style="font-size:50px; color:#434343;"></div>
Como es un JSP no puede tener elementos que se actualicen :(
<br>

<form action="/WiseUp/quiz-done-servlet" method="POST">
<%
ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
Question question = new Question();
for (int i = 0; i < 5; i++) {
    question = questions.get(i);
    %>
    Question <%=i+1%>/5: <br>
    <%=question.getQuestion()%> <br>
    <%=question.getOptionA()%> <input type="radio" name="question-<%=i+1%>" value="option-a"/> <br>
    <%=question.getOptionB()%> <input type="radio" name="question-<%=i+1%>" value="option-b"/> <br>
    <%=question.getOptionC()%> <input type="radio" name="question-<%=i+1%>" value="option-c"/> <br>
    <%=question.getOptionD()%> <input type="radio" name="question-<%=i+1%>" value="option-d"/> <br>
    <br>
<% } %>
<!-- session.setAttribute("questions", questions); -->
<button type="submit">Done!</button>
</form>
</body>
</html>