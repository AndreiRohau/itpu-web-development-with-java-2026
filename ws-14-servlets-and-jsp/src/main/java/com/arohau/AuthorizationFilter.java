package com.arohau;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthorizationFilter implements Filter {
	private static final String COMMAND = "command";
	private static final String ROLE = "role";

	Map<String, Set<String>> roleCommands;

	@Override
	public void init(FilterConfig config) throws ServletException {
		roleCommands = new HashMap<>();

		Enumeration<String> initParameterNames = config.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String initParameterName = initParameterNames.nextElement();
			String[] initParameterArray = config.getInitParameter(initParameterName).split(" ");
			roleCommands.put(initParameterName, Set.of(initParameterArray));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// we can check if the session exists, and what attributes it contains...
		HttpSession currentSession = req.getSession(false);
		String roleName = null;
		if (currentSession != null) {
			roleName = (String) currentSession.getAttribute(ROLE);
		}
		final String commandName = req.getParameter(COMMAND);
		final Set<String> availableRoleCommands = roleCommands.get(roleName);

		if (req.getServletPath().equalsIgnoreCase("/commandHandler") ||
				commandName.matches("logout") ||
				commandName.matches("log") ||
				commandName.matches("reg") ||
				commandName.matches("changeLanguage") ||
				commandName.matches("goToPage") ||
				(availableRoleCommands != null && availableRoleCommands.contains(commandName))) {

			chain.doFilter(request, response);
		} else if (hasSomeCondition()) {
			res.sendError(403);
		} else {
			res.sendRedirect("index.jsp");
		}
	}

	private static boolean hasSomeCondition() {
		return false;
	}
}