package com.lening.controller;

import com.lening.entity.DeptBean;
import com.lening.entity.MeunBean;
import com.lening.entity.PostBean;
import com.lening.entity.UserBean;
import com.lening.service.UserService;
import com.lening.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * 创作时间：2021/4/6 10:36
 * 作者：王波波
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(@RequestBody UserBean userBean, HttpSession session){
        UserBean gologin = userService.gologin(userBean);
        if (gologin==null){
            return "no";
        }else{
            session.setAttribute("gologin",gologin);
            return "ok";
        }

    }

    @RequestMapping("/getmeunList")
    public List<MeunBean> getmeunList(HttpSession session){
        //获取当前登录用户下不是按钮的菜单
        UserBean gologin = (UserBean) session.getAttribute("gologin");
        //查询该用户下的url
        Set<String> urls = userService.getusermeunUrlsByid(gologin);
        session.setAttribute("urls",urls);
        return userService.getmeunList(gologin);
    }
    @RequestMapping("/getUserlist")
    public Page<UserBean> getUserlist(@RequestBody UserBean userBean, @RequestParam(defaultValue = "1")Integer pageNum,  @RequestParam(defaultValue = "5")Integer pageSize){
        Page<UserBean> userlist = userService.getUserlist(userBean, pageNum, pageSize);
        return userlist;
    }
    @RequestMapping("/postlist")
    public Page<PostBean> postlist(@RequestBody PostBean postBean, @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        Page<PostBean> postlist = userService.getpostList(postBean,pageNum,pageSize);
        return postlist;
    }
    @RequestMapping("/selectdept")
    public Page<DeptBean> selectdept(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize){
        Page<DeptBean> selectdept = userService.selectdept(pageNum,pageSize);
        System.out.println(selectdept);
        return selectdept;
    }
    @RequestMapping("/selectmenu")
    public List<MeunBean> selectmenu(Long pid){
      List<MeunBean> selectmenu =  userService.selectmenu(pid);
      return selectmenu;
    }
    @RequestMapping("/addorupmenu")
    public void addorupmenu(@RequestBody MeunBean meunBean) {
        userService.addorupmenu(meunBean);
    }
    @RequestMapping("/delmenu")
    public void delmenu(Long id){
        userService.delmenu(id);
    }
    @RequestMapping("/selectmenuByid")
    public MeunBean selectmenuByid(Long id){
        MeunBean selectmenuByid = userService.selectmenuByid(id);
        return selectmenuByid;
    }
    @RequestMapping("/savedeptbyid")
    public UserBean savedeptbyid(Long id){
        return userService.savedeptbyid(id);
    }

    @RequestMapping("/upuserdept")
    public void upuserdept(Long userid,@RequestBody Long[] ids){
        userService.upuserdept(userid,ids);
    }

    @RequestMapping("/getpostbyid")
    public UserBean getpostbyid(Long id){
       return userService.getpostbyid(id);

    }

    @RequestMapping("/addpost")
    public void addpost(@RequestBody UserBean userBean,Integer ismain){
            userService.addpost(userBean,ismain);
    }
}
