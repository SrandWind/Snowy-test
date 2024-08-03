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
package vip.xiaonuo.biz.modular.simple.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.simple.entity.BizSimple;
import vip.xiaonuo.biz.modular.simple.mapper.BizSimpleMapper;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleAddParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleEditParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleIdParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimplePageParam;
import vip.xiaonuo.biz.modular.simple.service.BizSimpleService;

import java.util.List;

/**
 * 简单业务Service接口实现类
 *
 * @author wsk
 * @date  2024/08/02 15:16
 **/
@Service
public class BizSimpleServiceImpl extends ServiceImpl<BizSimpleMapper, BizSimple> implements BizSimpleService {

    @Override
    public Page<BizSimple> page(BizSimplePageParam bizSimplePageParam) {
        QueryWrapper<BizSimple> queryWrapper = new QueryWrapper<BizSimple>().checkSqlInjection();
        if(ObjectUtil.isNotEmpty(bizSimplePageParam.getName())) {
            queryWrapper.lambda().like(BizSimple::getName, bizSimplePageParam.getName());
        }
        if(ObjectUtil.isNotEmpty(bizSimplePageParam.getCode())) {
            queryWrapper.lambda().like(BizSimple::getCode, bizSimplePageParam.getCode());
        }
        if(ObjectUtil.isNotEmpty(bizSimplePageParam.getState())) {
            queryWrapper.lambda().eq(BizSimple::getState, bizSimplePageParam.getState());
        }
        if(ObjectUtil.isNotEmpty(bizSimplePageParam.getType())) {
            queryWrapper.lambda().eq(BizSimple::getType, bizSimplePageParam.getType());
        }
        if(ObjectUtil.isNotEmpty(bizSimplePageParam.getPonPer())) {
            queryWrapper.lambda().eq(BizSimple::getPonPer, bizSimplePageParam.getPonPer());
        }
        if(ObjectUtil.isNotEmpty(bizSimplePageParam.getPs())) {
            queryWrapper.lambda().like(BizSimple::getPs, bizSimplePageParam.getPs());
        }
        if(ObjectUtil.isAllNotEmpty(bizSimplePageParam.getSortField(), bizSimplePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizSimplePageParam.getSortOrder());
            queryWrapper.orderBy(true, bizSimplePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizSimplePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizSimple::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizSimpleAddParam bizSimpleAddParam) {
        BizSimple bizSimple = BeanUtil.toBean(bizSimpleAddParam, BizSimple.class);
        this.save(bizSimple);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizSimpleEditParam bizSimpleEditParam) {
        BizSimple bizSimple = this.queryEntity(bizSimpleEditParam.getId());
        BeanUtil.copyProperties(bizSimpleEditParam, bizSimple);
        this.updateById(bizSimple);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizSimpleIdParam> bizSimpleIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizSimpleIdParamList, BizSimpleIdParam::getId));
    }

    @Override
    public BizSimple detail(BizSimpleIdParam bizSimpleIdParam) {
        return this.queryEntity(bizSimpleIdParam.getId());
    }

    @Override
    public BizSimple queryEntity(String id) {
        BizSimple bizSimple = this.getById(id);
        if(ObjectUtil.isEmpty(bizSimple)) {
            throw new CommonException("简单业务不存在，id值为：{}", id);
        }
        return bizSimple;
    }
}
