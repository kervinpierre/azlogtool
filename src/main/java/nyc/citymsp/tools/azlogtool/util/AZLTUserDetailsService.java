package nyc.citymsp.tools.azlogtool.util;


import nyc.citymsp.tools.azlogtool.entity.AZLTGrantedAuthority;
import nyc.citymsp.tools.azlogtool.entity.AZLTUser;
import nyc.citymsp.tools.azlogtool.service.AZLTGrantedAuthorityService;
import nyc.citymsp.tools.azlogtool.service.AZLTUserService;
import nyc.citymsp.tools.azlogtool.service.impl.AZLTUserPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kervin on 2016-04-30.
 */
@Component
public final class AZLTUserDetailsService implements UserDetailsService
{
    private static final Logger LOGGER = LogManager.getLogger(AZLTUserDetailsService.class);

    @Autowired(required = true)
    private AZLTUserService userService;

    @Autowired(required = true)
    private AZLTGrantedAuthorityService authService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        // FIXME : SECURITY : Regex the 'username' to make sure it's not an
        // attack

        AZLTUser user = userService.findByUsername(userName);

        if (user == null)
        {
            throw new UsernameNotFoundException(userName + " not found.");
        }

        //AZLTUserPassword up = new AZLTUserPassword();
        //up.validatePassword(user, )

        return user;
    }
    
    public void addDefaultAdminUser() throws AZLTException
    {
        AZLTUser user = userService.findByUsername("radmin");
        if(user != null)
        {
            return;
        }

        user = userService.createUser("radmin");
        user.setCreatedDate(DateTime.now());
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setAccountExpired(false);
        user.setCanLogin(true);
        user.setEnabled(true);
        user.setLocked(false);
        user.setCredentialsNonExpired(true);
        user.setCredExpired(false);

        Set<AZLTGrantedAuthority> authSet = new HashSet<>();
        AZLTGrantedAuthority auth = authService.createAuthority("ROLE_ADMIN");
        auth.setAzltUser(user);
        authSet.add(auth);
        user.setUserAuthorities(authSet);

        AZLTUserPassword up = new AZLTUserPassword();
        String pass = "test"; // up.generatePassword(user);
        up.changePassword(user, "test");

        LOGGER.info(String.format("Generated secret : '%s'", pass));

        authService.saveAndFlush(auth);
        userService.saveAndFlush(user);
    }

}
