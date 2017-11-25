package nyc.citymsp.tools.azlogtool.controller.prefs;


import nyc.citymsp.tools.azlogtool.config.AZLTAppProp;
import nyc.citymsp.tools.azlogtool.entity.AZLTUser;
import nyc.citymsp.tools.azlogtool.service.AZLTRadCheckService;
import nyc.citymsp.tools.azlogtool.service.AZLTUserService;
import nyc.citymsp.tools.azlogtool.service.AdminConfigService;
import nyc.citymsp.tools.azlogtool.service.impl.AZLTUserPassword;
import nyc.citymsp.tools.azlogtool.util.AZLTException;
import nyc.citymsp.tools.azlogtool.util.LoggingUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kervin on 2016-04-29.
 */
@Controller("/prefs")
public class PrefsController
{
    private static final Logger LOGGER = LogManager.getLogger(PrefsController.class);

    @Autowired
    private AZLTAppProp appProps;

    @Autowired
    private AZLTRadCheckService radCheckService;

    @Autowired
    private AdminConfigService adminConfigService;

    @Autowired
    private AZLTUserService userService;

    @RequestMapping("/prefs")
    public String prefs(HttpServletRequest request,
                              @RequestParam(value = "saveButton", required = false) String savedStr,
                              @RequestParam(value = "P1", required = false) String p1Str) throws AZLTException
    {
        LoggingUtils.logRequestDebug(request);

        UsernamePasswordAuthenticationToken  puser = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
        if( puser == null
                || puser.isAuthenticated() == false )
        {
            return "/index";
        }

        AZLTUser user = (AZLTUser)puser.getPrincipal();
        if( user == null )
        {
            return "/index";
        }

        if( StringUtils.equals(savedStr, "saved")
                && StringUtils.isNoneBlank(p1Str))
        {
            AZLTUserPassword up = new AZLTUserPassword();
            up.changePassword(user, p1Str);
            userService.saveAndFlush(user);

            AZLTUserPassword.changePassword(user, p1Str,
                    userService, radCheckService,
                    appProps.getAzltPasswordHashStr(),
                    true);

            return "prefs/success";
        }

        return "prefs/index";
    }

//    /**
//     * Save then redirect to filled form.
//     *
//     * @param config
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
//    public String save(@ModelAttribute("SpringWeb")AdminConfig config,
//                             ModelMap model)
//    {
//        model.addAttribute("weWorkDoneDir", config.getWeWorkDoneDir());
//        model.addAttribute("id", config.getId());
//
//        return "adminSave";
//    }
}
