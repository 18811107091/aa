package com.lening.mapper;

import com.lening.entity.DeptBean;
import com.lening.entity.UserBean;
import com.lening.entity.UserBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserBeanExample example);

    int deleteByExample(UserBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    List<UserBean> selectByExample(UserBeanExample example);

    UserBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByExample(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    List<UserBean> savedeptbyid(Long id);

    void deldept(@Param("userid") Long userid);

    void delpost(@Param("userid") Long userid);

    void adddept(@Param("userid") Long userid,@Param("deptid") Long deptid);

    List<UserBean> getLogin(UserBean userBean);

    void deleteByuserid(Long id);

    void addpost(@Param("userid") Long userid, @Param("postid") Long postid,@Param("ismain") Integer ismain);

    List<DeptBean> getUserDeptById(Long id);
}
