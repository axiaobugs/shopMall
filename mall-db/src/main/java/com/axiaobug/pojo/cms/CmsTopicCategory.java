package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Discription: Topic category table
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 17:46
 */
@Data
@Entity
@Table (name = "cms_topic_category")
@NoArgsConstructor
@AllArgsConstructor
public class CmsTopicCategory implements Serializable {

	private static final long serialVersionUID =  6780655626370274485L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name = null;

	private String icon = null;

	/**
	 * subject amount
	 * 专题数量
	 * */
   	@Column(name = "subject_count" )
	private Integer subjectCount = null;

   	@Column(name = "show_status" )
	private Integer showStatus = null;

	private Integer sort = null;

	@Override
	public String toString() {
		return "CmsTopicCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", icon='" + icon + '\'' +
				", subjectCount=" + subjectCount +
				", showStatus=" + showStatus +
				", sort=" + sort +
				'}';
	}

	/****************************************************/

	@OneToMany(mappedBy = "cmsTopicCategory")
	private List<CmsTopic> topics = new ArrayList<>();
}
