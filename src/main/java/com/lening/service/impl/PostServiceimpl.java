package com.lening.service.impl;

import com.lening.entity.MeunBean;
import com.lening.mapper.MeunMapper;
import com.lening.mapper.PostMapper;
import com.lening.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ð§¡ð§¡â¤
 *
 * @version 1.0
 * @authorï¼ çæ³¢æ³¢
 * @dateï¼ 2021-04-10 13:37
 */
@Service
public class PostServiceimpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Resource
    private MeunMapper meunMapper;


    @Override
    public List<MeunBean> selectmenuByid(Long postid) {
        //å¨æ¥èå
        List<MeunBean> meunBeans = meunMapper.selectByExample(null);
        //å½åèä½æ¥æçèå
        List<Long> longs = postMapper.selectmenuByid(postid);
        //å¤æ­å·²æçèåæ¯å¦ç­äºå¨æ¥èåï¼åæ¾
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
        //åå é¤ä¸­é´è¡¨çæ°æ®
        postMapper.delpostmeun(postid);
        if (ids!=null&&ids.length>=1){
            for (Long id : ids) {
                postMapper.savepostmeun(postid,id);
            }
        }
    }
}
