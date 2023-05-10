<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Question" %>
<%@ page import="java.util.ArrayList" %>

<html>
<body>

<%
ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
Question question = new Question();
for (int i = 0; i < 5; i++) {
    question = questions.get(i);%>
    Question number <%=i+1%> <br>
    Question: <%=question.getQuestion()%> <br>
    Answer: <%=question.getAnswer()%> <br>
    <br>
<% } %>

</body>
</html>