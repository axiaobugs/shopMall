package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: 人气推荐商品表  用于管理首页显示的人气推荐信息。
 * Popular recommended products table
 *
 * management of to display the info of popular products
 *
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 12:25
 */
@Data
@Entity
@Table (name = "sms_home_recommend_product")
@NoArgsConstructor
@AllArgsConstructor
public class SmsHomeRecommendProduct implements Serializable {

//	private static final long serialVersionUID =  4649309374652471387L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "product_name" )
	private String productName = null;

   	@Column(name = "recommend_status" )
	private Integer recommendStatus = null;


	private Integer sort = null;

	@Override
	public String toString() {
		return "SmsHomeRecommendProduct{" +
				"id=" + id +
				", productId=" + productId +
				", productName='" + productName + '\'' +
				", recommendStatus=" + recommendStatus +
				", sort=" + sort +
				'}';
	}

	/**************************************************************************************/
}
