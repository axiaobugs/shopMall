package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 20:52
 */
@Data
@Entity
@Table (name = "oms_cart_item")
@NoArgsConstructor
@AllArgsConstructor
public class OmsCartItem implements Serializable {

	private static final long serialVersionUID =  6830842437589941845L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "product_sku_id" )
	private Integer productSkuId = null;

   	@Column(name = "member_id" )
	private Integer memberId = null;

	private Integer quantity = null;

	/**
	 * price which added to the cart
	 * 添加到购物车的价格
	 * */
	private BigDecimal price;

	private String productPic = null;

   	@Column(name = "product_name" )
	private String productName = null;

	/**
	 * Subtitle
	 * 商品副标题（卖点）
	 * */
   	@Column(name = "product_sub_title" )
	private String productSubtitle = null;

   	@Column(name = "product_sku_code" )
	private String productSkuCodeSubtitle = null;

   	@Column(name = "member_nickname" )
	private String memberNicknameSubtitle = null;

   	@Column(name = "create_date" )
	private Date createDate = null;

   	@Column(name = "modify_date" )
	private Date modifyDate = null;

   	@Column(name = "delete_status" )
	private Integer deleteStatus = null;

   	@Column(name = "product_category_id" )
	private Integer productCategoryId = null;

   	@Column(name = "product_brand" )
	private String productBrand = null;

   	@Column(name = "product_sn" )
	private String productSerialNum = null;

	/**
	 * Json format
	 * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
	 * */
   	@Column(name = "product_attr" )
	private String productAttr = null;

	@Override
	public String toString() {
		return "OmsCartItem{" +
				"id=" + id +
				", productId=" + productId +
				", productSkuId=" + productSkuId +
				", memberId=" + memberId +
				", quantity=" + quantity +
				", price=" + price +
				", productPic='" + productPic + '\'' +
				", productName='" + productName + '\'' +
				", productSubtitle='" + productSubtitle + '\'' +
				", productSkuCodeSubtitle='" + productSkuCodeSubtitle + '\'' +
				", memberNicknameSubtitle='" + memberNicknameSubtitle + '\'' +
				", createDate=" + createDate +
				", modifyDate=" + modifyDate +
				", deleteStatus=" + deleteStatus +
				", productCategoryId=" + productCategoryId +
				", productBrand='" + productBrand + '\'' +
				", productSerialNum='" + productSerialNum + '\'' +
				", productAttr='" + productAttr + '\'' +
				'}';
	}
}
