package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 首页轮播广告表   用于管理首页显示的轮播广告信息。
 * Homepage carousel ad table --> Used to manage the carousel advertising information displayed on the homepage
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 12:40
 */
@Data
@Entity
@Table (name = "sms_home_advertise")
@NoArgsConstructor
@AllArgsConstructor
public class SmsHomeAdvertise implements Serializable {

//	private static final long serialVersionUID =  1259800351884447987L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "name" )
	private String name = null;

	/**
	 * 0: PC 1: Mobile
	 * 轮播位置：0->PC首页轮播；1->app首页轮播
	 * */
	private Integer type  = null;

	private String pic = null;

	private Date startTime = null;

   	@Column(name = "end_time" )
	private Date endTime = null;

	/**
	 * 0: offline 1: online
	 * 上下线状态：0->下线；1->上线
	 * */
	private Integer status=null;

   	@Column(name = "click_count" )
	private Integer clickCount = null;

   	@Column(name = "order_count" )
	private Integer orderCount = null;

   	@Column(name = "url" )
	private String url = null;

	private String note = null;

	private Integer sort = null;

	@Override
	public String toString() {
		return "SmsHomeAdvertise{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type=" + type +
				", pic='" + pic + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", status=" + status +
				", clickCount=" + clickCount +
				", orderCount=" + orderCount +
				", url='" + url + '\'' +
				", note='" + note + '\'' +
				", sort=" + sort +
				'}';
	}

	/**************************************************************************************/
}
