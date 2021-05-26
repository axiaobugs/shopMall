package com.axiaobug.controller;

import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.cms.CmsPrefrenceArea;
import com.axiaobug.pojo.cms.CmsSubject;
import com.axiaobug.service.CmsPrefrenceAreaService;
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
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@RestController
@Api(tags = "CmsPrefrenceAreaController")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaServiceController {

    private final CmsPrefrenceAreaService cmsPrefrenceAreaService;

    @Autowired
    public CmsPrefrenceAreaServiceController(CmsPrefrenceAreaService cmsPrefrenceAreaService){
        this.cmsPrefrenceAreaService = cmsPrefrenceAreaService;
    }

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> prefrenceAreaList = cmsPrefrenceAreaService.findAll();
        return CommonResult.success(prefrenceAreaList);
    }

    @ApiOperation(value = "根据优选分页获取专题(可以添加关键字搜索)")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsPrefrenceArea>> pageableSubject(@RequestParam(value = "size") Integer pageSize,
                                                                @RequestParam(value = "page") Integer pageNum,
                                                                @RequestParam(value = "keyword",required = false) String keyword){
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        CmsPrefrenceArea prefrenceArea = new CmsPrefrenceArea();
        prefrenceArea.setName(keyword);
        Example<CmsPrefrenceArea> example = Example.of(prefrenceArea);
        Page<CmsPrefrenceArea> subjectList = this.cmsPrefrenceAreaService.findAll(example,pageable);
        List<CmsPrefrenceArea> content = subjectList.getContent();
        return CommonResult.success(content);
    }
}
