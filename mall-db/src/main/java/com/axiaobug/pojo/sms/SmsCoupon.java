package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 优惠券表
 * 用于存储优惠券信息，需要注意的是优惠券的使用类型，不同使用类型的优惠券使用范围不一样。
 * Coupon table
 * It is used to store coupon information.
 * It should be noted that the coupon usage type is different.
 * The coupon usage range of different usage types is different.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 14:21
 */
@Data
@Entity
@Table (name = "sms_coupon")
@NoArgsConstructor
@AllArgsConstructor
public class SmsCoupon implements Serializable {

//	private static final long serialVersionUID =  3419795740920176388L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
     * Coupon source
     * 0: normal 1: member 2: purchase 3: registry account
     * 优惠券类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券
     * */
	private Integer type = null;

   	@Column(name = "name" )
	private String name = null;

	/**
     * which platform to use
     * 0: all 1: mobile 2: pc
     * 使用平台：0->全部；1->移动；2->PC
     * */
   	@Column(name = "platform" )
	private Integer platform  = null;

    /**qty*/
	private Integer count = null;

	/**price amount*/
   	@Column(name = "amount" )
	private BigDecimal amount = null;

	/**
     * limit to use per person
     * 每人限领张数
     * */
   	@Column(name = "per_limit" )
	private Integer perLimit = null;

	/**
     * use the coupon when greater than this Exp point only
     * 0: no limit
     * 使用门槛；0表示无门槛
     * */
   	@Column(name = "min_point" )
	private BigDecimal minPoint = null;

   	@Column(name = "start_time" )
	private Date startTime = null;

   	@Column(name = "end_time" )
	private Date endTime = null;

	/**
     * use limit
     * 0:full functional 1: limit category 2: limit product
     * 使用类型：0->全场通用；1->指定分类；2->指定商品
     * */
   	@Column(name = "use_type" )
	private Integer useType = null;

	private String note = null;

	/**
     * publish qty
     * 发行数量
     * */
   	@Column(name = "publish_count" )
	private Integer publishCount = null;

	/**
     * used qty
     * 已使用数量
     * */
   	@Column(name = "use_count" )
	private Integer useCount = null;

	/**
     * receive qty
     * 领取数量
     * */
   	@Column(name = "receive_count" )
	private Integer receiveCount = null;

   	@Column(name = "enable_time" )
	private Date enableTime;

	private String code = null;

	/**
     * 0: no limit
     * 可领取的会员类型：0->无限时
     * */
   	@Column(name = "member_level" )
	private Integer memberLevel = null;

    @Override
    public String toString() {
        return "SmsCoupon{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", platform=" + platform +
                ", count=" + count +
                ", amount=" + amount +
                ", perLimit=" + perLimit +
                ", minPoint=" + minPoint +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", useType=" + useType +
                ", note='" + note + '\'' +
                ", publishCount=" + publishCount +
                ", useCount=" + useCount +
                ", receiveCount=" + receiveCount +
                ", enableTime=" + enableTime +
                ", code='" + code + '\'' +
                ", memberlevel=" + memberLevel +
                '}';
    }

    /*********************************************************************/

    @OneToMany(mappedBy = "smsCoupon",fetch = FetchType.LAZY)
	private List<SmsCouponHistory> couponHistories = new ArrayList<>();

    @OneToMany(mappedBy = "smsCoupon",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private List<SmsCouponProductRelation> couponProductRelations = new ArrayList<>();

    @OneToMany(mappedBy = "smsCoupon",fetch = FetchType.LAZY)
	private List<SmsCouponProductCategoryRelation> couponProductCategoryRelations = new ArrayList<>();


}
