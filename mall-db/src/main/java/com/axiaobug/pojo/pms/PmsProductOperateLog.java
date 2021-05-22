package com.axiaobug.pojo.pms;

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
 * @Date: 2021/05/22 21:49
 */
@Data
@Entity
@Table (name = "pms_product_operate_log")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductOperateLog implements Serializable {

	private static final long serialVersionUID =  3926135153161532336L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;


   	/**
   	* price before modified
 	* 改变前价格
   	* */
   	@Column(name = "price_old" )
	private BigDecimal priceOld = null;

	/**
	 * price after modified
	 * 改变后价格
	 * */
   	@Column(name = "price_new" )
	private BigDecimal priceNew = null;

	/**
	 * promote price before modified
	 * 改变前优惠价
	 * */
   	@Column(name = "sale_price_old" )
	private BigDecimal salePriceOld = null;

	/**
	 * promote price after modified
	 * 改变后优惠价
	 * */
   	@Column(name = "sale_price_new" )
	private BigDecimal salePriceNew = null;

	/**
	 * credit before modified
	 * 改变前的积分
	 * */
   	@Column(name = "gift_point_old" )
	private Integer giftPointOld = null;

	/**
	 * credit after modified
	 * 改变后的积分
	 * */
   	@Column(name = "gift_point_new" )
	private Integer giftPointNew = null;

	/**
	 * limit to use of credit before modified
	 * 改变前积分使用限制
	 * */
   	@Column(name = "use_point_limit_old" )
	private Integer usePointLimitOld = null;

	/**
	 * limit to use of credit after modified
	 * 改变后积分使用限制
	 * */
   	@Column(name = "use_point_limit_new" )
	private Integer usePointLimitNew = null;

	/**
	 * record the man who write this log
	 * 操作人
	 * */
   	@Column(name = "operate_man" )
	private String operateMan = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;


   	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private PmsProduct pmsProduct;
}
