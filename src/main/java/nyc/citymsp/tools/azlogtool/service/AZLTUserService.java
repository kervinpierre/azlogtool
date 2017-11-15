package nyc.citymsp.tools.azlogtool.service;

import nyc.citymsp.tools.azlogtool.entity.AZLTUser;
import nyc.citymsp.tools.azlogtool.util.AZLTException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The service layer interface for the User-Service implementation.
 *
 * This layer essentially hides the repository from higher layers and also provides
 * business logic to these higher layers.
 *
 * @author Kervin Pierre <info@sludev.com>
 * Created by kervin on 2016-04-30.
 */
@Service
public interface AZLTUserService
{
    AZLTUser findByUsername(String username);
    AZLTUser getOne(UUID uuid);
   // AZLTUser validateCredential(String username, String password);

    List<AZLTUser> findAllUsers();
    Page<AZLTUser> findAllUsers(Pageable pageable);
    Page<AZLTUser> findAllUsers(Specification<AZLTUser> spec, Pageable pageable);

    List<String> findAllUsernames();
    List<String> findAllFirstnames();
    List<String> findAllLastnames();

    void updateUser(AZLTUser user, Map<String, Object> model) throws AZLTException;

    AZLTUser createUser(String username);
    AZLTUser createUser(Map<String, Object> model) throws AZLTException;

    AZLTUser saveAndFlush(AZLTUser AZLTUser);
    void flush();
    void updateUser(AZLTUser AZLTUser);
    void deleteUser(AZLTUser AZLTUser);
}
