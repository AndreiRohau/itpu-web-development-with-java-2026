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
        <span>Time</span>
        <span id="current">Time loading..</span>
        <br/>
    </body>
</html>
