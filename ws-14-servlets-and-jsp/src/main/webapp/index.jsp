<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>JSTL Demo</title>
</head>
<body>
<h1>Authorization filter Example</h1>

<p>1. ${requestScope.name1} </p>
<p>2. ${requestScope.greeting} </p>
<p>3. ${requestScope.param.name1} </p>

<p>1. ${requestScope.name2} </p>
<p>2. ${requestScope.greeting} </p>
<p>3. ${requestScope.param.name2} </p>

<form method="get" action="commandHandler">
	<input type="hidden" name="command" value="getGreeting" />
	<label for="name1">Enter your name:</label>
	<input id="name1" type="text" name="name1" value="MARK"/>
	<button type="submit">Submit</button>
</form>

<form method="post" action="commandHandler">
	<input type="hidden" name="command" value="postGreeting" />
	<label for="name2">Enter your name:</label>
	<input id="name2" type="text" name="name2" />
	<button type="submit">Submit</button>
</form>

</body>
</html>