package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: 优惠券和商品的关系表
 * 用于存储优惠券与商品的关系，当优惠券的使用类型为指定商品时，优惠券与商品需要建立关系。
 * Relationship between coupons and products table
 * It is used to store the relationship between the coupon and the product.
 * When the use type of the coupon is a designated product, the relationship between
 * the coupon and the product needs to be established.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 14:57
 */
@Data
@Entity
@Table (name = "sms_coupon_product_relation")
@NoArgsConstructor
@AllArgsConstructor
public class SmsCouponProductRelation implements Serializable {

	private static final long serialVersionUID =  1552216704860987679L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "coupon_id" )
	private Integer couponId = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "product_name" )
	private String productName = null;

   	@Column(name = "product_sn" )
	private String productSerialNum = null;

	@Override
	public String toString() {
		return "SmsCouponProductRelation{" +
				"id=" + id +
				", couponId=" + couponId +
				", productId=" + productId +
				", productName='" + productName + '\'' +
				", productSerialNum='" + productSerialNum + '\'' +
				'}';
	}

	/*********************************************************************************/

	@ManyToOne
	@JoinColumn(name = "coupon_id",insertable = false,updatable = false)
	private SmsCoupon smsCoupon;
}
