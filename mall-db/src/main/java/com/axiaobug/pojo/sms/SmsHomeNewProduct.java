package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: 新品推荐商品表  用于管理首页显示的新鲜好物信息。
 * New product recommendation table ---> Used to manage the fresh and good information displayed on the homepage
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 12:37
 */
@Data
@Entity
@Table (name = "sms_home_new_product")
@NoArgsConstructor
@AllArgsConstructor
public class SmsHomeNewProduct implements Serializable {

	private static final long serialVersionUID =  6073240739093745554L;

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
		return "SmsHomeNewProduct{" +
				"id=" + id +
				", productId=" + productId +
				", productName='" + productName + '\'' +
				", recommendStatus=" + recommendStatus +
				", sort=" + sort +
				'}';
	}

	/**************************************************************************************/
}
