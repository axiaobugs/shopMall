package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/21 12:44
 */
@Data
@Entity
@Table (name = "pms_product")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProduct implements Serializable {

	private static final long serialVersionUID =  4312377958177817138L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "brand_id" )
	private Integer brandId = 0;

   	@Column(name = "product_category_id" )
	private Integer productCategoryId = 0;

   	@Column(name = "feight_template_id" )
	private Integer feightTemplateId = 0;

   	@Column(name = "product_attribute_category_id" )
	private Integer productAttributeCategoryId = 0;

	private String name = "";

	private String pic = "";

	/**货号*/
   	@Column(name = "product_sn" )
	private String productSerialNumber = "";

	/**
	 * 0: no 1: deleted
	 * 删除状态：0->未删除；1->已删除
	 * */
   	@Column(name = "delete_status" )
	private Integer deleteStatus = 0;

	/**
	 * 0: out of stock 1 in stock
	 * 上架状态：0->下架；1->上架
	 * */
   	@Column(name = "publish_status" )
	private Integer publishStatus = 1;

	/**
	 * 0: normal  1:New release
	 * 新品状态:0->不是新品；1->新品
	 * */
   	@Column(name = "new_status" )
	private Integer newStatus = 0;

	/**
	 * 0: normal 1: promote
	 * 推荐状态；0->不推荐；1->推荐
	 * */
   	@Column(name = "recommand_status" )
	private Integer recommendStatus = 0;

	/**
	 * 0: no verify 1:verified
	 * 审核状态：0->未审核；1->审核通过
	 * */
   	@Column(name = "verify_status" )
	private Integer verifyStatus = 0;

	/**排序*/
	private Integer sort;

	/**
	 * Sale num
	 * 销量
	 * */
   	@Column(name = "sale" )
	private Integer sale = 0;

   	@Column(scale = 2)
	private BigDecimal price = BigDecimal.valueOf(0);

	/**
	 * promote price
	 * 促销价格
	 * */
   	@Column(name = "promotion_price",scale = 2)
	private BigDecimal promotionPrice = BigDecimal.valueOf(0);

	/**
	 * Exp
	 * 赠送的成长值
	 * */
   	@Column(name = "gift_growth" )
	private Integer giftGrowth = 0;

	/**
	 * credit
	 * 赠送的积分
	 * */
   	@Column(name = "gift_point" )
	private Integer giftCredit = 0;

	/**
	 * limit of credit
	 * 限制使用的积分数
	 * */
   	@Column(name = "use_point_limit" )
	private Integer usePointLimit = 0;

   	@Column(name = "sub_title" )
	private String subtitle = "";

   	@Column(name = "description" )
	private String description = "";

	/**
	 * market price which for calculate the discount
	 *
	 * 市场价
	 * */
   	@Column(name = "original_price", scale = 2)
	private BigDecimal originalPrice = BigDecimal.valueOf(0);

	private Integer stock = 10;

	/**
	 * Alert when stock lower than this num
	 * 库存预警值
	 * */
   	@Column(name = "low_stock" )
	private Integer lowStock = 2;

	/**
	 *
	 * 单位
	 * */
	private String unit = "";

	/**
	 * weight (gram)
	 * 商品重量，默认为克
	 * */
   	@Column(name = "weight" )
	private BigDecimal weight = BigDecimal.valueOf(10);

	/**
	 * 0:No 1:Yes
	 * 是否为预告商品：0->不是；1->是
	 * */
   	@Column(name = "preview_status" )
	private Integer previewStatus = 0;

	/**
	 * 1: Free to return 2: Refund 3: Free Shipping
	 * like "1,2,3"
	 * 以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
	 * */
   	@Column(name = "service_ids" )
	private String serviceIds = "";

	private String keywords = "";

	private String note = "";

	/**
	 * limit 5 pic address = "add1,add2,add3,...."
	 * 画册图片，连产品图片限制为5张，以逗号分割
	 * */
   	@Column(name = "album_pics" )
	private String albumPics = "";

   	@Column(name = "detail_title" )
	private String detailTitle = "";

   	@Column(name = "detail_desc" )
	private String detailDescription = "";

   	@Column(name = "detail_html" )
	private String detailHtml = "";

	/**
	 * mobile html
	 * 移动端网页详情
	 * */
   	@Column(name = "detail_mobile_html" )
	private String detailMobileHtml = "";

	/**
	 * promotion start time
	 * 促销开始时间
	 * */
   	@Column(name = "promotion_start_time" )
	private Date promotionStartTime = null;

	/**
	 * promotion end time
	 * 促销结束时间
	 * */
   	@Column(name = "promotion_end_time" )
	private Date promotionEndTime = null;

	/**
	 * limit of sale num in promotion
	 * 活动限购数量
	 * */
   	@Column(name = "promotion_per_limit" )
	private Integer promotionPerLimit = 0;

	/**
	 * 0: normal 1: promote price 2: member price 3: bulk purchases 4: Further reductions  5:flash sale
	 * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购*/
   	@Column(name = "promotion_type" )
	private Integer promotionType = 0;

   	@Column(name = "brand_name" )
	private String brandName = "";

	/**
	 * 商品分类名称
	 * */
   	@Column(name = "product_category_name" )
	private String productCategoryName = "";

	@Override
	public String toString() {
		return "PmsProduct{" +
				"id=" + id +
				", brandId=" + brandId +
				", productCategoryId=" + productCategoryId +
				", feightTemplateId=" + feightTemplateId +
				", productAttributeCategoryId=" + productAttributeCategoryId +
				", name='" + name + '\'' +
				", pic='" + pic + '\'' +
				", productSerialNumber='" + productSerialNumber + '\'' +
				", deleteStatus=" + deleteStatus +
				", publishStatus=" + publishStatus +
				", newStatus=" + newStatus +
				", recommendStatus=" + recommendStatus +
				", verifyStatus=" + verifyStatus +
				", sort=" + sort +
				", sale=" + sale +
				", price=" + price +
				", promotionPrice=" + promotionPrice +
				", giftGrowth=" + giftGrowth +
				", giftCredit=" + giftCredit +
				", usePointLimit=" + usePointLimit +
				", subtitle='" + subtitle + '\'' +
				", description='" + description + '\'' +
				", originalPrice=" + originalPrice +
				", stock=" + stock +
				", lowStock=" + lowStock +
				", unit='" + unit + '\'' +
				", weight=" + weight +
				", previewStatus=" + previewStatus +
				", serviceIds='" + serviceIds + '\'' +
				", keywords='" + keywords + '\'' +
				", note='" + note + '\'' +
				", albumPics='" + albumPics + '\'' +
				", detailTitle='" + detailTitle + '\'' +
				", detailDescription='" + detailDescription + '\'' +
				", detailHtml='" + detailHtml + '\'' +
				", detailMobileHtml='" + detailMobileHtml + '\'' +
				", promotionStartTime=" + promotionStartTime +
				", promotionEndTime=" + promotionEndTime +
				", promotionPerLimit=" + promotionPerLimit +
				", promotionType=" + promotionType +
				", brandName='" + brandName + '\'' +
				", productCategoryName='" + productCategoryName + '\'' +
				'}';
	}

/******************************************************************************************************************************/
   	@ManyToOne
	@JoinColumn(name = "brand_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	private PmsBrand pmsBrand;

   	@ManyToOne
	@JoinColumn(name = "feight_template_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	private PmsFeightTemplate pmsFeightTemplate;

   	@ManyToOne
	@JoinColumn(name = "product_attribute_category_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	private PmsProductAttributeCategory pmsProductAttributeCategory;

   	@ManyToOne
	@JoinColumn(name = "product_category_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	private PmsProductCategory pmsProductCategory;


}
