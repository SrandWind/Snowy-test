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
package vip.xiaonuo.biz.modular.simple.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.simple.entity.BizSimple;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleAddParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleEditParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimpleIdParam;
import vip.xiaonuo.biz.modular.simple.param.BizSimplePageParam;

import java.util.List;

/**
 * 简单业务Service接口
 *
 * @author wsk
 * @date  2024/08/02 15:16
 **/
public interface BizSimpleService extends IService<BizSimple> {

    /**
     * 获取简单业务分页
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    Page<BizSimple> page(BizSimplePageParam bizSimplePageParam);

    /**
     * 添加简单业务
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    void add(BizSimpleAddParam bizSimpleAddParam);

    /**
     * 编辑简单业务
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    void edit(BizSimpleEditParam bizSimpleEditParam);

    /**
     * 删除简单业务
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    void delete(List<BizSimpleIdParam> bizSimpleIdParamList);

    /**
     * 获取简单业务详情
     *
     * @author wsk
     * @date  2024/08/02 15:16
     */
    BizSimple detail(BizSimpleIdParam bizSimpleIdParam);

    /**
     * 获取简单业务详情
     *
     * @author wsk
     * @date  2024/08/02 15:16
     **/
    BizSimple queryEntity(String id);
}
