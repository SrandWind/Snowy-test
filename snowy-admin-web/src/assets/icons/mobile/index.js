/**
 *  Copyright [2022] [https://www.xiaonuo.vip]
 *	Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *	1.请不要删除和修改根目录下的LICENSE文件。
 *	2.请不要删除和修改Snowy源码头部的版权声明。
 *	3.本项目代码可免费商业使用，商业使用请保留源码和相关描述文件的项目出处，作者声明等。
 *	4.分发源码时候，请注明软件出处 https://www.xiaonuo.vip
 *	5.不可二次分发开源参与同类竞品，如有想法可联系团队xiaonuobase@qq.com商议合作。
 *	6.若您的项目无法满足以上几点，需要更多功能代码，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
import './line/iconfont.css'
import lineJsonData from './line/iconfont.json'

import './filled/iconfont.css'
import filledJsonData from './filled/iconfont.json'

import './square/iconfont.css'
import squareJsonData from './square/iconfont.json'

import './ring/iconfont.css'
import ringJsonData from './ring/iconfont.json'
export default {
	icons: [
		{
			name: '基础',
			key: 'default',
			iconItem: [
				{
					name: '线框风格',
					key: 'default',
					item: lineJsonData.glyphs
				},
				{
					name: '实底风格',
					key: 'filled',
					item: filledJsonData.glyphs
				},
			]
		},
		{
			name: '其他',
			key: 'other',
			iconItem: [
				{
					name: '圆形风格',
					key: 'default',
					item: squareJsonData.glyphs
				},
				{
					name: '方形风格',
					key: 'ring',
					item: ringJsonData.glyphs
				},
			]
		}
	]
}