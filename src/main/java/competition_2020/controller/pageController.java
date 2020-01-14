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

    @RequestMapping("2")
    public String a(){
        return "2";
    }


}
