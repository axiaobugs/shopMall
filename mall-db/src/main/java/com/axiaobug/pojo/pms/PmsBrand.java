package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;


/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/21 16:19
 */
@Data
@Entity
@Table (name = "pms_brand")
@NoArgsConstructor
@AllArgsConstructor
public class PmsBrand implements Serializable {

	private static final long serialVersionUID =  1278540195769295643L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

   	@Column(name = "name")
	private String name = null;

	/**
	 * Initials
	 * 首字母
	 * */
   	@Column(name = "first_letter" )
	private String firstLetter = null;

   	@Column(name = "sort" )
	private Integer sort = 0;

	/**
	 *
	 * 是否为品牌制造商：0->不是；1->是
	 * */
   	@Column(name = "factory_status" )
	private Integer factoryStatus = 0;

   	/**
	 * 0: no display 1: display
	 * */
   	@Column(name = "show_status" )
	private Integer showStatus = 1;

	/**
	 * product count
	 * 产品数量
	 * */
   	@Column(name = "product_count" )
	private Integer productCount = 0;

	/**
	 * Comment count
	 * 产品评论数量
	 * */
   	@Column(name = "product_comment_count" )
	private Integer productCommentCount = 0;

	/**
	 * logo pic address
	 * 品牌logo
	 * */
   	@Column(name = "logo" )
	private String logo = null;

	/**
	 * Big logo or display pic address
	 * 专区大图
	 * */
   	@Column(name = "big_pic" )
	private String bigPic = "";

	/**
	 * About Us
	 * 品牌故事
	 * */
   	@Column(name = "brand_story" )
	private String brandStory = null;
/** *************************************************************************/

   	@OneToMany(mappedBy = "pmsBrand",cascade = CascadeType.PERSIST)
	private List<PmsProduct> products = new ArrayList<>();
}
