# Start the servlet app on macOS

This guide covers two ways to run `ws-11-servlets-and-jsp` on macOS with VS Code: deploy the WAR manually, or use the [Tomcat AI Deployer extension by Al-rimi](https://marketplace.visualstudio.com/items?itemName=Al-rimi.tomcat).

## Compatibility

The project sets Java source and target to 21 and depends on `jakarta.servlet-api` 6.0.0. Use Java 21 and Tomcat 10.1.x: Tomcat 10.1 implements Jakarta Servlet 6.0. Check the [Tomcat version compatibility table](https://tomcat.apache.org/whichversion.html) and [Tomcat 10.1 requirements](https://tomcat.apache.org/migration-10.1.html) when choosing versions.

## 1. Select Java, Maven, and Tomcat with SDKMAN

The versions currently used for this project are Java `21.0.12-amzn`, Maven `3.9.16`, and Tomcat `10.1.55`. In Terminal, select them for the current shell:

```bash
sdk use java 21.0.12-amzn
sdk use maven 3.9.16
sdk use tomcat 10.1.55
java -version
mvn -v
```

`sdk current` shows the active versions. `sdk use` changes versions only in the current Terminal session. To choose different versions, check the available candidates with `sdk list java`, `sdk list maven`, or `sdk list tomcat`, then use `sdk use <candidate> <version>`. To change a global default, use `sdk default <candidate> <version>`.

For project-specific selection, run `sdk env init` from this project's directory. The generated `.sdkmanrc` records the versions active at that time; run `sdk env` when you enter the project to switch to them. Only commit `.sdkmanrc` if those exact candidate identifiers are suitable for the other machines using the repository.

## Option 1: Build and deploy the WAR manually

Tomcat's servlet engine is named Catalina. `CATALINA_HOME` is a conventional variable for Tomcat's installation folder, but you do not need to set it. The commands below use the folder path directly.

```bash
sdk home tomcat 10.1.55
```

This prints Tomcat's folder. With the standard SDKMAN setup, it is `$HOME/.sdkman/candidates/tomcat/10.1.55`. In VS Code, open **Terminal → New Terminal**. If the terminal starts at the repository's top level, enter the project folder; if it is already in `ws-11-servlets-and-jsp`, skip the `cd` command. You can check that you are in the right folder by running `ls pom.xml`.

```bash
cd ws-11-servlets-and-jsp
```

Build the WAR with Maven:

```bash
mvn clean package
```

Maven creates `target/ws-11-servlets-and-jsp-1.0-SNAPSHOT.war`. With Tomcat stopped, copy it into Tomcat's `webapps` directory as `WS.war` to use the short `/WS` context path:

```bash
cp target/ws-11-servlets-and-jsp-1.0-SNAPSHOT.war "$HOME/.sdkman/candidates/tomcat/10.1.55/webapps/WS.war"
```

Tomcat uses the deployed WAR filename for the context path, so this deployment is available at `/WS`.

Do not double-click `startup.sh` in Finder. Run this command in VS Code's integrated Terminal to start Tomcat manually:

```bash
"$HOME/.sdkman/candidates/tomcat/10.1.55/bin/startup.sh"
```

If zsh reports `permission denied`, make Tomcat's shell scripts executable for your user once:

```bash
chmod u+x "$HOME/.sdkman/candidates/tomcat/10.1.55/bin/"*.sh
```

`startup.sh` runs Tomcat in the background, so `Tomcat started.` followed by `Process completed` is expected. You can close that Terminal window; Tomcat keeps running. To watch its log from a terminal, run:

```bash
tail -f "$HOME/.sdkman/candidates/tomcat/10.1.55/logs/catalina.out"
```

Press **Ctrl+C** to stop watching the log; this does not stop Tomcat. To keep Tomcat attached to a terminal and see its messages there, run this instead of `startup.sh` and leave the terminal open:

```bash
"$HOME/.sdkman/candidates/tomcat/10.1.55/bin/catalina.sh" run
```

Stop the background server with `shutdown.sh` as shown below.

Tomcat expands the WAR under `webapps`. Open the app and servlet:

- [http://localhost:8080/WS/](http://localhost:8080/WS/)
- [http://localhost:8080/WS/FrontController](http://localhost:8080/WS/FrontController)

The manual deployment context path comes from the deployed WAR filename. To stop Tomcat, run:

```bash
"$HOME/.sdkman/candidates/tomcat/10.1.55/bin/shutdown.sh"
```

For startup or deployment errors, inspect the files under `$HOME/.sdkman/candidates/tomcat/10.1.55/logs/`. No Tomcat Manager user configuration is needed for this WAR deployment. If `sdk home tomcat 10.1.55` printed a different folder, replace `$HOME/.sdkman/candidates/tomcat/10.1.55` in the copy, start, stop, and log paths with the printed path.

## Option 2: Deploy with the Al-rimi Tomcat extension

This option lets the extension build the Maven project, deploy it to Tomcat, and start or stop the server from VS Code.

1. Open the **Tomcat** view from VS Code's Activity Bar. This repository contains several example apps; if the extension asks which app to deploy, choose `ws-11-servlets-and-jsp`.
2. The **Al-rimi Tomcat** extension is already installed in this VS Code setup. Its **Instances & Apps** view manages Tomcat instances and detected web apps.
3. Get the SDKMAN installation paths in the VS Code terminal:

   ```bash
   sdk home java 21.0.12-amzn
   sdk home tomcat 10.1.55
   ```

   In the extension's Tomcat/Java home settings, add or select the printed Java home and Tomcat home paths. Use Tomcat `10.1.55` and Java `21.0.12-amzn` for this project.

4. In VS Code **Settings** (`Cmd+,`), search for `Tomcat` and set **Tomcat: Build Type** to **Maven**. Keep auto-deploy disabled while following this guide so deployment only happens when you request it.
5. Use the app's **Deploy/Run** action in **Instances & Apps**, or the **Deploy** button in the editor title bar, to build and deploy the project. The extension will start its Tomcat instance as needed.

The extension runs `mvn clean package` and deploys the app using its project folder name as the context path. You do not need to run the manual copy/start commands from Option 1 in this option. Open the deployed app from the extension, or try:

- [http://localhost:8080/ws-11-servlets-and-jsp/](http://localhost:8080/ws-11-servlets-and-jsp/)
- [http://localhost:8080/ws-11-servlets-and-jsp/FrontController](http://localhost:8080/ws-11-servlets-and-jsp/FrontController)

Use the extension's **Stop** action to stop this instance. Do not start the manual Tomcat server at the same time on port `8080`. The extension's optional AI log explanations are not needed for build/deploy.

If the extension cannot find `mvn`, open VS Code from a terminal after selecting the SDKMAN versions (`sdk use java 21.0.12-amzn`, `sdk use maven 3.9.16`, and `sdk use tomcat 10.1.55`) so the extension inherits the SDKMAN paths. The extension's build and deployment controls are described in the [Al-rimi Tomcat extension guide](https://github.com/Al-rimi/tomcat#readme).
