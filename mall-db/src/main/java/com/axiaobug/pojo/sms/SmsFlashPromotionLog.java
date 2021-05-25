package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 限时购通知记录表
 * 用于存储会员的限时购预约记录，当有的限时购场次还未开始时，会员可以进行预约操作，当场次开始时，系统会进行提醒。
 * Limited time purchase notice record table
 * It is used to store members' limited-time purchase reservation records.
 * When some limited-time purchase sessions have not yet started,
 * members can make reservation operations. When the session starts,
 * the system will remind you.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 12:22
 */
@Data
@Entity
@Table (name = "sms_flash_promotion_log")
@NoArgsConstructor
@AllArgsConstructor
public class SmsFlashPromotionLog implements Serializable {

	private static final long serialVersionUID =  6075740742588424321L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "member_id" )
	private Integer memberId = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "member_phone" )
	private String memberPhone = null;

   	@Column(name = "product_name" )
	private String productName = null;

   	@Column(name = "subscribe_time" )
	private Date subscribeTime = null;

   	@Column(name = "send_time" )
	private Date sendTime = null;

	@Override
	public String toString() {
		return "SmsFlashPromotionLog{" +
				"id=" + id +
				", memberId=" + memberId +
				", productId=" + productId +
				", memberPhone='" + memberPhone + '\'' +
				", productName='" + productName + '\'' +
				", subscribeTime=" + subscribeTime +
				", sendTime=" + sendTime +
				'}';
	}

	/**************************************************************************************/
}
