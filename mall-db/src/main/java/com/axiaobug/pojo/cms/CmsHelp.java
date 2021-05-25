package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 帮助管理
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 17:11
 */
@Data
@Entity
@Table (name = "cms_help")
@NoArgsConstructor
@AllArgsConstructor
public class CmsHelp implements Serializable {

	private static final long serialVersionUID =  8679993801441869041L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	/** fk -> cms_help_category.category_id*/
   	@Column(name = "category_id" )
	private Integer categoryId = null;

	private String icon = null;

	private String title = null;

   	@Column(name = "show_status" )
	private Integer showStatus = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "read_count" )
	private Integer readCount = null;

	private String content = null;

	@Override
	public String toString() {
		return "CmsHelp{" +
				"id=" + id +
				", categoryId=" + categoryId +
				", icon='" + icon + '\'' +
				", title='" + title + '\'' +
				", showStatus=" + showStatus +
				", createTime=" + createTime +
				", readCount=" + readCount +
				", content='" + content + '\'' +
				'}';
	}

	/*******************************************************************/

	@ManyToOne
	@JoinColumn(name = "category_id",insertable = false,updatable = false)
	private CmsHelpCategory cmsHelpCategory;
}
