package com.klu.project.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUtils {

    private SecurityUtils() {
        // Utility class - no instantiation
    }

    public static String getCurrentUsername(Authentication authentication) {
        return authentication.getName();
    }

    public static boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public static boolean isFaculty(Authentication authentication) {
        return hasRole(authentication, "FACULTY");
    }

    public static boolean isStudent(Authentication authentication) {
        return hasRole(authentication, "STUDENT");
    }
}
