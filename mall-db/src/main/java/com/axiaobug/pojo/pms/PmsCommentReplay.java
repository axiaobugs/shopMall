package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 16:59
 */
@Data
@Entity
@Table (name = "pms_comment_replay")
@NoArgsConstructor
@AllArgsConstructor
public class PmsCommentReplay implements Serializable {

	private static final long serialVersionUID =  6022075062662672501L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "comment_id" )
	private Integer commentId = null;

   	@Column(name = "member_nick_name" )
	private String memberNickName = null;

   	@Column(name = "member_icon" )
	private String memberIcon = null;

   	@Column(name = "content" )
	private String content = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	/**
	 * 0: member 1: admin
	 * 评论人员类型；0->会员；1->管理员
	 * */
   	@Column(name = "type" )
	private Integer type = null;


   	@ManyToOne
	@JoinColumn(name = "comment_id",insertable = false,updatable = false)
	private PmsComment pmsComment;

}
