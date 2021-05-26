package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Discription: 优选主题管理表
 * Preferred product
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 19:24
 */
@Data
@Entity
@Table (name = "cms_prefrence_area")
@NoArgsConstructor
@AllArgsConstructor
public class CmsPrefrenceArea implements Serializable {

	private static final long serialVersionUID =  5377433994967775719L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "name" )
	private String name = null;

   	@Column(name = "sub_title" )
	private String subtitle = null;

	/**展示图片*/
   	@Column(name = "pic" )
	private byte[] pic = null;

   	@Column(name = "sort" )
	private Integer sort = null;

   	@Column(name = "show_status" )
	private Integer showStatus = null;

	@Override
	public String toString() {
		return "CmsPrefrenceArea{" +
				"id=" + id +
				", name='" + name + '\'' +
				", subtitle='" + subtitle + '\'' +
				", pic=" + Arrays.toString(pic) +
				", sort=" + sort +
				", showStatus=" + showStatus +
				'}';
	}

	/********************************************************************/

	@OneToMany(mappedBy = "cmsPrefrenceArea")
	@JsonIgnore
	private List<CmsPrefrenceAreaProductRelation> productRelationList = new ArrayList<>();
}
