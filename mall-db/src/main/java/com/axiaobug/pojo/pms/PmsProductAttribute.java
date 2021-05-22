package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 10:49
 */
@Data
@Entity
@Table (name = "pms_product_attribute")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductAttribute implements Serializable {

	private static final long serialVersionUID =  5261539435004609543L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

   	@Column(name = "product_attribute_category_id" )
	private Integer productAttributeCategoryId = null;

	private String name = null;

	/**
	 * 0: unique 1: single 2: multiple
	 * 属性选择类型：0->唯一；1->单选；2->多选
	 * */
   	@Column(name = "select_type" )
	private Integer selectType = 1;

	/**
	 * input method
	 * 1:manual 2: select from list
	 * 属性录入方式：0->手工录入；1->从列表中选取
	 * */
   	@Column(name = "input_type" )
	private Integer inputType = 0;

	/**
	 * list of select source,separate with ","
	 * 可选值列表，以逗号隔开
	 * */
   	@Column(name = "input_list" )
	private String inputList = null;

	/**
	 * sort highest would upload pic
	 * 排序字段：最高的可以单独上传图片
	 * */
   	@Column(name = "sort" )
	private Integer sort = 0;

	/**
	 * 1:normal 2: color
	 * 分类筛选样式：0->普通；1->颜色
	 * */
   	@Column(name = "filter_type" )
	private Integer filterType = 0;

	/**
	 * 0:no needed 1: keyword 2: range search
	 * 检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
	 * */
   	@Column(name = "search_type" )
	private Integer searchType = 0;

	/**
	 * 0: no  1: yes
	 * 相同属性产品是否关联；0->不关联；1->关联
	 * */
   	@Column(name = "related_status" )
	private Integer relatedStatus = 0;

	/**
	 * manual to add new product
	 * 0: no 1: yes
	 * 是否支持手动新增；0->不支持；1->支持
	 * */
   	@Column(name = "hand_add_status" )
	private Integer handAddStatus = 0;

	/**
	 * 0:specification 1: param
	 * 属性的类型；0->规格；1->参数
	 * */
   	@Column(name = "type" )
	private Integer type = 0;

   	/*************************************************************/


   	@ManyToOne
	@JoinColumn(name = "product_attribute_category_id",
			insertable = false,
			updatable = false)
	private PmsProductAttributeCategory pmsProductAttributeCategory;


}
