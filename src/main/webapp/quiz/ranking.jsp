<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Score" %>
<%@ page import="edu.wiseup.persistence.dao.UserDAO" %>
<%@ page import="java.util.List" %>

<html>
    <body>
        <h2>No está terminado!!!</h2>
        <form action="/WiseUp/ranking-servlet" method="GET">
                <p>ID Score: </p> <input type="text" name="id"/></br>
                <button type="submit">Buscar</button>
                <button type="reset">Cancelar</button>
        </form>
        <%
        Score scoreSelected = (Score) session.getAttribute("score");
        List<Score> scores = (List<Score>)session.getAttribute("scores");
        session.removeAttribute("score");
        session.removeAttribute("scores");
        %>
        <%
        if(scoreSelected != null){%>
            <h2>Score</h2>
            <table>
            <tr>
                <th>id</th>
                <th>user</th>
                <th>score</th>
                <th>date</th>
            </tr>
            <tr>
                <td><%=scoreSelected.getId()%></td>
                <td><%=scoreSelected.getUser()%></td>
                <td><%=scoreSelected.getScore()%></td>
                <td><%=scoreSelected.getDate()%></td>
            </tr>
            </table>

            <h2>Ranking</h2>
            <table>
            <tr>
                <th>id</th>
                <th>user</th>
                <th>score</th>
                <th>date</th>
            </tr>
            <%for(Score score : scores){%>
                <tr>
                    <td><%=score.getId()%></td>
                    <td><%=score.getUser()%></td>
                    <td><%=score.getScore()%></td>
                    <td><%=score.getDate()%></td>
                </tr>
            <%}%>
            </table>
        <%}%>
    </body>
</html>