package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 限时购表
 * 用于存储限时购活动的信息，包括开始时间、结束时间以及上下线状态。
 * Limited time purchase table
 * Used to store information about limited-time purchases,
 * including start time, end time, and online and offline status.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 14:10
 */
@Data
@Entity
@Table (name = "sms_flash_promotion")
@NoArgsConstructor
@AllArgsConstructor
public class SmsFlashPromotion implements Serializable {

	private static final long serialVersionUID =  1958786721758031347L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = null;

	private String title = null;


   	@Column(name = "start_date" )
	private Date startDate = null;

	/**结束日期*/
   	@Column(name = "end_date" )
	private Date endDate = null;

	/**
	 * status of online
	 * 上下线状态
	 * */
   	@Column(name = "status" )
	private Integer status;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	@Override
	public String toString() {
		return "SmsFlashPromotion{" +
				"id=" + id +
				", title='" + title + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", status=" + status +
				", createTime=" + createTime +
				'}';
	}

	/************************************************************************/

	@OneToMany(mappedBy = "smsFlashPromotion")
	private List<SmsFlashPromotionProductRelation> flashPromotionProductRelations = new ArrayList<>();
}
