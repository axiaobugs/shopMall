package com.axiaobug.controller;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.cms.CmsSubject;
import com.axiaobug.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Discription: 商品专题管理Controller
 * Commodity subject controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@RestController
@Api(tags = "CmsSubjectController")
@RequestMapping("/subject")
public class CmsSubjectController {
    private final CmsSubjectService cmsSubjectService;

    @Autowired
    public CmsSubjectController(CmsSubjectService cmsSubjectService){
        this.cmsSubjectService=cmsSubjectService;
    }

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = this.cmsSubjectService.findAll();
        return CommonResult.success(subjectList);
    }

    @ApiOperation(value = "根据专题分类名称分页获取专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> pageableSubject(@RequestParam(value = "size") Integer pageSize,
                                                          @RequestParam(value = "page") Integer pageNum,
                                                          @RequestParam(value = "keyword",required = false) String keyword){
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        CmsSubject subject = new CmsSubject();
        subject.setCategoryName(keyword);
        Example<CmsSubject> example = Example.of(subject);
        Page<CmsSubject> subjectList = this.cmsSubjectService.findAll(example,pageable);
        List<CmsSubject> content = subjectList.getContent();
        return CommonResult.success(content);
    }

}
