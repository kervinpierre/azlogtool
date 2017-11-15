package nyc.citymsp.tools.azlogtool.service;

import nyc.citymsp.tools.azlogtool.entity.AZLTRadCheck;
import nyc.citymsp.tools.azlogtool.util.AZLTException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
public interface AZTLRadCheckService
{
    AZLTRadCheck findByUsername(String username);
    AZLTRadCheck getOne(int id);
    // AZLTRadCheck validateCredential(String username, String password);

    List<AZLTRadCheck> findAllRadChecks();
    Page<AZLTRadCheck> findAllRadChecks(Pageable pageable);
    Page<AZLTRadCheck> findAllRadChecks(Specification<AZLTRadCheck> spec, Pageable pageable);

    void updateRadCheck(AZLTRadCheck rc, Map<String, Object> model) throws AZLTException;

    AZLTRadCheck createRadCheck(String username);
    AZLTRadCheck createRadCheck(Map<String, Object> model) throws AZLTException;

    List<String> findAllUsernames();

    AZLTRadCheck saveAndFlush(AZLTRadCheck AZLTRadCheck);
    void flush();
    void updateRadCheck(AZLTRadCheck AZLTRadCheck);
    void deleteRadCheck(AZLTRadCheck AZLTRadCheck);
}
