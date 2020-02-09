package competition_2020.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Nick
 * @Date 2020/1/14--13:31
 * @Description
 */
@Controller
public class SubPageController {

    /**
     * 账号权限管理
     * @return
     */
    @RequestMapping("/acclist")
    public String accList(){
        return "SubAdmin/acclist";
    }

    /**
     * 人员管理
     * @return
     */
    @RequestMapping("/plist")
    public String pList(){
        return "SubAdmin/plist";
    }


    /**
     * 配送点查询列表
     * @return
     */
    @RequestMapping("/dislist")
    public String disList(){
        return "SubAdmin/dislist";
    }

    /**
     * 配送中心查询列表
     * @return
     */
    @RequestMapping("/dotlist")
    public String dotList(){
        return "SubAdmin/dotlist";
    }

    /**
     * 车辆查询列表
     * @return
     */
    @RequestMapping("/carlist")
    public String carList(){
        return "SubAdmin/carlist";
    }

    @RequestMapping("/admin-add")
    public String a(){
        return "admin-add";
    }

    @RequestMapping("/index1")
    public String s(){
        return "index1";
    }

    @RequestMapping({"/SubAdminIndex","/"})
    public String index() {
        return "SubAdmin/index";
    }

    @RequestMapping("/login")
    public String c(){
        return "login2";
    }

    @RequestMapping("/login2")
    public String b(){
        return "login1";
    }

    @RequestMapping("/order-list")
    public String orderList(){
        return "SubAdmin/order-list";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "SubAdmin/welcome";
    }

    @RequestMapping("/echarts1")
    public String echarts1(){
        return "echarts1";
    }

    /**
     * 数据报表
     * @return
     */
    @RequestMapping("/welcome1")
    public String welcome1(){
        return "SubAdmin/welcome1";
    }

    /**
     * 可视化拼单
     * @return
     */
    @RequestMapping("/2")
    public String d(){
        return "SubAdmin/2";
    }

    /**
     * 订单实时信息
     * @return
     */
    @RequestMapping("/order")
    public String o(){
        return "SubAdmin/order";
    }

    /**
     * 库存信息
     * @return
     */
    @RequestMapping("/list")
    public String gList(){
        return "SubAdmin/list";
    }

}
