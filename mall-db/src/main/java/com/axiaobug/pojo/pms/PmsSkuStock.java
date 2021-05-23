package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/23 10:37
 */
@Data
@Entity
@Table (name = "pms_sku_stock")
@NoArgsConstructor
@AllArgsConstructor
public class PmsSkuStock implements Serializable {

	private static final long serialVersionUID =  7492077510101209303L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

	/**
	 * SKU code
	 * sku编码
	 * */
   	@Column(name = "sku_code" )
	private String skuCode = "";

   	@Column(name = "price" )
	private BigDecimal price = null;

   	@Column(name = "stock" )
	private Integer stock = null;

	/**
	 * num to alert
	 * 预警库存
	 * */
   	@Column(name = "low_stock" )
	private Integer lowStock;

   	@Column(name = "pic" )
	private String pic = null;

	/**
	 * sold num
	 * 销量
	 * */
   	@Column(name = "sale" )
	private Integer sale = null;

	/**
	 * promote price
	 * 单品促销价格
	 * */
   	@Column(name = "promotion_price" )
	private BigDecimal promotionPrice = null;

	/**
	 * Product Hold
	 * 锁定库存
	 * */
   	@Column(name = "lock_stock" )
	private Integer lockStock = null;

	/**
	 * product specification in JSON
	 * 商品销售属性，json格式
	 * */
   	@Column(name = "sp_data" )
	private String spData = null;


   	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private PmsProduct pmsProduct;

	@Override
	public String toString() {
		return "PmsSkuStock{" +
				"id=" + id +
				", productId=" + productId +
				", skuCode='" + skuCode + '\'' +
				", price=" + price +
				", stock=" + stock +
				", lowStock=" + lowStock +
				", pic='" + pic + '\'' +
				", sale=" + sale +
				", promotionPrice=" + promotionPrice +
				", lockStock=" + lockStock +
				", spData='" + spData + '\'' +
				'}';
	}
}
