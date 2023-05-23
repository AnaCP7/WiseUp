<%@ page pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Account</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">

    <style>
        .main {
            padding: 50px 0;
        }

        .signup {
            margin-bottom: 0;
        }
    </style>
</head>
<body>

<div class="main">

    <!-- Create Account form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Create Account</h2>
                    <form action="/WiseUp/create-account-servlet" method="POST" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="user" id="name" placeholder="Your Name"/>
                        </div>

                        <div class="form-group">
                            <label for="pass1"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="pass1" id="pass1" placeholder="Password"/>
                        </div>
                        <div class="form-group">
                            <label for="pass2"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="password" name="pass2" id="pass2" placeholder="Repeat your password"/>
                        </div>

                        <% if (session.getAttribute("error") != null) {%>
                            <p style="color: red"><%=session.getAttribute("error")%></p>
                            <% session.removeAttribute("error");
                        }%>

                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="images/loginImage.jpg" alt="sing up image"></figure>
                    <a href="logIn.jsp" class="signup-image-link">I am already a member</a>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
