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

    //人员添加
    @RequestMapping("/MajorAdmin/p-add")
    public String pAdd(){
        return "MajorAdmin/p-add";
    }

    //人员信息编辑
    @RequestMapping("/MajorAdmin/p-edit")
    public String pEdit(){
        return "MajorAdmin/p-edit";
    }
    
    /**
     * 公司及车辆查询
     * @return
     */
    @RequestMapping("/MajorAdmin/carlist")
    public String carList(){
        return "MajorAdmin/carlist";
    }

    //车辆添加
    @RequestMapping("/MajorAdmin/car-add")
    public String carAdd(){
        return "MajorAdmin/car-add";
    }

    //车辆信息编辑
    @RequestMapping("/MajorAdmin/car-edit")
    public String carEdit(){
        return "MajorAdmin/car-edit";
    }
    
    /**
     * 公司及配送中心查询
     * @return
     */
    @RequestMapping("/MajorAdmin/dotlist")
    public String dotList(){
        return "MajorAdmin/dotlist";
    }

    //配送中心添加
    @RequestMapping("/MajorAdmin/dot-add")
    public String dotAdd(){
        return "MajorAdmin/dot-add";
    }

    //配送中心信息编辑
    @RequestMapping("/MajorAdmin/dot-edit")
    public String dotEdit(){
        return "MajorAdmin/dot-edit";
    }
    
    /**
     * 公司及配送点查询
     * @return
     */
    @RequestMapping("/MajorAdmin/dislist")
    public String disList(){
        return "MajorAdmin/dislist";
    }

    //配送点添加
    @RequestMapping("/MajorAdmin/dis-add")
    public String disAdd(){
        return "MajorAdmin/dis-add";
    }

    //配送点信息编辑
    @RequestMapping("/MajorAdmin/dis-edit")
    public String disEdit(){
        return "MajorAdmin/dis-edit";
    }
    
    /*@RequestMapping("/MajorAdmin/dislist")
    public String test(){
        return "MajorAdmin/admin-cate";
    }*/

    /**
     * 公司账号权限分配
     * @return
     */
    @RequestMapping("/MajorAdmin/acclist")
    public String accList(){
        return "MajorAdmin/acclist";
    }

    //添加账号
    @RequestMapping("/MajorAdmin/acc-add")
    public String accAdd(){
        return "MajorAdmin/acc-add";
    }

    //账号编辑
    @RequestMapping("/MajorAdmin/acc-edit")
    public String accEdit(){
        return "MajorAdmin/acc-edit";
    }
    
}
