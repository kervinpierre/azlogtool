package nyc.citymsp.tools.azlogtool.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Granted Authorities on system users. This class controls the ROLE_* based
 * permissions in the system. It does NOT control ACL Permissions, which are
 * lighter and more fine grain.
 *
 * @author Kervin Pierre <info@sludev.com>
 *
 * Created by kervin on 2016-04-30.
 */
@Entity
@Audited
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@azltGrantedAuthId")
@Table(name = "azlt_granted_authority")
public class AZLTGrantedAuthority extends AbstractAuditable<AZLTUser, UUID>
        implements GrantedAuthority
{
    private static final Logger LOGGER = LogManager.getLogger(AZLTGrantedAuthority.class);

    private UUID authId;
    private String authority;
    private AZLTUser azltUser;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "auth_id", unique = true, columnDefinition = "BINARY(16)")
    public UUID getAuthId()
    {
        return authId;
    }

    public void setAuthId(UUID aid)
    {
        authId = aid;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public AZLTUser getAzltUser()
    {
        return azltUser;
    }

    public void setAzltUser(AZLTUser u)
    {
        azltUser = u;
    }

    @Override
    @Column(name = "authority", nullable = false)
    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String auth)
    {
        this.authority = auth;
    }
}
