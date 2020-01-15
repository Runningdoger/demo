package competition_2020.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Nick
 * @Date 2020/1/14--13:31
 * @Description
 */
@Controller
public class pageController {

    @RequestMapping("2")
    public String a(){
        return "2";
    }

    @RequestMapping({"/","index"})
    public String index() {
        return "index";
    }

}
