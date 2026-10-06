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

<%
final LocalDateTime now = LocalDateTime.now();
out.println(now);
printerTmp(now.toString());
%>
<br/>

<!-- Talk about scopes in next workshop -->
<p>Message from server: ${requestScope.result}</p>

<p>Message from server: <%=request.getAttribute("result")%></p>

<p>Message from server: <%=(request.getAttribute("result") == null) ? "" : request.getAttribute("result")%></p>

<br/>

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

<p>POST</p>
<form method="post" action="fc">
    <input type="hidden" name="command" value="postInputParameter"/>
    <input type="text" name="message" value="..default value.."/>
    <button type="submit">Submit</button>
</form>

</body>
</html>
