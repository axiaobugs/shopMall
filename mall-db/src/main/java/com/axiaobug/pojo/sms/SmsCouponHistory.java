package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 优惠券历史记录表
 * 用于存储会员领取及使用优惠券的记录，当会员领取到优惠券时，会产生一条优惠券的记录
 * Coupon history table
 * It is used to store the record of members receiving and using coupons.
 * When the member receives the coupons, a record of the coupons will be generated
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 14:42
 */
@Data
@Entity
@Table (name = "sms_coupon_history")
@NoArgsConstructor
@AllArgsConstructor
public class SmsCouponHistory implements Serializable {

	private static final long serialVersionUID =  5712898725012687998L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "coupon_id" )
	private Integer couponId = null;

   	@Column(name = "member_id" )
	private Integer memberId = null;

   	@Column(name = "coupon_code" )
	private String couponCode = null;

   	@Column(name = "member_nickname" )
	private String memberNickname = null;

	/**
	 * 0: gift given by system back end
	 * 1: by user self
	 * 获取类型：0->后台赠送；1->主动获取
	 * */
   	@Column(name = "get_type" )
	private Integer getType = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	/**
	 * 0:no use 1: used 2: Expired
	 * 使用状态：0->未使用；1->已使用；2->已过期
	 * */
   	@Column(name = "use_status" )
	private Integer useStatus = null;

   	@Column(name = "use_time" )
	private Date useTime = null;

   	@Column(name = "order_id" )
	private Integer orderId = null;

   	@Column(name = "order_sn" )
	private String orderSerialNum = null;

	@Override
	public String toString() {
		return "SmsCouponHistory{" +
				"id=" + id +
				", couponId=" + couponId +
				", memberId=" + memberId +
				", couponCode='" + couponCode + '\'' +
				", memberNickname='" + memberNickname + '\'' +
				", getType=" + getType +
				", createTime=" + createTime +
				", useStatus=" + useStatus +
				", useTime=" + useTime +
				", orderId=" + orderId +
				", orderSerialNum='" + orderSerialNum + '\'' +
				'}';
	}

	/*********************************************************************/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmsCoupon smsCoupon;
}
