package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: subject comment table (many to one)
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 19:58
 */
@Data
@Entity
@Table (name = "cms_subject_comment")
@NoArgsConstructor
@AllArgsConstructor
public class CmsSubjectComment implements Serializable {

	private static final long serialVersionUID =  3675021650085171676L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "subject_id" )
	private Integer subjectId = null;

   	@Column(name = "member_nick_name" )
	private String memberNickname = null;

   	@Column(name = "member_icon" )
	private String memberIcon = null;

	private String content = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "show_status" )
	private Integer showStatus = null;

	@Override
	public String toString() {
		return "CmsSubjectComment{" +
				"id=" + id +
				", subjectId=" + subjectId +
				", memberNickname='" + memberNickname + '\'' +
				", memberIcon='" + memberIcon + '\'' +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				", showStatus=" + showStatus +
				'}';
	}

	/**********************************************************************/

	@ManyToOne
	@JoinColumn(name = "subject_id",insertable = false,updatable = false)
	private CmsSubject cmsSubject;
}
