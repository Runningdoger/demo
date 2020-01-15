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

    @RequestMapping({"/","index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String b(){
        return "login1";
    }

    @RequestMapping("/order-list")
    public String orderList(){
        return "/order-list.html";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/echarts1")
    public String echarts1(){
        return "echarts1";
    }

}
