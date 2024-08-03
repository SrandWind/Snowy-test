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
package vip.xiaonuo.biz.modular.simple.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 简单业务添加参数
 *
 * @author wsk
 * @date  2024/08/02 15:16
 **/
@Getter
@Setter
public class BizSimpleAddParam {

    /** 名称 */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 编码 */
    @Schema(description = "编码")
    private String code;

    /** 状态 */
    @Schema(description = "状态")
    private Boolean state;

    /** 类型 */
    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "type不能为空")
    private String type;

    /** 所属组织 */
    @Schema(description = "所属组织")
    private String orgId;

    /** 负责人 */
    @Schema(description = "负责人")
    private String ponPer;

    /** 相关人 */
    @Schema(description = "相关人")
    private String relPer;

    /** 附件 */
    @Schema(description = "附件")
    private String enclosure;

    /** 备注 */
    @Schema(description = "备注")
    private String ps;

}
