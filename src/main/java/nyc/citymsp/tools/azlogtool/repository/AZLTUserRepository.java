package nyc.citymsp.tools.azlogtool.repository;

import nyc.citymsp.tools.azlogtool.entity.AZLTUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by kervin on 2016-04-30.
 */
@Repository
public interface AZLTUserRepository
        extends JpaRepository<AZLTUser, UUID>, JpaSpecificationExecutor<AZLTUser>
{
    @Query("from AZLTUser")
    public List<AZLTUser> findAllUsers();

    public AZLTUser findByUsername(String username);
   // public AZLTUser findByUsernameAndPassword(String username, String password);

    @Query("SELECT u.username FROM AZLTUser u")
    public List<String> findAllUsernames();

    @Query("SELECT DISTINCT u.firstName FROM AZLTUser u")
    public List<String> findAllFirstnames();

    @Query("SELECT DISTINCT u.lastName FROM AZLTUser u")
    public List<String> findAllLastnames();
}

