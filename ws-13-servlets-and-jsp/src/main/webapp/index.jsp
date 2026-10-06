<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.time.LocalDateTime"%>

<%!
public void printerTmp(String s) {
    System.out.println("printerTmp: " + s);
}
%>

<html>

<%@include file="WEB-INF/page_header.jsp"%>
<body>
<h2>Hello World! tralala</h2>
<p>${sessionScope.greeting}</p>
<br/>

<%
final LocalDateTime now = LocalDateTime.now();
out.println(now);
printerTmp(now.toString());
%>
<br/>
<p>Message from server: ${requestScope.msg}</p>
<br/>
<p>Message from server: ${requestScope.result}</p>
<br/>
<p>From session: ${sessionScope.loggedUser}</p>

<!--
<p>Message from server: <%=request.getAttribute("result")%></p>
<p>Message from server: <%=(request.getAttribute("result") == null) ? "" : request.getAttribute("result")%></p>
-->
<br/>


<p>POST | register new user</p>
<form method="post" action="fc">
    <input type="hidden" name="command" value="reg"/>
    <input type="text" name="login" value="log123"/>
    <input type="text" name="password" value="psw123"/>
    <input type="text" name="email" value="email123@email.com"/>
    <button type="submit">Submit</button>
</form>

<p>POST | login</p>
<form method="post" action="fc">
    <input type="hidden" name="command" value="log"/>
    <input type="text" name="login" value="log123"/>
    <input type="text" name="password" value="psw123"/>
    <button type="submit">Submit</button>
</form>

<hr/>

<p>GET</p>
<form method="get" action="fc">
<!--operation=sum&command=sum&number-1=0&number-2=0&SubmitButtonType=sum-->
    <p>Please select your operation:</p>
    <input type="radio" checked="checked" id="sum" name="operation" value="sum">
    <label for="sum">SUM</label><br>
    <input type="radio" id="subtract" name="operation" value="subtract">
    <label for="subtract">SUBTRACT</label><br>
    <input type="hidden" name="command" value="sum"/>
    <input type="number" name="number-1" value="0"/>
    <input type="number" name="number-2" value="0"/>
    <button type="submit" name="SubmitButtonType" value="sum">Submit</button>
</form>
<br/>

<p>POST some data</p>
<form method="post" action="fc">
    <input type="hidden" name="command" value="postInputParameter"/>
    <input type="text" name="message" value="..default value.."/>
    <button type="submit">Submit</button>
</form>

</body>
</html>
