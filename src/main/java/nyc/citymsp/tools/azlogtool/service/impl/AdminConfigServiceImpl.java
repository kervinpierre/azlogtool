package nyc.citymsp.tools.azlogtool.service.impl;

import nyc.citymsp.tools.azlogtool.entity.AdminConfig;
import nyc.citymsp.tools.azlogtool.repository.AdminConfigRepository;
import nyc.citymsp.tools.azlogtool.service.AdminConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * * The actual implementation of the user Service layer interface.
 *
 * @author Kervin Pierre <info@sludev.com>
 *     
 * Created by kervin on 2016-04-30.
 */
@Primary
@Service
public class AdminConfigServiceImpl implements AdminConfigService
{
    @Autowired(required = true)
    private AdminConfigRepository inputRepository;

    @Override
    public AdminConfig findById(UUID id)
    {
        return inputRepository.findById(id);
    }

    @Override
    public AdminConfig findFirstByOrderByLabel()
    {
        return inputRepository.findFirstByOrderByLabel();
    }

    public List<AdminConfig> findAll()
    {
        return inputRepository.findAll();
    }

    @Override
    public AdminConfig createAdminConfig(String label)
    {
        AdminConfig res = new AdminConfig();

        res.setLabel(label);

        return res;
    }

    @Override
    public AdminConfig saveAndFlush(AdminConfig AdminConfig)
    {
        AdminConfig res = inputRepository.saveAndFlush(AdminConfig);

        return res;
    }

    @Override
    public void flush()
    {
        inputRepository.flush();
    }

    @Override
    public void updateAdminConfig(AdminConfig AdminConfig)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAdminConfig(AdminConfig AdminConfig)
    {
        inputRepository.delete(AdminConfig);
    }
}
