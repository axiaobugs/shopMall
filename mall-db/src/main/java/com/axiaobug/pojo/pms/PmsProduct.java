package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

//	private static final long serialVersionUID =  4312377958177817138L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "brand_id" )
	private Integer brandId = null;

   	@Column(name = "product_category_id" )
	private Integer productCategoryId = null;

   	@Column(name = "feight_template_id" )
	private Integer feightTemplateId = null;

   	@Column(name = "product_attribute_category_id" )
	private Integer productAttributeCategoryId = null;

	private String name = null;

	private String pic = null;

	/**货号*/
   	@Column(name = "product_sn" )
	private String productSerialNumber = null;

	/**
	 * 0: no 1: deleted
	 * 删除状态：0->未删除；1->已删除
	 * */
   	@Column(name = "delete_status" )
	private Integer deleteStatus = null;

	/**
	 * 0: out of stock 1 in stock
	 * 上架状态：0->下架；1->上架
	 * */
   	@Column(name = "publish_status" )
	private Integer publishStatus = null;

	/**
	 * 0: normal  1:New release
	 * 新品状态:0->不是新品；1->新品
	 * */
   	@Column(name = "new_status" )
	private Integer newStatus = null;

	/**
	 * 0: normal 1: promote
	 * 推荐状态；0->不推荐；1->推荐
	 * */
   	@Column(name = "recommand_status" )
	private Integer recommendStatus = null;

	/**
	 * 0: no verify 1:verified
	 * 审核状态：0->未审核；1->审核通过
	 * */
   	@Column(name = "verify_status" )
	private Integer verifyStatus = null;

	/**排序*/
	private Integer sort = null;

	/**
	 * Sale num
	 * 销量
	 * */
   	@Column(name = "sale" )
	private Integer sale = null;

   	@Column(scale = 2)
	private BigDecimal price = null;

	/**
	 * promote price
	 * 促销价格
	 * */
   	@Column(name = "promotion_price",scale = 2)
	private BigDecimal promotionPrice = null;

	/**
	 * Exp
	 * 赠送的成长值
	 * */
   	@Column(name = "gift_growth" )
	private Integer giftGrowth = null;

	/**
	 * credit
	 * 赠送的积分
	 * */
   	@Column(name = "gift_point" )
	private Integer giftCredit = null;

	/**
	 * limit of credit
	 * 限制使用的积分数
	 * */
   	@Column(name = "use_point_limit" )
	private Integer usePointLimit = null;

   	@Column(name = "sub_title" )
	private String subtitle = null;

   	@Column(name = "description" )
	private String description = null;

	/**
	 * market price which for calculate the discount
	 *
	 * 市场价
	 * */
   	@Column(name = "original_price", scale = 2)
	private BigDecimal originalPrice = null;

	private Integer stock = null;

	/**
	 * Alert when stock lower than this num
	 * 库存预警值
	 * */
   	@Column(name = "low_stock" )
	private Integer lowStock = null;

	/**
	 *
	 * 单位
	 * */
	private String unit = null;

	/**
	 * weight (gram)
	 * 商品重量，默认为克
	 * */
   	@Column(name = "weight" )
	private BigDecimal weight = null;

	/**
	 * 0:No 1:Yes
	 * 是否为预告商品：0->不是；1->是
	 * */
   	@Column(name = "preview_status" )
	private Integer previewStatus = null;

	/**
	 * 1: Free to return 2: Refund 3: Free Shipping
	 * like "1,2,3"
	 * 以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
	 * */
   	@Column(name = "service_ids" )
	private String serviceIds = null;

	private String keywords = null;

	private String note = null;

	/**
	 * limit 5 pic address = "add1,add2,add3,...."
	 * 画册图片，连产品图片限制为5张，以逗号分割
	 * */
   	@Column(name = "album_pics" )
	private String albumPics = null;

   	@Column(name = "detail_title" )
	private String detailTitle = null;

   	@Column(name = "detail_desc" )
	private String detailDescription = null;

   	@Column(name = "detail_html" )
	private String detailHtml = null;

	/**
	 * mobile html
	 * 移动端网页详情
	 * */
   	@Column(name = "detail_mobile_html" )
	private String detailMobileHtml = null;

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
	private Integer promotionPerLimit = null;

	/**
	 * 0: normal 1: promote price 2: member price 3: bulk purchases 4: Further reductions  5:flash sale
	 * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购*/
   	@Column(name = "promotion_type" )
	private Integer promotionType = null;

   	@Column(name = "brand_name" )
	private String brandName = null;

	/**
	 * 商品分类名称
	 * */
   	@Column(name = "product_category_name" )
	private String productCategoryName = null;

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
   	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	@JsonIgnore
	private PmsBrand pmsBrand;

   	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "feight_template_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	@JsonIgnore
	private PmsFeightTemplate pmsFeightTemplate;

   	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "product_attribute_category_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	@JsonIgnore
	private PmsProductAttributeCategory pmsProductAttributeCategory;

   	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_category_id",
			referencedColumnName = "id",
			insertable = false,
			updatable = false)
	@JsonIgnore
	private PmsProductCategory pmsProductCategory;

   	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
   	private PmsComment comments;


   	@OneToMany(mappedBy = "pmsProduct",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsProductOperateLog> productOperateLogs = new ArrayList<>();

   	@OneToMany(mappedBy = "pmsProduct",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsProductAttributeValue> productAttributeValues = new ArrayList<>();

   	@OneToMany(mappedBy = "pmsProduct",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsSkuStock> skuStocks = new ArrayList<>();

   	@OneToMany(mappedBy = "pmsProduct",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsProductFullReduction> productFullReductions = new ArrayList<>();

   	@OneToMany(mappedBy = "pmsProduct",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsProductVertifyRecord> productVertifyRecords = new ArrayList<>();

}
