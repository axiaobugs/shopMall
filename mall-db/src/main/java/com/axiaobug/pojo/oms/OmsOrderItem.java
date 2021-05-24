package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 16:48
 */
@Data
@Entity
@Table (name = "oms_order_item")
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderItem implements Serializable {

	private static final long serialVersionUID =  3095452983429884746L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**订单id*/
   	@Column(name = "order_id" )
	private Integer orderId = null;

	/**订单编号*/
   	@Column(name = "order_sn" )
	private String orderSerialNum = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "product_pic" )
	private String productPic = null;

   	@Column(name = "product_name" )
	private String productName = null;

   	@Column(name = "product_brand" )
	private String productBrand = null;

   	@Column(name = "product_sn" )
	private String productSerialNum = null;

	/**
	 * sale price
	 * 销售价格
	 * */
   	@Column(name = "product_price" )
	private BigDecimal productPrice = null;

	/**
	 * Qty
	 * 购买数量
	 * */
   	@Column(name = "product_quantity" )
	private Integer productQuantity = null;

   	@Column(name = "product_sku_id" )
	private Integer productSkuId = null;

   	@Column(name = "product_sku_code" )
	private String productSkuCode = null;

   	@Column(name = "product_category_id" )
	private Integer productCategoryId = null;

   	@Column(name = "promotion_name" )
	private String promotionName = null;

	/**
	 * promotion amount
	 * 商品促销分解金额
	 * */
   	@Column(name = "promotion_amount" )
	private BigDecimal promotionAmount = null;

	/**
	 * coupon amount
	 * 优惠券优惠分解金额
	 * */
   	@Column(name = "coupon_amount" )
	private BigDecimal couponAmount = null;

	/**
	 * Credit amount to use
	 * 积分优惠分解金额
	 * */
   	@Column(name = "integration_amount" )
	private BigDecimal integrationAmount;

	/**
	 * real amount = sale_price - promotion_amount - coupon_amount - integration_amount
	 * 该商品经过优惠后的分解金额
	 * */
   	@Column(name = "real_amount" )
	private BigDecimal realAmount  = null;

   	@Column(name = "gift_integration" )
	private Integer giftIntegration;

   	@Column(name = "gift_growth" )
	private Integer giftGrowth = null;

	/**
	 * Sale specification
	 * Json [{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
	 * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
	 * */
   	@Column(name = "product_attr" )
	private String productAttr = null;

	@Override
	public String toString() {
		return "OmsOrderItem{" +
				"id=" + id +
				", orderId=" + orderId +
				", orderSerialNum='" + orderSerialNum + '\'' +
				", productId=" + productId +
				", productPic='" + productPic + '\'' +
				", productName='" + productName + '\'' +
				", productBrand='" + productBrand + '\'' +
				", productSerialNum='" + productSerialNum + '\'' +
				", productPrice=" + productPrice +
				", productQuantity=" + productQuantity +
				", productSkuId=" + productSkuId +
				", productSkuCode='" + productSkuCode + '\'' +
				", productCategoryId=" + productCategoryId +
				", promotionName='" + promotionName + '\'' +
				", promotionAmount=" + promotionAmount +
				", couponAmount=" + couponAmount +
				", integrationAmount=" + integrationAmount +
				", realAmount=" + realAmount +
				", giftIntegration=" + giftIntegration +
				", giftGrowth=" + giftGrowth +
				", productAttr='" + productAttr + '\'' +
				'}';
	}

	/****************************************************************************/
   	@ManyToOne
	@JoinColumn(name = "order_id",insertable = false,updatable = false)
	private OmsOrder omsOrder;

}
