<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Question" %>
<%@ page import="java.util.ArrayList" %>

<html>
<body>

<form action="/WiseUp/quiz-done-servlet" method="POST">
<%
ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
Question question = new Question();
for (int i = 0; i < 5; i++) {
    question = questions.get(i);
    %>
    Question <%=i+1%>/5: <br>
    <%=question.getQuestion()%> <br>
    <%=question.getOptionA()%> <input type="radio" name="question<%=i+1%>" value="optionA"/> <br>
    <%=question.getOptionB()%> <input type="radio" name="question<%=i+1%>" value="optionB"/> <br>
    <%=question.getOptionC()%> <input type="radio" name="question<%=i+1%>" value="optionC"/> <br>
    <%=question.getOptionD()%> <input type="radio" name="question<%=i+1%>" value="optionD"/> <br>
    <br>
<% } %>
<!-- session.setAttribute("questions", questions); -->
<button type="submit">Done!</button>
</form>
</body>
</html>