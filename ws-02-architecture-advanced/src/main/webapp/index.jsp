<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.time.LocalDateTime"%>


<html>
    <%@include file="WEB-INF/page_header.jsp"%>
    <body>
        <h2>Hello View!</h2>
        <p>Please enter text:</p>
        <p>GET</p>
        <form method="get" action="FrontController">
            <input type="text" name="MESSAGE"/>
            <button type="submit">Submit</button>
        </form>
        <br/>

        <p>POST</p>
        <form method="post" action="FrontController">
            <input type="text" name="MESSAGE"/>
            <button type="submit">Submit</button>
        </form>
        <br/>
        <p>Message from client: [${message}]</p>
        <p>Translation from server: [${translation}]</p>

    </body>
</html>
