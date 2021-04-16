package com.lening.service.impl;

import com.lening.entity.DeptBean;
import com.lening.entity.PostBean;
import com.lening.mapper.DeptMapper;
import com.lening.mapper.PostMapper;
import com.lening.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 🧡🧡❤
 *
 * @version 1.0
 * @author： 王波波
 * @date： 2021-04-10 13:37
 */
@Service
public class DeptServiceimpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private PostMapper postMapper;


    @Override
    public DeptBean godeptpost(Long id) {
        DeptBean deptBean = deptMapper.selectByPrimaryKey(id);
        List<PostBean> deptBeans = postMapper.selectByExample(null);
        List<DeptBean> bydeptidsave = deptMapper.bydeptidsave(id);
        deptBean.setDept(deptBeans);
        deptBean.setDepts(bydeptidsave);
        System.out.println(deptBean.toString());
        return deptBean;
    }

    @Override
    public void savepost(Long id, Long[] ids) {
        deptMapper.deletedept(id);
        for (Long aLong : ids) {
            deptMapper.adddept(id,aLong);
        }
    }
}
