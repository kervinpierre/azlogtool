package nyc.citymsp.tools.azlogtool.service.impl;

import nyc.citymsp.tools.azlogtool.entity.AZLTUser;
import nyc.citymsp.tools.azlogtool.repository.AZLTUserRepository;
import nyc.citymsp.tools.azlogtool.service.AZLTUserService;
import nyc.citymsp.tools.azlogtool.util.AZLTException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * * The actual implementation of the user Service layer interface.
 *
 * @author Kervin Pierre <info@sludev.com>
 *     
 * Created by kervin on 2016-04-30.
 */
@Service
public class AZLTUserServiceImpl implements AZLTUserService
{
    private static final Logger LOGGER = LogManager.getLogger(AZLTUserService.class);

    @Autowired(required = true)
    private AZLTUserRepository userRepository;

    @Override
    public AZLTUser findByUsername(String userName)
    {
        return userRepository.findByUsername(userName);
    }

    public AZLTUser getOne(UUID uuid)
    {
        return userRepository.getOne(uuid);
    }

//    @Override
//    public AZLTUser validateCredential(String username, String password)
//    {
//        return userRepository.findByUsernameAndPassword(username, password);
//    }

    @Override
    public List<AZLTUser> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Page<AZLTUser> findAllUsers(Specification<AZLTUser> spec, Pageable pageable)
    {
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public Page<AZLTUser> findAllUsers(Pageable pageable)
    {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<String> findAllUsernames()
    {
        return userRepository.findAllUsernames();
    }

    @Override
    public List<String> findAllFirstnames()
    {
        return userRepository.findAllFirstnames();
    }

    @Override
    public List<String> findAllLastnames()
    {
        return userRepository.findAllFirstnames();
    }

    @Override
    public AZLTUser createUser(final Map<String, Object> model) throws AZLTException
    {
        AZLTUser res = new AZLTUser();

        if( model == null )
        {
            return null;
        }

        Object idObj = model.get("id");

        if( idObj != null
                && Objects.toString(idObj) != null
                && StringUtils.isNoneBlank(Objects.toString(idObj)) )
        {
            throw new AZLTException(
                    String.format("ID should not be present '%s'",
                            model.get("id")));
        }

        updateUser(res, model);

        return res;
    }

    public AZLTUser updateUser(final Map<String, Object> model) throws AZLTException
    {
        AZLTUser res = null;

        if( model == null )
        {
            return null;
        }




        updateUser(res, model);

        return res;
    }

    @Override
    public void updateUser(final AZLTUser user, final Map<String, Object> model) throws AZLTException
    {
        Object usernameObj = model.get("username");
        Object passwordObj = model.get("password");
        Object emailObj    = model.get("email");
        Object lastSeenObj = model.get("lastSeen");
        Object createdObj  = model.get("createdDate");
        Object firstNameObj    = model.get("firstName");
        Object lastNameObj     = model.get("lastName");
        Object mainGroupObj    = model.get("mainGroup");
        Object statusObj       = model.get("status");
        Object enabledObj      = model.get("enabled");
        Object radiusEnabledObj= model.get("radiusEnabled");
        Object canLoginObj     = model.get("canLogin");
        Object lockedObj       = model.get("locked");
        Object lastModifiedObj = model.get("lastModified");
        Object accountNonLockedObj     = model.get("accountNonLocked");
        Object accountNonExpiredObj    = model.get("accountNonExpired");
        Object accountExpiredObj       = model.get("accountExpired");
        Object credentialsNonExpiredObj= model.get("credentialsNonExpired");
        Object credentialsExpiredObj   = model.get("credentialsExpired");

        String username = null;
        String password = null;
        String email = null;
        Boolean locked = null;
        String lastSeenStr = null;
        DateTime lastSeen = null;
        String createdStr = null;
        DateTime created = null;
        String firstName = null;
        String lastName = null;
        String mainGroup = null;
        String statusStr = null;
        Integer status = null;
        Boolean enabled = null;
        Boolean radiusEnabled = null;
        Boolean canLogin = null;
        Boolean accountNonLocked = null;
        Boolean accountNonExpired = null;
        Boolean accountExpired = null;
        Boolean credentialsNonExpired = null;
        Boolean credentialsExpired = null;
        String lastModifiedStr = null;
        DateTime lastModified = null;

        username = StringUtils.trim(Objects.toString(usernameObj, null));
        password = Objects.toString(passwordObj, null);
        email    = StringUtils.trim(Objects.toString(emailObj, null));
        locked   = BooleanUtils.toBoolean(Objects.toString(lockedObj, null));

        lastSeenStr = Objects.toString(lastSeenObj, null);
        if( StringUtils.isNoneBlank(lastSeenStr) )
        {
            try
            {
                // lastSeen = Date.from(Instant.parse(Objects.toString(lastSeenObj)));
                lastSeen = DateTime.parse(lastSeenStr);
            }
            catch( Exception ex )
            {
                LOGGER.debug(String.format("Error parsing '%s'", lastSeenStr), ex);
            }
        }

        createdStr = Objects.toString(createdObj, null);
        if( StringUtils.isNoneBlank(createdStr) )
        {
            try
            {
                //created = Date.from(Instant.parse(Objects.toString(createdObj)));
                created = DateTime.parse(createdStr);
            }
            catch( Exception ex )
            {
                LOGGER.debug(String.format("Error parsing '%s'", createdStr), ex);
            }
        }

        firstName = StringUtils.trim(Objects.toString(firstNameObj, null));
        lastName = StringUtils.trim(Objects.toString(lastNameObj, null));
        mainGroup = StringUtils.trim(Objects.toString(mainGroupObj, null));

        statusStr = Objects.toString(statusObj, null);
        if( StringUtils.isNoneBlank(statusStr) )
        {
            try
            {
                status = Integer.parseInt(statusStr);
            }
            catch( Exception ex )
            {
                LOGGER.debug(String.format("Error parsing '%s'", statusStr), ex);
            }
        }

        enabled   = BooleanUtils.toBoolean(Objects.toString(enabledObj, null));
        canLogin  = BooleanUtils.toBoolean(Objects.toString(canLoginObj, null));
        radiusEnabled      = BooleanUtils.toBoolean(Objects.toString(radiusEnabledObj, null));
        accountNonLocked   = BooleanUtils.toBoolean(Objects.toString(accountNonLockedObj, null));
        accountNonExpired  = BooleanUtils.toBoolean(Objects.toString(accountNonExpiredObj, null));
        accountExpired     = BooleanUtils.toBoolean(Objects.toString(accountExpiredObj, null));
        credentialsNonExpired  = BooleanUtils.toBoolean(Objects.toString(credentialsNonExpiredObj, null));
        credentialsExpired     = BooleanUtils.toBoolean(Objects.toString(credentialsExpiredObj, null));

        lastModifiedStr = Objects.toString(lastModifiedObj, null);
        if( StringUtils.isNoneBlank(lastModifiedStr) )
        {
            try
            {
                //lastModified = Date.from(Instant.parse(Objects.toString(lastModifiedObj)));
                lastModified = DateTime.parse(lastModifiedStr);
            }
            catch( Exception ex )
            {
                LOGGER.debug(String.format("Error parsing '%s'", lastModifiedStr), ex);
            }
        }

        if( StringUtils.isNoneBlank(username))
        {
            user.setUsername(username);
        }

        if( StringUtils.isNoneBlank(password))
        {
            user.setPassword(password);
        }

        if( StringUtils.isNoneBlank(email))
        {
            user.setEmail(email);
        }

        if( locked != null )
        {
            user.setLocked(locked);
        }

        if( lastSeen != null )
        {
            user.setLastSeen(lastSeen);
        }

        if( created != null )
        {
            user.setCreatedDate(created);
        }

        if( StringUtils.isNoneBlank(firstName) )
        {
            user.setFirstName(firstName);
        }

        if( StringUtils.isNoneBlank(lastName) )
        {
            user.setLastName(lastName);
        }

        if( StringUtils.isNoneBlank(mainGroup) )
        {
            user.setMainGroup(mainGroup);
        }

        if( status != null )
        {
            user.setStatus(status);
        }

        if( enabled != null )
        {
            user.setEnabled(enabled);
        }

        if( radiusEnabled != null )
        {
            user.setRadiusEnabled(radiusEnabled);
        }

        if( canLogin != null )
        {
            user.setCanLogin(canLogin);
        }

        if( accountNonLocked != null )
        {
            user.setAccountNonLocked(accountNonLocked);
        }

        if( accountNonExpired != null )
        {
            user.setAccountNonExpired(accountNonExpired);
        }

        if( accountExpired != null )
        {
            user.setAccountExpired(accountExpired);
        }

        if( credentialsNonExpired != null )
        {
            user.setCredentialsNonExpired(credentialsNonExpired);
        }

        if( credentialsExpired != null )
        {
            user.setCredExpired(credentialsExpired);
        }

        if( lastModified != null )
        {
            user.setLastModifiedDate(lastModified);
        }

        user.setAccountExpired(false);
    }

    @Override
    public AZLTUser createUser(String username)
    {
        AZLTUser res = new AZLTUser();

        res.setUsername(username);
        res.setAccountExpired(false);
        res.setCanLogin(false);

        return res;
    }

    @Override
    public AZLTUser saveAndFlush(AZLTUser AZLTUser)
    {
        AZLTUser res = userRepository.saveAndFlush(AZLTUser);

        return res;
    }

    @Override
    public void flush()
    {
        userRepository.flush();
    }

    @Override
    public void updateUser(AZLTUser AZLTUser)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(AZLTUser AZLTUser)
    {
        userRepository.delete(AZLTUser);
    }
}
