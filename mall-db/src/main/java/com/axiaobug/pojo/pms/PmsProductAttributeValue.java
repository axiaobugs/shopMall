package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 22:11
 */
@Data
@Entity
@Table (name = "pms_product_attribute_value")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductAttributeValue implements Serializable {

	private static final long serialVersionUID =  5745797430100218953L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "product_attribute_id" )
	private Integer productAttributeId = null;

	/**手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开*/
   	@Column(name = "value" )
	private String value = null;

   	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_attribute_id",insertable = false,updatable = false)
	@JsonIgnore
	private PmsProductAttribute pmsProductAttribute;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	@JsonIgnore
	private PmsProduct pmsProduct;


}
