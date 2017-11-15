package nyc.citymsp.tools.azlogtool.service.impl;

import nyc.citymsp.tools.azlogtool.entity.AZLTGrantedAuthority;
import nyc.citymsp.tools.azlogtool.repository.AZLTGrantedAuthorityRepository;
import nyc.citymsp.tools.azlogtool.service.AZLTGrantedAuthorityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by kervin on 2016-07-03.
 */
@Service
public class AZLTGrantedAuthorityServiceImpl implements AZLTGrantedAuthorityService
{
    private static final Logger LOGGER = LogManager.getLogger(AZLTGrantedAuthorityServiceImpl.class);

    @Autowired(required = true)
    private AZLTGrantedAuthorityRepository authRepository;

    @Override
    public AZLTGrantedAuthority findByAuthority(String authname)
    {
        return null;
    }

    @Override
    public AZLTGrantedAuthority getOne(UUID uuid)
    {
        return authRepository.getOne(uuid);
    }

    @Override
    public AZLTGrantedAuthority createAuthority(String authname)
    {
        AZLTGrantedAuthority res = new AZLTGrantedAuthority();
        res.setAuthority(authname);

        return res;
    }

    @Override
    public AZLTGrantedAuthority saveAndFlush(AZLTGrantedAuthority grantedAuthority)
    {
        AZLTGrantedAuthority res = authRepository.saveAndFlush(grantedAuthority);

        return res;
    }

    @Override
    public void flush()
    {
        authRepository.flush();
    }

    @Override
    public void updateAuthority(AZLTGrantedAuthority grantedAuthority)
    {

    }

    @Override
    public void deleteAuthority(AZLTGrantedAuthority grantedAuthority)
    {
        authRepository.delete(grantedAuthority);
    }
}
