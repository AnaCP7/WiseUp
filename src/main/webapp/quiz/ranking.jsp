<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Score" %>
<%@ page import="edu.wiseup.persistence.dao.UserDAO" %>
<%@ page import="java.util.List" %>

<html>
    <body>
        <%
        List<Score> scores = (List<Score>) session.getAttribute("scores");
        session.removeAttribute("scores");
        if (scores == null) { %>
            <form action="/WiseUp/ranking-servlet" method="POST">
                <button type="submit">Mostrar ranking</button>
            </form>
        <% }
        else { %>
            <h2>WiseUp Ranking</h2>
            <table>
                <tr>
                    <th>Ranking</th>
                    <th>Score</th>
                    <th>User</th>
                    <th>Date</th>
                </tr>
                <%
                long n = 1;
                for(Score score : scores){%>
                    <tr>
                        <td><%=n++%></td>
                        <td><%=score.getScore()%></td>
                        <td><%=score.getUser().getUsername()%></td>
                        <td><%=score.getDate().toString().substring(0,10)%></td>
                    </tr>
                <%}%>
            </table>
        <%}%>
    </body>
</html>