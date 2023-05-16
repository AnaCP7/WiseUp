<%@ page pageEncoding="UTF-8" %>

<html>
<body>

Score: <%=session.getAttribute("score")%>
Good job!

<form action="/WiseUp/submit-score-servlet" method="POST">
<input type="hidden" name="score" value="<%=session.getAttribute("score")%>"/>
<%
session.removeAttribute("score");
session.removeAttribute("questions");
%>
<button type="submit">Submit score in ranking</button>
</form>

</body>
</html>