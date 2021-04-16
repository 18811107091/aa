package com.lening.service.impl;

import com.lening.entity.MeunBean;
import com.lening.mapper.MeunMapper;
import com.lening.mapper.PostMapper;
import com.lening.service.PostService;
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
public class PostServiceimpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Resource
    private MeunMapper meunMapper;


    @Override
    public List<MeunBean> selectmenuByid(Long postid) {
        //全查菜单
        List<MeunBean> meunBeans = meunMapper.selectByExample(null);
        //当前职位拥有的菜单
        List<Long> longs = postMapper.selectmenuByid(postid);
        //判断已有的菜单是否等于全查菜单，回显
        if (longs!=null&&longs.size()>=1){
            for (Long aLong : longs) {
                for (MeunBean meunBean : meunBeans) {
                    if (aLong.equals(meunBean.getId())){
                        meunBean.setChecked(true);
                        break;
                    }
                }
            }
        }
        return meunBeans;
    }

    @Override
    public void savepost(Long postid, Long[] ids) {
        //先删除中间表的数据
        postMapper.delpostmeun(postid);
        if (ids!=null&&ids.length>=1){
            for (Long id : ids) {
                postMapper.savepostmeun(postid,id);
            }
        }
    }
}
