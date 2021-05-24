package com.axiaobug.pojo.ums;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/23 15:42
 */
@Data
@Entity
@Table (name = "ums_integration_consume_setting")
@NoArgsConstructor
@AllArgsConstructor
public class UmsIntegrationConsumeSetting implements Serializable {

	private static final long serialVersionUID =  7789610884948036069L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**每一元需要抵扣的积分数量*/
   	@Column(name = "deduction_per_amount" )
	private Integer deductionPerAmount;

	/**
	 * 每笔订单最高抵用百分比
	 * */
   	@Column(name = "max_percent_per_order" )
	private Integer maxPercentPerOrder;

	/**
	 * 每次使用积分最小单位100
	 * */
   	@Column(name = "use_unit" )
	private Integer useUnit;

	/**
	 *
	 * 是否可以和优惠券同用；0->不可以；1->可以
	 * */
   	@Column(name = "coupon_status" )
	private Integer couponStatus;

}
