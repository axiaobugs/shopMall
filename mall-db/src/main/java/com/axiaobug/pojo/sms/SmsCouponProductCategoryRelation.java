package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: 优惠券和商品分类关系表
 * 用于存储优惠券与商品分类的关系，当优惠券的使用类型为指定分类时，优惠券与商品分类需要建立关系。
 * Coupon and product classification relationship table
 * It is used to store the relationship between coupons and product categories.
 * When the use type of coupons is a designated category,
 * a relationship needs to be established between coupons and product categories.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 15:06
 */
@Data
@Entity
@Table (name = "sms_coupon_product_category_relation")
@NoArgsConstructor
@AllArgsConstructor
public class SmsCouponProductCategoryRelation implements Serializable {

	private static final long serialVersionUID =  6841471876636278499L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "coupon_id" )
	private Integer couponId = null;

   	@Column(name = "product_category_id" )
	private Integer productCategoryId = null;

   	@Column(name = "product_category_name" )
	private String productCategoryName;

   	@Column(name = "parent_category_name" )
	private String parentCategoryName = null;

	@Override
	public String toString() {
		return "SmsCouponProductCategoryRelation{" +
				"id=" + id +
				", couponId=" + couponId +
				", productCategoryId=" + productCategoryId +
				", productCategoryName='" + productCategoryName + '\'' +
				", parentCategoryName='" + parentCategoryName + '\'' +
				'}';
	}

	/**********************************************************************/

	@ManyToOne
	@JoinColumn(name = "coupon_id",insertable = false,updatable = false)
	private SmsCoupon smsCoupon;
}
