/*
 * Copyright [2022] [https://www.xiaonuo.vip]
 *
 * Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改Snowy源码头部的版权声明。
 * 3.本项目代码可免费商业使用，商业使用请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 https://www.xiaonuo.vip
 * 5.不可二次分发开源参与同类竞品，如有想法可联系团队xiaonuobase@qq.com商议合作。
 * 6.若您的项目无法满足以上几点，需要更多功能代码，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package vip.xiaonuo.biz.modular.simple.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.biz.modular.simple.entity.BizSimple;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleAddParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleEditParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleIdParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimplePageParam;
import vip.xiaonuo.biz.modular.simple.service.BizSimpleService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 简单业务控制器
 *
 * @author wsk
 * @date  2024/08/02 15:16
 */
@Tag(name = "简单业务控制器")
@RestController
@Validated
public class BizSimpleController {

    @Resource
    private BizSimpleService bizSimpleService;

    /**
     * 获取简单业务分页
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    @Operation(summary = "获取简单业务分页")
    @SaCheckPermission("/biz/simple/page")
    @GetMapping("/biz/simple/page")
    public CommonResult<Page<BizSimple>> page(BizSimplePageParam bizSimplePageParam) {
        return CommonResult.data(bizSimpleService.page(bizSimplePageParam));
    }

    /**
     * 添加简单业务
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    @Operation(summary = "添加简单业务")
    @CommonLog("添加简单业务")
    @SaCheckPermission("/biz/simple/add")
    @PostMapping("/biz/simple/add")
    public CommonResult<String> add(@RequestBody @Valid BizSimpleAddParam bizSimpleAddParam) {
        bizSimpleService.add(bizSimpleAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑简单业务
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    @Operation(summary = "编辑简单业务")
    @CommonLog("编辑简单业务")
    @SaCheckPermission("/biz/simple/edit")
    @PostMapping("/biz/simple/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizSimpleEditParam bizSimpleEditParam) {
        bizSimpleService.edit(bizSimpleEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除简单业务
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    @Operation(summary = "删除简单业务")
    @CommonLog("删除简单业务")
    @SaCheckPermission("/biz/simple/delete")
    @PostMapping("/biz/simple/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   List<BizSimpleIdParam> bizSimpleIdParamList) {
        bizSimpleService.delete(bizSimpleIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取简单业务详情
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    @Operation(summary = "获取简单业务详情")
    @SaCheckPermission("/biz/simple/detail")
    @GetMapping("/biz/simple/detail")
    public CommonResult<BizSimple> detail(@Valid BizSimpleIdParam bizSimpleIdParam) {
        return CommonResult.data(bizSimpleService.detail(bizSimpleIdParam));
    }
}
