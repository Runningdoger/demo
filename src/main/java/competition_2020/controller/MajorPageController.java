package competition_2020.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Nick
 * @Date 2020/2/8--15:32
 * @Description
 */
@Controller
public class MajorPageController {

    /**
     * 总管理员首页
     * @return
     */
    @RequestMapping("/MajorAdmin/index")
    public String maj(){
        return "MajorAdmin/index";
    }

    /**
     * 公司及人员查询
     * @return
     */
    @RequestMapping("/MajorAdmin/plist")
    public String pList(){
        return "MajorAdmin/plist";
    }

    /**
     * 公司及车辆查询
     * @return
     */
    @RequestMapping("/MajorAdmin/carlist")
    public String carList(){
        return "MajorAdmin/carlist";
    }

    /**
     * 公司及配送中心查询
     * @return
     */
    @RequestMapping("/MajorAdmin/dotlist")
    public String dotList(){
        return "MajorAdmin/dotlist";
    }

    /**
     * 公司及配送点查询
     * @return
     */
    @RequestMapping("/MajorAdmin/dislist")
    public String disList(){
        return "MajorAdmin/dislist";
    }

    /**
     * 公司账号权限分配
     * @return
     */
    @RequestMapping("/MajorAdmin/acclist")
    public String accList(){
        return "MajorAdmin/acclist";
    }

}
