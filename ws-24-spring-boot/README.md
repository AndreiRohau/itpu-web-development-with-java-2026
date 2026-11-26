# Spring Boot authentication example

This project demonstrates a small, layered registration and authentication flow built with Spring Boot, Spring MVC, Thymeleaf, Spring Security, Spring Data JPA, and H2.

## Layers

- `com.arohau.web` contains MVC controllers and routes.
- `com.arohau.service` contains registration, validation input, and Spring Security's database-backed `UserDetailsService`.
- `com.arohau.repository` contains Spring Data JPA repositories.
- `com.arohau.domain` contains the `AppUser` and `Role` entities.
- `com.arohau.config` configures security and initializes the built-in roles.

New accounts are always assigned `ROLE_USER`. The `ROLE_ADMIN` role is initialized too, and `/admin` shows a role-protected route. Registration never accepts a role from the form.

## Run

From this directory, run:

```sh
mvn spring-boot:run
```

Open <http://localhost:8080/register> to create an account, then sign in at <http://localhost:8080/login>. The H2 database is file-backed at `./authdb`, so account data remains after restart. Passwords are stored as BCrypt hashes.

The H2 console is enabled at <http://localhost:8080/h2-console> for local development. Connect with JDBC URL `jdbc:h2:file:./authdb`, username `sa`, and a blank password. The console is permitted through Spring Security and uses same-origin frames; keep it limited to local development.

```
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'qwer'
  AND NOT EXISTS (
    SELECT 1 FROM user_roles ur
    WHERE ur.user_id = u.id AND ur.role_id = r.id
  );
```

The existing Actuator settings and conditional bean examples in this project remain in place.

Open these URLs in the same browser session:
- /custom-actuator/health — application health
- /custom-actuator/info — the info.app.* values from [application.properties](/Users/Andrei_Rohau/repository/itpu/2026/itpu-web-development-with-java-2026/ws-24-spring-boot/src/main/resources/application.properties)
- /custom-actuator/beans — registered Spring beans
- /custom-actuator/metrics — available metrics; try /custom-actuator/metrics/jvm.memory.used
