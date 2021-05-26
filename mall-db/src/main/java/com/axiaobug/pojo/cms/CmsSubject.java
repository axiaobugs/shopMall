package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 专题管理表
 * subject table
 * management of all info about subject
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 19:47
 *
 * TODO: subject需要添加单元测试.
 */
@Data
@Entity
@Table (name = "cms_subject")
@NoArgsConstructor
@AllArgsConstructor
public class CmsSubject implements Serializable {

	private static final long serialVersionUID =  8311663691323674138L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "category_id" )
	private Integer categoryId = null;

   	@Column(name = "title" )
	private String title = null;

	private String pic = null;

	/**
	 * relation product Qty (count by product SPU)
	 * 关联产品数量
	 * */
   	@Column(name = "product_count" )
	private Integer productCount = null;

   	@Column(name = "recommend_status" )
	private Integer recommendStatus = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "collect_count" )
	private Integer collectCount = null;

   	@Column(name = "read_count" )
	private Integer readCount = null;

   	@Column(name = "comment_count" )
	private Integer commentCount = null;

   	@Column(name = "album_pics" )
	private String albumPics = null;

   	@Column(name = "description" )
	private String description = null;

	/**
	 * 0: no display
	 * 1: display
	 * 显示状态：0->不显示；1->显示
	 * */
   	@Column(name = "show_status" )
	private Integer showStatus = null;

   	@Column(name = "content" )
	private String content = null;

   	@Column(name = "forward_count" )
	private Integer forwardCount = null;

   	@Column(name = "category_name" )
	private String categoryName = null;

	@Override
	public String toString() {
		return "CmsSubject{" +
				"id=" + id +
				", categoryId=" + categoryId +
				", title='" + title + '\'' +
				", pic='" + pic + '\'' +
				", productCount=" + productCount +
				", recommendStatus=" + recommendStatus +
				", createTime=" + createTime +
				", collectCount=" + collectCount +
				", readCount=" + readCount +
				", commentCount=" + commentCount +
				", albumPics='" + albumPics + '\'' +
				", description='" + description + '\'' +
				", showStatus=" + showStatus +
				", content='" + content + '\'' +
				", forwardCount=" + forwardCount +
				", categoryName='" + categoryName + '\'' +
				'}';
	}

	/****************************************************/


	@OneToMany(mappedBy = "cmsSubject")
	@JsonIgnore
	private List<CmsSubjectProductRelation> subjectProductRelations;

	@OneToMany(mappedBy = "cmsSubject")
	@JsonIgnore
	private List<CmsSubjectComment> cmsSubjectComments;

	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false,updatable = false)
	@JsonIgnore
	private CmsSubjectCategory cmsSubjectCategory;
}
