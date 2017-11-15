package nyc.citymsp.tools.azlogtool.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by kervin on 2016-04-30.
 */
public final class AZLTException extends Exception
{
    private static final Logger LOGGER = LogManager.getLogger(
            AZLTException.class);

    public AZLTException(String msg)
    {
        super(msg);
    }

    public AZLTException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
