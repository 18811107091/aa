package com.lening.service;


import com.lening.entity.DeptBean;
import com.lening.entity.MeunBean;
import com.lening.entity.PostBean;
import com.lening.entity.UserBean;
import com.lening.utils.Page;

import java.util.List;
import java.util.Set;

/**
 * 创作时间：2021/4/6 10:37
 * 作者：王波波
 */
public interface UserService {
    List<MeunBean> getmeunList(UserBean gologin);

    Page<UserBean> getUserlist(UserBean userBean, Integer pageNum, Integer pageSize);

    Page<PostBean> getpostList(PostBean postBean, Integer pageNum, Integer pageSize);

    Page<DeptBean> selectdept( Integer pageNum, Integer pageSize);

    List<MeunBean> selectmenu(Long pid);

    void addorupmenu(MeunBean meunBean);

    void delmenu(Long id);

    MeunBean selectmenuByid(Long userid);

    UserBean savedeptbyid(Long userid);

    void upuserdept(Long userid, Long[] ids);

    UserBean getpostbyid(Long id);

    UserBean gologin(UserBean userBean);

    void addpost(UserBean userBean,Integer ismain);

    Set<String> getusermeunUrlsByid(UserBean gologin);
}
