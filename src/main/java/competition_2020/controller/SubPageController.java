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

    //添加账号
    @RequestMapping("/SubAdmin/acc-add")
    public String accAdd(){
        return "SubAdmin/acc-add";
    }

    //账号编辑
    @RequestMapping("/SubAdmin/acc-edit")
    public String accEdit(){
        return "SubAdmin/acc-edit";
    }

    /**
     * 人员管理
     * @return
     */
    @RequestMapping("/plist")
    public String pList(){
        return "SubAdmin/plist";
    }

    //人员添加
    @RequestMapping("/SubAdmin/p-add")
    public String pAdd(){
        return "SubAdmin/p-add";
    }

    //人员信息编辑
    @RequestMapping("/SubAdmin/p-edit")
    public String pEdit(){
        return "SubAdmin/p-edit";
    }

    /**
     * 配送点查询列表
     * @return
     */
    @RequestMapping("/dislist")
    public String disList(){
        return "SubAdmin/dislist";
    }

    //配送点添加
    @RequestMapping("/SubAdmin/dis-add")
    public String disAdd(){
        return "SubAdmin/dis-add";
    }

    //配送点信息编辑
    @RequestMapping("/SubAdmin/dis-edit")
    public String disEdit(){
        return "SubAdmin/dis-edit";
    }

    /**
     * 配送中心查询列表
     * @return
     */
    @RequestMapping("/dotlist")
    public String dotList(){
        return "SubAdmin/dotlist";
    }

    //配送中心添加
    @RequestMapping("/SubAdmin/dot-add")
    public String dotAdd(){
        return "SubAdmin/dot-add";
    }

    //配送中心信息编辑
    @RequestMapping("/SubAdmin/dot-edit")
    public String dotEdit(){
        return "SubAdmin/dot-edit";
    }

    /**
     * 车辆查询列表
     * @return
     */
    @RequestMapping("/carlist")
    public String carList(){
        return "SubAdmin/carlist";
    }

    //车辆添加
    @RequestMapping("/SubAdmin/car-add")
    public String carAdd(){
        return "SubAdmin/car-add";
    }

    //车辆信息编辑
    @RequestMapping("/SubAdmin/car-edit")
    public String carEdit(){
        return "SubAdmin/car-edit";
    }

    /**
     * 订单增加
     * @return
     */
    @RequestMapping("/order-add")
    public String order(){
        return "SubAdmin/order-add";
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

    /**
     * 订单列表
     * @return
     */
    @RequestMapping("/order-list")
    public String orderList(){
        return "SubAdmin/order-list";
    }

    //订单添加
    @RequestMapping("/SubAdmin/order-add")
    public String orderAdd(){
        return "SubAdmin/car-add";
    }

    //订单信息编辑
    @RequestMapping("/SubAdmin/order-edit")
    public String orderEdit(){
        return "SubAdmin/order-edit";
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

    //库存添加
    @RequestMapping("/SubAdmin/list-add")
    public String listAdd(){
        return "SubAdmin/car-add";
    }

    //库存信息编辑
    @RequestMapping("/SubAdmin/list-edit")
    public String listEdit(){
        return "SubAdmin/list-edit";
    }

    /**
     * 订单编辑
     * @return
     */
    @RequestMapping("/order-edit")
    public String member(){
        return "SubAdmin/order-edit";
    }

}
