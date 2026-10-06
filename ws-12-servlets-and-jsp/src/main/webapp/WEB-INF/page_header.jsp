<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<html>
    <head>
        <script>
            function startTime() {
              var today = new Date();
              var h = today.getHours();
              var m = today.getMinutes();
              var s = today.getSeconds();
              m = checkTime(m);
              s = checkTime(s);
              document.getElementById('current').innerHTML =
                h + ":" + m + ":" + s;
              var t = setTimeout(startTime, 500);
            }

            function checkTime(i) {
              if (i < 10) {
                i = "0" + i
              }; // add zero in front of numbers < 10
              return i;
            }
        </script>
    </head>
    <body onload="startTime()">
        <h2>This is a header which was included from page_header.jsp</h2>
        <br/>
        <p id="time-description">We achieve this by using javascript functions, that defined in page_header.jsp -> head -> script</p>
        <p id="current">Time loading..</p>
        <br/>
    </body>
</html>
