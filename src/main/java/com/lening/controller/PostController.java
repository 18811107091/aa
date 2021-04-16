package com.lening.controller;

import com.lening.entity.MeunBean;
import com.lening.service.PostService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 🧡🧡❤
 *
 * @version 1.0
 * @author： 王波波
 * @date： 2021-04-10 13:38
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostService postService;
    @RequestMapping("/selectmenuByid")
    public List<MeunBean> selectmenuByid(Long postid){
        List<MeunBean> selectmenuByid = postService.selectmenuByid(postid);
        return selectmenuByid;
    }

    @RequestMapping("/savepost")
    public void savepost(Long postid,@RequestBody Long[] ids){
        postService.savepost(postid, ids);
    }
}
