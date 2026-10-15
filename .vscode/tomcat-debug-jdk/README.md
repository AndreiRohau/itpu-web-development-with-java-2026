# Tomcat Java debugging

In the Al-rimi.tomcat extension, add/select this folder as **Java Home**:

`/Users/Andrei_Rohau/repository/itpu/2026/itpu-web-development-with-java-2026/.vscode/tomcat-debug-jdk`

Do not use it for **Tomcat Home**. Tomcat Home must point to your Apache Tomcat
installation (the folder containing `bin/catalina.sh` on macOS/Linux).

The extension uses `bin/java` from that folder. This wrapper delegates to the
machine's Java installation and adds the JDWP agent only when Al-rimi.tomcat
starts Tomcat. The debugger listens on `localhost:5005`.

On macOS it uses `JAVA_HOME` when set, otherwise `/usr/libexec/java_home`. On
other Unix-like systems it uses `JAVA_HOME` or `java` from `PATH`.

Then start the app with Al-rimi.tomcat and choose **Attach to Tomcat (ws-14)**
in VS Code's **Run and Debug** view.
