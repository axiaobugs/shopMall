package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 21:01
 */
@Data
@Entity
@Table (name = "oms_order_setting")
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderSetting implements Serializable {

	private static final long serialVersionUID =  6999163438980860981L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * over time of flash sale close (min)
	 * 秒杀订单超时关闭时间(分)
	 * */
   	@Column(name = "flash_order_overtime" )
	private Integer flashOrderOverTime = null;

	/**
	 * over time of normal sale close (min)
	 * 正常订单超时时间(分)
	 * */
   	@Column(name = "normal_order_overtime" )
	private Integer normalOrderOverTime = null;

	/**
	 * the days of auto to confirm the Receipt
	 * 发货后自动确认收货时间（天）
	 * */
   	@Column(name = "confirm_overtime" )
	private Integer confirmOverTime = null;

	/**
	 * the days of can not apply for After-sales service if it is auto-confirm
	 * 自动完成交易时间，不能申请售后（天）
	 * */
   	@Column(name = "finish_overtime" )
	private Integer finishOverTime = null;

	/**
	 * the days of to giving good comment if there are no response
	 * 订单完成后自动好评时间（天）
	 * */
   	@Column(name = "comment_overtime" )
	private Integer commentOverTime = null;

}
