package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 14:56
 */
@Data
@Entity
@Table (name = "pms_product_category_attribute_relation")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductCategoryAttributeRelation implements Serializable {

	private static final long serialVersionUID =  7882545578451843618L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_category_id" )
	private Integer productCategoryId = null;

   	@Column(name = "product_attribute_id" )
	private Integer productAttributeId = null;

}
