package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 11:36
 */
@Data
@Entity
@Table (name = "pms_product_category")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductCategory implements Serializable {

//	private static final long serialVersionUID =  2078423651113654953L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 0: first category
	 * 上机分类的编号：0表示一级分类
	 * */
   	@Column(name = "parent_id" )
	private Integer parentId = null;

	private String name = null;

	/**
	 * 1: first 2: second
	 * 分类级别：0->1级；1->2级
	 * */
	private Integer level = null;

   	@Column(name = "product_count" )
	private Integer productCount = null;

   	@Column(name = "product_unit" )
	private String productUnit = null;

	/**
	 * 0: no display nav bar 1: display
	 * 是否显示在导航栏：0->不显示；1->显示
	 * */
   	@Column(name = "nav_status" )
	private Integer navStatus = null;

	/**
	 * 0:no display 1: display
	 * 显示状态：0->不显示；1->显示
	 * */
   	@Column(name = "show_status" )
	private Integer showStatus = null;

	private Integer sort = null;


	private String icon = null;

	private String keywords = null;


	private String description = null;

	/********************************************************/

	@OneToMany(mappedBy = "pmsProductCategory",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private List<PmsProduct> products = new ArrayList<>();


}
