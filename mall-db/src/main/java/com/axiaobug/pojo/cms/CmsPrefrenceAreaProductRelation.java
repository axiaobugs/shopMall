package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * Preferred product to product relation table
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 19:24
 */
@Data
@Entity
@Table (name = "cms_prefrence_area_product_relation")
@NoArgsConstructor
@AllArgsConstructor
public class CmsPrefrenceAreaProductRelation implements Serializable {

	private static final long serialVersionUID =  4860115804202995135L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "prefrence_area_id" )
	private Integer prefrenceAreaId = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

	@Override
	public String toString() {
		return "CmsPrefrenceAreaProductRelation{" +
				"id=" + id +
				", prefrenceAreaId=" + prefrenceAreaId +
				", productId=" + productId +
				'}';
	}

	/********************************************************************/

	@ManyToOne
	@JoinColumn(name = "prefrence_area_id",insertable = false,updatable = false)
	private CmsPrefrenceArea cmsPrefrenceArea;

}
