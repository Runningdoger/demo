package competition_2020.controller;

import competition_2020.service.majorAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nick
 * @Date 2020/1/13--15:42
 * @Description
 */
@CrossOrigin
@Controller
public class adminController {

    @Resource
    majorAdminService majorAdminService;

    /**
     * 总管理员验证登录
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/m_adminLogin", method = RequestMethod.GET)
    @ResponseBody
    public Map login(@RequestParam(value = "username", required = true) String name,
                     @RequestParam(value = "password", required = true) String password) throws Exception {
        boolean b = majorAdminService.login(name, password);
        Map<String, String> map = new HashMap<>();
        if (b) {
            map.put("key", "100");// 登陆成功
        } else {
            map.put("key", "200");// 登陆失败
        }
        return map;
    }

}
