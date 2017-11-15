package nyc.citymsp.tools.azlogtool.service;

import nyc.citymsp.tools.azlogtool.entity.AZLTGrantedAuthority;
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
public interface AZLTGrantedAuthorityService
{
    AZLTGrantedAuthority findByAuthority(String authname);
    AZLTGrantedAuthority getOne(UUID uuid);

    AZLTGrantedAuthority createAuthority(String authname);

    AZLTGrantedAuthority saveAndFlush(AZLTGrantedAuthority grantedAuthority);
    void flush();
    void updateAuthority(AZLTGrantedAuthority grantedAuthority);
    void deleteAuthority(AZLTGrantedAuthority grantedAuthority);
}
