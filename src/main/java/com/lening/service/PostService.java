package com.lening.service;

import com.lening.entity.MeunBean;

import java.util.List;

/**
 * 🧡🧡❤
 *
 * @version 1.0
 * @author： 王波波
 * @date： 2021-04-10 13:37
 */
public interface PostService {
    List<MeunBean> selectmenuByid(Long postid);
    void savepost(Long postid, Long[] ids);
}
