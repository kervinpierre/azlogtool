package nyc.citymsp.tools.azlogtool.repository;

import nyc.citymsp.tools.azlogtool.entity.AZLTGrantedAuthority;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by kervin on 2016-07-03.
 */
public interface AZLTGrantedAuthorityRepository extends JpaRepository<AZLTGrantedAuthority, UUID>
{
    @Query("from AZLTGrantedAuthority")
    public List<AZLTGrantedAuthority> findAllAuthorities();

   // public AZLTGrantedAuthority findByAuthority(String authname);
}
