package com.lening.controller;

import com.lening.entity.DeptBean;
import com.lening.service.DeptService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 🧡🧡❤
 *
 * @version 1.0
 * @author： 王波波
 * @date： 2021-04-10 13:38
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;
    @RequestMapping("/godeptpost")
    public DeptBean godeptpost(Long id){
        DeptBean godeptpost = deptService.godeptpost(id);
        return godeptpost;
    }

    @RequestMapping("/savepost")
    public void savepost(Long id,@RequestBody Long[] ids){
        deptService.savepost(id,ids);
    }
}
