package tech.fiap.project.infra.configuration.authorization;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class RetrieveCurrentAuthenticatedEmail {

	public static String retrieveEmailAuthenticated() {
		String email = RetrieveCurrentAuthenticatedEmail.getAuthenticatedUserEmail();
		if (email == null) {
			return "User not authenticated";
		}
		return email;
	}

	private static String getAuthenticatedUserEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			}
		}
		return null;
	}

}
