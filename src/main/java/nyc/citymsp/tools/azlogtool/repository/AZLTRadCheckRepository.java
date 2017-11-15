package nyc.citymsp.tools.azlogtool.repository;

import nyc.citymsp.tools.azlogtool.entity.AZLTRadCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kervin on 2016-04-30.
 */
@Repository
public interface AZLTRadCheckRepository
        extends JpaRepository<AZLTRadCheck, Integer>, JpaSpecificationExecutor<AZLTRadCheck>
{
    @Query("from AZLTRadCheck")
    public List<AZLTRadCheck> findAllRadCheck();

    public AZLTRadCheck findByUsername(String username);
   // public AZLTRadCheck findByUsernameAndPassword(String username, String password);

    @Query("SELECT DISTINCT u.username FROM AZLTRadCheck u")
    public List<String> findAllUsernames();
}

