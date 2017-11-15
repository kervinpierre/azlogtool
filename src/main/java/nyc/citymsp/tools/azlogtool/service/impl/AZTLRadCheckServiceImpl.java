package nyc.citymsp.tools.azlogtool.service.impl;

import nyc.citymsp.tools.azlogtool.entity.AZLTRadCheck;
import nyc.citymsp.tools.azlogtool.repository.AZLTRadCheckRepository;
import nyc.citymsp.tools.azlogtool.service.AZTLRadCheckService;
import nyc.citymsp.tools.azlogtool.util.AZLTException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * * The actual implementation of the user Service layer interface.
 *
 * @author Kervin Pierre <info@sludev.com>
 *     
 * Created by kervin on 2016-04-30.
 */
@Service
public class AZTLRadCheckServiceImpl implements AZTLRadCheckService
{
    private static final Logger LOGGER = LogManager.getLogger(AZTLRadCheckService.class);

    @Autowired(required = true)
    private AZLTRadCheckRepository radCheckRepository;

    @Override
    public AZLTRadCheck findByUsername(String userName)
    {
        return radCheckRepository.findByUsername(userName);
    }

    public AZLTRadCheck getOne(int id)
    {
        return radCheckRepository.getOne(id);
    }

    @Override
    public List<AZLTRadCheck> findAllRadChecks()
    {
        return  radCheckRepository.findAll();
    }

    @Override
    public Page<AZLTRadCheck> findAllRadChecks(Pageable pageable)
    {
        return  radCheckRepository.findAll(pageable);
    }

    @Override
    public Page<AZLTRadCheck> findAllRadChecks(Specification<AZLTRadCheck> spec, Pageable pageable)
    {
        return  radCheckRepository.findAll(spec, pageable);
    }

//    @Override
//    public AZLTRadCheck validateCredential(String username, String password)
//    {
//        return radCheckRepository.findByUsernameAndPassword(username, password);
//    }

    @Override
    public AZLTRadCheck createRadCheck(final Map<String, Object> model) throws AZLTException
    {
        AZLTRadCheck res = new AZLTRadCheck();

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

        updateRadCheck(res, model);

        return res;
    }

    @Override
    public List<String> findAllUsernames()
    {
        return radCheckRepository.findAllUsernames();
    }

    public AZLTRadCheck updateRadCheck(final Map<String, Object> model) throws AZLTException
    {
        AZLTRadCheck res = null;

        if( model == null )
        {
            return null;
        }

        updateRadCheck(res, model);

        return res;
    }

    @Override
    public void updateRadCheck(final AZLTRadCheck user, final Map<String, Object> model) throws AZLTException
    {
        Object usernameObj = model.get("username");
        Object attributeObj = model.get("attribute");
        Object opObj    = model.get("op");
        Object valueObj = model.get("value");

        String username = null;
        String attribute = null;
        String op = null;
        String value = null;

        username  = StringUtils.trim(Objects.toString(usernameObj, null));
        attribute = StringUtils.trim(Objects.toString(attributeObj, null));
        op        = StringUtils.trim(Objects.toString(opObj, null));
        value     = StringUtils.trim(Objects.toString(valueObj, null));

        if( StringUtils.isNoneBlank(username))
        {
            user.setUsername(username);
        }

        if( StringUtils.isNoneBlank(attribute))
        {
            user.setAttribute(attribute);
        }

        if( StringUtils.isNoneBlank(op))
        {
            user.setOp(op);
        }

        if( value != null )
        {
            user.setValue(value);
        }
    }

    @Override
    public AZLTRadCheck createRadCheck(String username)
    {
        AZLTRadCheck res = new AZLTRadCheck();

        res.setUsername(username);

        return res;
    }

    @Override
    public AZLTRadCheck saveAndFlush(AZLTRadCheck AZLTRadCheck)
    {
        AZLTRadCheck res = radCheckRepository.saveAndFlush(AZLTRadCheck);

        return res;
    }

    @Override
    public void flush()
    {
        radCheckRepository.flush();
    }

    @Override
    public void updateRadCheck(AZLTRadCheck AZLTRadCheck)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRadCheck(AZLTRadCheck AZLTRadCheck)
    {
        radCheckRepository.delete(AZLTRadCheck);
    }
}
