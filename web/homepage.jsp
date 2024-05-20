<%-- 
    Document   : homepage
    Created on : Jan 14, 2024, 2:59:21 AM
    Author     : tudo7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><div><b>${mess}</b></div></h1>
        <h1><div><b>${user.name}</b></div></h1>
        <h1><div><b>${user.id}</b></div></h1>
        <h1><div><b>${user.email}</b></div></h1>
        <h1><div><b>${user.mobile}</b></div></h1>
        <h1><div><b>${user.avatar}</b></div></h1>
        <a href="quiz-handle?quizId=1">take a quiz</a>
        <a href="quiz-history">history</a>
        <a href="quiz-detail?quizId=1">edit quiz</a>
        <a href="question-import">question import</a>
        
        
    </body>
</html>
