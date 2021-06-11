package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;

/**
 * @Discription: 限时购与商品关系表
 * 用于存储与限时购相关的商品信息，一个限时购中有多个场次，每个场次都可以设置不同活动商品。
 * Time-limited purchase and commodity relationship table
 * Used to store product information related to limited-time purchases.
 * There are multiple sessions in a limited-time purchase,
 * and different event products can be set for each session.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 13:38
 */
@Data
@Entity
@Table (name = "sms_flash_promotion_product_relation")
@NoArgsConstructor
@AllArgsConstructor
public class SmsFlashPromotionProductRelation implements Serializable {

//	private static final long serialVersionUID =  1569541633361901100L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * fk -->flash_promotion_id
	 * */
   	@Column(name = "flash_promotion_id" )
	private Integer flashPromotionId = null;

	/**
	 * fk --> flash_promotion_session_id
	 * */
   	@Column(name = "flash_promotion_session_id" )
	private Integer flashPromotionSessionId = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

	/**
	 * Limited time purchase price
	 * 限时购价格
	 * */
   	@Column(name = "flash_promotion_price" )
	private BigDecimal flashPromotionPrice = null;

	/**
	 * Qty of Limited time purchase
	 * 限时购数量
	 * */
   	@Column(name = "flash_promotion_count" )
	private Integer flashPromotionCount = null;

	/**
	 * Purchase limit per person
	 * 每人限购数量
	 * */
   	@Column(name = "flash_promotion_limit" )
	private Integer flashPromotionLimit = null;

	private Integer sort = null;

	@Override
	public String toString() {
		return "SmsFlashPromotionProductRelation{" +
				"id=" + id +
				", flashPromotionId=" + flashPromotionId +
				", flashPromotionSessionId=" + flashPromotionSessionId +
				", productId=" + productId +
				", flashPromotionPrice=" + flashPromotionPrice +
				", flashPromotionCount=" + flashPromotionCount +
				", flashPromotionLimit=" + flashPromotionLimit +
				", sort=" + sort +
				'}';
	}

	/**************************************************************************************/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flash_promotion_session_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmsFlashPromotionSession smsFlashPromotionSession;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flash_promotion_id",insertable = false,updatable = false)
	@JsonIgnore
	private SmsFlashPromotion smsFlashPromotion;

}
