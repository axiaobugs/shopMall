package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 限时购场次表
 * 用于存储限时购场次的信息，在一天中，一个限时购活动会有多个不同的活动时间段。
 * Limited time purchase schedule Table
 * It is used to store information about limited-time purchases.
 * In one day, a limited-time purchase event will have multiple different activity time periods.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 13:54
 */
@Data
@Entity
@Table (name = "sms_flash_promotion_session")
@NoArgsConstructor
@AllArgsConstructor
public class SmsFlashPromotionSession implements Serializable {

	private static final long serialVersionUID =  2208740720409917360L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name = null;

   	@Column(name = "start_time" )
	private Date startTime = null;

   	@Column(name = "end_time" )
	private Date endTime = null;

	/**
	 * 0: Enable 1: Disable
	 * 启用状态：0->不启用；1->启用
	 * */
	private Integer status = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	@Override
	public String toString() {
		return "SmsFlashPromotionSession{" +
				"id=" + id +
				", name='" + name + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", status=" + status +
				", createTime=" + createTime +
				'}';
	}

	/***********************************************************************************/

	@OneToMany(mappedBy = "smsFlashPromotionSession",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SmsFlashPromotionProductRelation> flashPromotionProductRelations = new ArrayList<>();

}
