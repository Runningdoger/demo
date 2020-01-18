package competition_2020.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Nick
 * @Date 2020/1/14--13:31
 * @Description
 */
@Controller
public class pageController {

    @RequestMapping("/admin-add")
    public String a(){
        return "admin-add";
    }

    @RequestMapping({"/MajorAdminIndex","/"})
    public String index() {
        return "MajorAdmin/index";
    }

    @RequestMapping("/login2")
    public String c(){
        return "login2";
    }

    @RequestMapping("/login")
    public String b(){
        return "login1";
    }

    @RequestMapping("/order-list")
    public String orderList(){
        return "order-list";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "MajorAdmin/welcome";
    }

    @RequestMapping("/echarts1")
    public String echarts1(){
        return "echarts1";
    }

    @RequestMapping("/welcome1")
    public String welcome1(){
        return "welcome1";
    }

    @RequestMapping("/2")
    public String d(){
        return "MajorAdmin/2";
    }

}
