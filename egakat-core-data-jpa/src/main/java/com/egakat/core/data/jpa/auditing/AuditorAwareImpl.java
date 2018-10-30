
package com.egakat.core.data.jpa.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	@Override
	public Optional<String> getCurrentAuditor() {
		
/*		log.debug("Getting the username of authenticated user.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
        	log.debug("Current user is anonymous. Returning null.");
            return null;
        }

        String username = ((User) authentication.getPrincipal()).getUsername();
        log.debug("Returning username: {}", username);
*/
		return Optional.of("_");
	}
}