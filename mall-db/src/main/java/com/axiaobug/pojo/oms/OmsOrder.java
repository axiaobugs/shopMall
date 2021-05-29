package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/23 17:22
 */
@Data
@Entity
@Table (name = "oms_order")
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrder implements Serializable {

	private static final long serialVersionUID =  7845211415612725449L;

	/**订单id*/
   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	/**
	 * TODO: 是否要做member的外键
	 * 会员ID
	 * */
   	@Column(name = "member_id" )
	private Integer memberId = 0;

   	@Column(name = "coupon_id" )
	private Integer couponId = null;

	/**
	 * order number
	 * 订单编号
	 * */
   	@Column(name = "order_sn" )
	private String orderSerialNum = null;

	/**
	 *
	 * 提交时间
	 * */
   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;


   	@Column(name = "member_username" )
	private String memberUsername = null;


   	/**
	 * total= pay + feight + tax
	 * */
   	@Column(name = "total_amount" )
	private BigDecimal totalAmount = null;

	/**
	 * paid amount (paid amount + promote + credit + coupon)
	 * 应付金额（实际支付金额）
	 * */
   	@Column(name = "pay_amount" )
	private BigDecimal payAmount = null;

	/**
	 * freight amount
	 * 运费金额
	 * */
   	@Column(name = "freight_amount" )
	private BigDecimal freightAmount = null;

	/**
	 * promote reduction amount
	 * 促销优化金额（促销价、满减、阶梯价）
	 * */
   	@Column(name = "promotion_amount" )
	private BigDecimal promotionAmount = null;

	/**
	 * use credit to reduction
	 * 积分抵扣金额
	 * */
   	@Column(name = "integration_amount" )
	private BigDecimal integrationAmount =null;

	/**
	 * coupon amount
	 * 优惠券抵扣金额
	 * */
   	@Column(name = "coupon_amount" )
	private BigDecimal couponAmount = null;

	/**
	 * discount amount approval by admin
	 * 管理员后台调整订单使用的折扣金额
	 * */
   	@Column(name = "discount_amount" )
	private BigDecimal discountAmount = null;

	/**
	 * pay type
	 * 0:no pay 1: Alipay 2: WeChat Pay
	 * 支付方式：0->未支付；1->支付宝；2->微信*/
   	@Column(name = "pay_type" )
	private Integer payType = null;

	/**
	 * order made from
	 * 0: PC  1: mobile APP
	 * 订单来源：0->PC订单；1->app订单*/
   	@Column(name = "source_type" )
	private Integer sourceType = null;

	/**
	 * 0: Pending payment
	 * 1: Pending dispatch
	 * 2: Dispatched
	 * 3: Done
	 * 4: Order Close
	 * 5: Invalid order
	 * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单*/
	private Integer status = null;

	/**订单类型：
	 * 0: Normal Order
	 * 1: Flash Sale Order
	 * 0->正常订单；1->秒杀订单
	 * */
   	@Column(name = "order_type" )
	private Integer orderType = null;

	/**
	 * freight Company
	 * 物流公司(配送方式)
	 * */
   	@Column(name = "delivery_company" )
	private String deliveryCompany = null;

	/**
	 * freight order num
	 * 物流单号
	 * */
   	@Column(name = "delivery_sn" )
	private String deliverySerialNum = null;

	/**
	 * The days of the order will be auto confirmed after it made.
	 * 自动确认时间（天）*/
   	@Column(name = "auto_confirm_day" )
	private Integer autoConfirmday = null;

	/**
	 * obtain the credit from this order
	 * 可以获得的积分
	 * */
   	@Column(name = "integration" )
	private Integer integration = null;

	/**
	 * Exp form this order
	 * 可以活动的成长值*/
   	@Column(name = "growth" )
	private Integer growth = null;

	/**
	 * promotion info
	 * 活动信息*/
   	@Column(name = "promotion_info" )
	private String promotionInfo = null;

	/**
	 * 0:No invoice 1: e-invoice 2: paper invoice
	 * 发票类型：0->不开发票；1->电子发票；2->纸质发票*/
   	@Column(name = "bill_type" )
	private Integer billType = null;

	/**
	 * invoice title
	 * 发票抬头
	 * */
   	@Column(name = "bill_header" )
	private String billHeader = null;

	/**
	 * invoice content
	 * 发票内容
	 * */
   	@Column(name = "bill_content" )
	private String billContent = null;

	/**
	 * invoice Phone
	 * 收票人电话*/
   	@Column(name = "bill_receiver_phone" )
	private String billReceiverPhone = null;

	/**
	 * invoice Email
	 * 收票人邮箱*/
   	@Column(name = "bill_receiver_email" )
	private String billReceiverEmail = null;

	/**
	 * consignee name
	 * 收货人姓名*/
   	@Column(name = "receiver_name" )
	private String receiverName = "";

	/**
	 * consignee phone
	 * 收货人电话*/
   	@Column(name = "receiver_phone" )
	private String receiverPhone = "";

	/**
	 * consignee post code
	 * 收货人邮编
	 * */
   	@Column(name = "receiver_post_code" )
	private String receiverPostCode = null;

	/**
	 * state
	 * 省份/直辖市*/
   	@Column(name = "receiver_province" )
	private String receiverProvince = null;

	/**
	 * city
	 * 城市*/
   	@Column(name = "receiver_city" )
	private String receiverCity = null;

	/**
	 * suburb
	 * 区*/
   	@Column(name = "receiver_region" )
	private String receiverSuburb = null;

	/**
	 * address
	 * 详细地址
	 * */
   	@Column(name = "receiver_detail_address" )
	private String receiverAddress = null;

	/**
	 * order note
	 * 订单备注*/
	private String note = null;

	/**
	 * 0: unknown 1: confirmed
	 * 确认收货状态：0->未确认；1->已确认*/
   	@Column(name = "confirm_status" )
	private Integer confirmStatus = null;

	/**
	 * 0: undelete 1: deleted
	 * 删除状态：0->未删除；1->已删除*/
   	@Column(name = "delete_status" )
	private Integer deleteStatus = 0;

	/**
	 *  used of credit when confirmed the order
	 * 下单时使用的积分*/
   	@Column(name = "use_integration" )
	private Integer useIntegration = null;


   	@Column(name = "payment_time" )
	private Date paymentTime = null;

   	@Column(name = "delivery_time" )
	private Date deliveryTime = null;

   	@Column(name = "receive_time" )
	private Date receiveTime = null;

   	@Column(name = "comment_time" )
	private Date commentTime = null;

   	@Column(name = "modify_time" )
	private Date modifyTime = null;

	@Override
	public String toString() {
		return "OmsOrder{" +
				"id=" + id +
				", memberId=" + memberId +
				", couponId=" + couponId +
				", orderSerialNum='" + orderSerialNum + '\'' +
				", createTime=" + createTime +
				", memberUsername='" + memberUsername + '\'' +
				", totalAmount=" + totalAmount +
				", payAmount=" + payAmount +
				", freightAmount=" + freightAmount +
				", promotionAmount=" + promotionAmount +
				", integrationAmount=" + integrationAmount +
				", couponAmount=" + couponAmount +
				", discountAmount=" + discountAmount +
				", payType=" + payType +
				", sourceType=" + sourceType +
				", status=" + status +
				", orderType=" + orderType +
				", deliveryCompany='" + deliveryCompany + '\'' +
				", deliverySerialNum='" + deliverySerialNum + '\'' +
				", autoConfirmday=" + autoConfirmday +
				", integration=" + integration +
				", growth=" + growth +
				", promotionInfo='" + promotionInfo + '\'' +
				", billType=" + billType +
				", billHeader='" + billHeader + '\'' +
				", billContent='" + billContent + '\'' +
				", billReceiverPhone='" + billReceiverPhone + '\'' +
				", billReceiverEmail='" + billReceiverEmail + '\'' +
				", receiverName='" + receiverName + '\'' +
				", receiverPhone='" + receiverPhone + '\'' +
				", receiverPostCode='" + receiverPostCode + '\'' +
				", receiverProvince='" + receiverProvince + '\'' +
				", receiverCity='" + receiverCity + '\'' +
				", receiverSuburb='" + receiverSuburb + '\'' +
				", receiverAddress='" + receiverAddress + '\'' +
				", note='" + note + '\'' +
				", confirmStatus=" + confirmStatus +
				", deleteStatus=" + deleteStatus +
				", useIntegration=" + useIntegration +
				", paymentTime=" + paymentTime +
				", deliveryTime=" + deliveryTime +
				", receiveTime=" + receiveTime +
				", commentTime=" + commentTime +
				", modifyTime=" + modifyTime +
				'}';
	}

	/***********************************************/

//	@OneToMany(mappedBy = "omsOrder")
//	@JsonIgnore
//	private List<OmsOrderItem> orderItems = new ArrayList<>();
//
//	@OneToMany(mappedBy = "omsOrder")
//	@JsonIgnore
//	private List<OmsOrderOperateHistory> orderOperateHistories = new ArrayList<>();
//
//	@OneToMany(mappedBy = "omsOrder")
//	@JsonIgnore
//	private List<OmsOrderReturnApply> omsOrderReturnApplies = new ArrayList<>();
}
