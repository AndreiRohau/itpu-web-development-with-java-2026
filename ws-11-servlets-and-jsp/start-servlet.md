0. **KEEP IN MIND VERSION COMPATIBILITY** - [VERTION COMPATIBILITY TABLE](https://tomcat.apache.org/whichversion.html)
1. download [Tomcat](https://tomcat.apache.org/download-90.cgi) using this [link](https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.85/bin/apache-tomcat-9.0.85.zip)
2. Unarchive downloaded ZIP to C:/dev folder (for example)

![img_02.png](readme-static/img_02.png)

3. maven -> install 

![img_03.png](readme-static/img_03.png)

4. copy [...-SNAPSHOT.war]() to [..\apache-tomcat-*\webapps]()

![img_04.png](readme-static/img_04.png) 
![img_05.png](readme-static/img_05.png)
Find [../conf/tomcat-users.xml]() 
![img.png](readme-static/img.png)
Uncommit **Admin**
![img_1.png](readme-static%2Fimg_1.png)

5. run [..\apache-tomcat-*\bin\startup.bat]() 

![img_06.png](readme-static/img_06.png) 
![img_07.png](readme-static/img_07.png)

6. Notice that WAR is unarchived 

![img_08.png](readme-static/img_08.png)

7. call [http://localhost:8080/*-SNAPSHOT](http://localhost:8080/*-SNAPSHOT)

8. call [http://localhost:8080/*-SNAPSHOT/FrontController](http://localhost:8080/3-servlet-jsp-1.0-SNAPSHOT/FrontController)

9. now try smart-tomcat with next steps

10. go to Settings \ plugins \ Marketplace \ Search for Smart Tomcat plugin and install it

![img_09.png](readme-static/img_09.png) 

11. Click Apply and Ok

12. After that you would probably need to restart IDEA

13. Click on Add Configuration... (top right) 

![img_10.png](readme-static/img_10.png)

14. Add new Smart Tomcat 

![img_11.png](readme-static/img_11.png)

15. Set Tomcat server with your's 

![img_12.png](readme-static/img_12.png)

16. Name my-tom 

![img_13.png](readme-static/img_13.png)

17. Apply and Ok

18. run it 

![img_14.png](readme-static/img_14.png)

19. click on [http://localhost:8080/3-servlet-jsp](http://localhost:8080/3-servlet-jsp)

20. click on [http://localhost:8080/3-servlet-jsp/FrontController](http://localhost:8080/3-servlet-jsp/FrontController)

---
Additional
Try to run and analyze https://github.com/AndreiRohau/MavenServlet/tree/master
---

Issues:
1. /_FrontController_ not opens _home_

- Go to run configuration and add maven install in before lunch section

![img_15.png](readme-static/img_15.png)

- enter "install" 

![img_16.png](readme-static/img_16.png) 

click Ok
- put maven above build 

![img_17.png](readme-static/img_17.png)
