package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: 首页品牌推荐表  用于管理首页显示的品牌制造商直供信息。
 * index page of trending brand table
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 12:30
 */
@Data
@Entity
@Table (name = "sms_home_brand")
@NoArgsConstructor
@AllArgsConstructor
public class SmsHomeBrand implements Serializable {

//	private static final long serialVersionUID =  1529094669863935103L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "brand_id" )
	private Integer brandId = null;

   	@Column(name = "brand_name" )
	private String brandName = null;

   	@Column(name = "recommend_status" )
	private Integer recommendStatus = null;

	private Integer sort = null;

	@Override
	public String toString() {
		return "SmsHomeBrand{" +
				"id=" + id +
				", brandId=" + brandId +
				", brandName='" + brandName + '\'' +
				", recommendStatus=" + recommendStatus +
				", sort=" + sort +
				'}';
	}

	/**************************************************************************************/
}
