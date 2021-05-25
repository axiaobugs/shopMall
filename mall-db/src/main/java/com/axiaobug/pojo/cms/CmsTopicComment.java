package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: content of topic table
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 17:34
 */
@Data
@Entity
@Table (name = "cms_topic_comment")
@NoArgsConstructor
@AllArgsConstructor
public class CmsTopicComment implements Serializable {

	private static final long serialVersionUID =  6692427944833978440L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "member_nick_name" )
	private String memberNickname = null;

   	@Column(name = "topic_id" )
	private Integer topicId = null;

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
		return "CmsTopicComment{" +
				"id=" + id +
				", memberNickname='" + memberNickname + '\'' +
				", topicId=" + topicId +
				", memberIcon='" + memberIcon + '\'' +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				", showStatus=" + showStatus +
				'}';
	}

	/******************************************************************/

	@ManyToOne
	@JoinColumn(name = "topic_id",insertable = false,updatable = false)
	private CmsTopic cmsTopic;
}
