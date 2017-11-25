package nyc.citymsp.tools.azlogtool.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Created by kervin on 2016-05-12.
 */
@Component
public final class AZLTAppProp
{
    private static final Logger LOGGER = LogManager.getLogger(AZLTAppProp.class);

    @Value("${azlt.ignore-ssl-errors:false}")
    private Boolean azltIgnoreSSLErrors;

    @Value("${azlt.password.hash:}")
    private String azltPasswordHashStr;

    public Boolean getAzltIgnoreSSLErrors()
    {
        return azltIgnoreSSLErrors;
    }

    public String getAzltPasswordHashStr()
    {
        return azltPasswordHashStr;
    }

}
