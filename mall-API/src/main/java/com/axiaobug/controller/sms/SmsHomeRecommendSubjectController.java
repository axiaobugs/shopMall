package com.axiaobug.controller.sms;

import com.axiaobug.common.CommonMethod;
import com.axiaobug.common.CommonResult;
import com.axiaobug.pojo.sms.SmsHomeRecommendSubject;
import com.axiaobug.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Home Recommended Subject Management Controller
 * @author Yanxiao
 * @version 0.1.0
 * @date 06 2021
 */
@RestController
@Api(tags = "首页专题推荐管理")
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Resource
    private SmsHomeRecommendSubjectService recommendSubjectService;

    @Resource
    private CommonMethod commonMethod;

    @ApiOperation("添加首页推荐专题")
    @PostMapping(value = "/create")
    public CommonResult<Boolean> create(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) throws Exception {
        return commonMethod.response(recommendSubjectService.create(homeRecommendSubjectList));
    }

    @ApiOperation("修改推荐排序")
    @PatchMapping(value = "/update/sort/{id}")
    public CommonResult<Boolean> updateSort(@PathVariable Integer id, Integer sort) {
        return commonMethod.response(recommendSubjectService.updateSort(id,sort));
    }

    @ApiOperation("批量删除推荐")
    @DeleteMapping(value = "/delete")
    public CommonResult<Boolean> delete(@RequestParam("ids") List<Integer> ids) throws Exception {
        return commonMethod.response(recommendSubjectService.delete(ids));
    }

    @ApiOperation("批量修改推荐状态")
    @PatchMapping(value = "/update/recommendStatus")
    public CommonResult<Boolean> updateRecommendStatus(@RequestParam("ids") List<Integer> ids,
                                                       @RequestParam("status") Integer recommendStatus) throws NoSuchMethodException {
        return commonMethod.response(recommendSubjectService.updateRecommendStatus(ids,recommendStatus));
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum) {
        List<SmsHomeRecommendSubject> homeRecommendSubjectList = recommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        if (!homeRecommendSubjectList.isEmpty()){
            return CommonResult.success(homeRecommendSubjectList);
        }
        return CommonResult.failed();
    }


}
