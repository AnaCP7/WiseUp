<%@ page pageEncoding="UTF-8" %>
<%@ page import="edu.wiseup.persistence.dao.Score" %>
<%@ page import="edu.wiseup.persistence.dao.UserDAO" %>
<%@ page import="java.util.List" %>

<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-image: url("images/quizbk.jpg");
                background-size: cover;
                background-repeat: no-repeat;
            }

            h2 {
                text-align: center;
            }

            table {
                margin: 50px auto;
                width: 600px;
                background-color: #ffffff;
                border-radius: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            }

            th, td {
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #007bff;
                color: #ffffff;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
        </style>
    </head>
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