package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 话题管理表
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 17:29
 */
@Data
@Entity
@Table (name = "cms_topic")
@NoArgsConstructor
@AllArgsConstructor
public class CmsTopic implements Serializable {

	private static final long serialVersionUID =  6158909646409790409L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "category_id" )
	private Integer categoryId = null;

	private String name = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "start_time" )
	private Date starTime = null;

   	@Column(name = "end_time" )
	private Date endTime = null;

	/**
	 * The number of participants
	 * 参与人数
	 * */
   	@Column(name = "attend_count" )
	private Integer attendCount = null;

	/**
	 * followed amount
	 * 关注人数
	 * */
   	@Column(name = "attention_count" )
	private Integer attentionCount = null;

   	/**
	 * read amount
	 * */
   	@Column(name = "read_count" )
	private Integer readCount = null;

	/**
	 * gift name
	 * 奖品名称*/
   	@Column(name = "award_name" )
	private String awardName = null;

	/**
	 * attend type
	 * 参与方式*/
   	@Column(name = "attend_type" )
	private String attendType = null;

	private String content = null;

	@Override
	public String toString() {
		return "CmsTopic{" +
				"id=" + id +
				", categoryId=" + categoryId +
				", name='" + name + '\'' +
				", createTime=" + createTime +
				", starTime=" + starTime +
				", endTime=" + endTime +
				", attendCount=" + attendCount +
				", attentionCount=" + attentionCount +
				", readCount=" + readCount +
				", awardName='" + awardName + '\'' +
				", attendType='" + attendType + '\'' +
				", content='" + content + '\'' +
				'}';
	}

	/************************************************************/

	@OneToMany(mappedBy = "cmsTopic")
	private List<CmsTopicComment> topicComments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false,updatable = false)
	private CmsTopicCategory cmsTopicCategory;
}
