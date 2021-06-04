package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 10:49
 */
@Data
@Entity
@Table (name = "pms_product_attribute_category")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductAttributeCategory implements Serializable {

	private static final long serialVersionUID =  2847028917884613505L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name = null;


   	@Column(name = "attribute_count" )
	private Integer attributeCount = 0;


   	@Column(name = "param_count" )
	private Integer paramCount = 0;


   	@OneToMany(mappedBy = "pmsProductAttributeCategory",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsProductAttribute> pmsProductAttributes = new ArrayList<>();

   	@OneToMany(mappedBy = "pmsProductAttributeCategory",cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PmsProduct> products = new ArrayList<>();


}
