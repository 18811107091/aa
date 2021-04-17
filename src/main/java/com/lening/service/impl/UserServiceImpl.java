package com.lening.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.entity.*;
import com.lening.mapper.DeptMapper;
import com.lening.mapper.MeunMapper;
import com.lening.mapper.PostMapper;
import com.lening.mapper.UserMapper;
import com.lening.redis.RedisUtil;
import com.lening.service.UserService;
import com.lening.utils.MD5key;
import com.lening.utils.Page;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 创作时间：2021/4/6 10:37
 * 作者：王波波
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private MeunMapper meunMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PostMapper postMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public UserBean gologin(UserBean userBean) {
        if (userBean!=null){
           List<UserBean> getLogin = userMapper.getLogin(userBean);
            if (getLogin!=null&&getLogin.size()==1){
                //开始加密获取到用户输入的信息
                UserBean userBean1 = getLogin.get(0);
                //获取到用户输入的密码进行加密
                String pwd = userBean.getPwd();
                pwd = userBean1.getAccountname()+pwd+userBean1.getPwdsalt();
                MD5key md5key = new MD5key();
                String newpwd = md5key.getkeyBeanofStr(pwd);
                //判断相等则返回
                if (newpwd.equals(userBean1.getPwd())){
                    return userBean1;
                }
            }
        }
        return null;
    }

    @Test
    public void test(){
        String pwd = "1234";
        pwd = "zhaomin"+pwd+"1234";
        MD5key md5key = new MD5key();
        String s = md5key.getkeyBeanofStr(pwd);
        System.out.println(s);
    }

    public List<MeunBean> getmeunList(UserBean gologin) {
       /* //判断查询当前登录用户下不是按钮的菜单
        if (gologin!=null){
            MeunBeanExample meunBeanExample = new MeunBeanExample();
            MeunBeanExample.Criteria criteria = meunBeanExample.createCriteria();
            criteria.andIsbuttonEqualTo(0);
            List<MeunBean> meunBeans = meunMapper.selectByExample(meunBeanExample);
            return meunBeans;
        }*/
        if(gologin!=null){
            List<MeunBean> list = null;
            redisUtil.removeObject(gologin.getId());
            //从缓存中获取用户列表
            Object userListCache = redisUtil.getObject(gologin.getId());
            list = (List<MeunBean>) userListCache;
            //判断缓存中是否存在
            if (userListCache != null) {//不空，则强转返回
                System.out.println("redis中存在，直接返回");
                list = (List<MeunBean>) userListCache;
            }else{
                System.out.println("redis缓存中不存在，从数据库中取出，并且放入缓存");
                //查询数据库，取出
                list = meunMapper.getmeunList(gologin.getId());
                //放入redis缓存
                redisUtil.putObject(gologin.getId(), list);
            }
            return list;
        }
        return null;
    }

    public Page getUserlist(UserBean userBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        if (userBean!=null){
            if (userBean.getUname()!=null&&userBean.getUname().length()>=1){
                criteria.andUnameLike("%"+userBean.getUname()+"%");
            }
            if (userBean.getAge()!=null){
                criteria.andAgeGreaterThanOrEqualTo(userBean.getAge());
            }
            if (userBean.getEage()!=null){
                criteria.andAgeLessThanOrEqualTo(userBean.getEage());
            }
        }
        List<UserBean> userBeans = userMapper.selectByExample(userBeanExample);
        PageInfo<UserBean> userBeanPageInfo = new PageInfo<UserBean>(userBeans);
        Long total = userBeanPageInfo.getTotal();
        Page<UserBean> page = new Page<UserBean>(userBeanPageInfo.getPageNum()+"",total.intValue(),userBeanPageInfo.getPageSize()+"");
        page.setList(userBeans);
        return page;
    }

    @Override
    public Page<PostBean> getpostList(PostBean postBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PostBeanExample postBeanExample = new PostBeanExample();
        PostBeanExample.Criteria criteria = postBeanExample.createCriteria();
        if (postBean!=null){
            if (postBean.getPostname()!=null){
                criteria.andPostnameLike("%"+postBean.getPostname()+"%");
            }
        }
        List<PostBean> postBeans = postMapper.selectByExample(postBeanExample);
        PageInfo<PostBean> postBeanPageInfo = new PageInfo<PostBean>(postBeans);
        Long total = postBeanPageInfo.getTotal();
        Page<PostBean> postBeanPage = new Page<PostBean>(postBeanPageInfo.getPageNum()+"",total.intValue(),postBeanPageInfo.getPageSize()+"");
        postBeanPage.setList(postBeans);
        return postBeanPage;
    }

    @Override
    public Page selectdept(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<DeptBean> deptBeans = deptMapper.selectByExample(null);
        PageInfo<DeptBean> PageInfo = new PageInfo<DeptBean>(deptBeans);
        long total = PageInfo.getTotal();
        Page<DeptBean> page = new Page<DeptBean>(PageInfo.getPageNum()+"", (int) total,PageInfo.getSize()+"");
        page.setList(deptBeans);
        return page;
    }

    @Override
    public List<MeunBean> selectmenu(@RequestParam(defaultValue = "1") Long pid) {
        MeunBeanExample meunBeanExample = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = meunBeanExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<MeunBean> meunBeans = meunMapper.selectByExample(meunBeanExample);
        for (MeunBean meunBean : meunBeans) {
            System.out.println(meunBean.toString());
        }
        return meunBeans;
    }

    @Override
    public void addorupmenu(MeunBean meunBean) {
        if(meunBean!=null){
            if (meunBean.getId()!=null){
                 meunMapper.updateByPrimaryKeySelective(meunBean);
            }else {
                meunMapper.insertSelective(meunBean);
        }
        }
    }

    List<Long> ids = new ArrayList<Long>();
    @Override
    public void delmenu(Long id) {
        selectmenutodel(id);
        for (Long id1 : ids) {
            System.out.println(ids);
            meunMapper.deleteByPrimaryKey(id1);
        }
    }

    @Override
    public MeunBean selectmenuByid(Long id) {
        return meunMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserBean savedeptbyid(Long id) {
        UserBean userBean = userMapper.selectByPrimaryKey(id);
        List<UserBean> savedeptbyid = userMapper.savedeptbyid(id);
        List<DeptBean> deptBeans = deptMapper.selectByExample(null);
        userBean.setDeptBeans(deptBeans);
        userBean.setDepts(savedeptbyid);
        System.out.println(userBean.toString());
        return userBean;
    }

    @Override
    public void upuserdept(Long userid, Long[] ids) {
        userMapper.delpost(userid);
        userMapper.deldept(userid);
        if (ids!=null&&ids.length>=1)for (Long deptid : ids) {
                userMapper.adddept(userid,deptid);
        }
    }



    @Override
    public UserBean getpostbyid(Long id) {
        UserBean userBean = userMapper.selectByPrimaryKey(id);
        //查询这个用户有没有部门
        List<DeptBean> dlist = userMapper.getUserDeptById(id);
        //查询部门里面的职位
        if(dlist!=null&&dlist.size()>=1){
            for (DeptBean deptBean : dlist) {
                //查询职业
                List<PostBean> plist = deptMapper.getDeptPostList(deptBean.getId());
                //查询该用户拥有的职业id
                Long[] postids = deptMapper.getUserPostByIdAndDeptid(id,deptBean.getId());
                deptBean.setPostids(postids);
                deptBean.setPlist(plist);
            }
        }
        userBean.setDlist(dlist);
        return userBean;
    }
    @Override
    public void addpost(UserBean userBean,Integer ismain) {
        //先删除已有的职位
        userMapper.deleteByuserid(userBean.getId());
        if (userBean.getDlist()!=null&&userBean.getDlist().size()>=1){
            //部门不为空的情况下遍历找出职位
            for (DeptBean deptBean : userBean.getDlist()) {
                if (deptBean.getPostids()!=null){
                    //职位不为空的情况下遍历找出
                    for (Long postids : deptBean.getPostids()) {
                        userMapper.addpost(userBean.getId(),postids,ismain);
                    }
                }
            }
        }

    }

    @Override
    public Set<String> getusermeunUrlsByid(UserBean gologin) {
      if (gologin!=null){
          Set<String> list = meunMapper.selectusermeunUrlsByid(gologin.getId());
          return list;
      }
      return null;
    }

    private void selectmenutodel(Long pid) {
        ids.add(pid);//将查询出来的id存入集合
        MeunBeanExample meunBeanExample = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = meunBeanExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<MeunBean> meunBeans = meunMapper.selectByExample(meunBeanExample);
        if (meunBeans!=null&&meunBeans.size()>=1) for (MeunBean meunBean : meunBeans) {
                selectmenutodel(meunBean.getId());//不为空的情况下再次调用原方法查询直到为空为止
            }
    }
}
