package com.axiaobug.pojo.pms;

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
 * @Author: Yanxiao Fang
 * @Date: 2021/05/22 15:38
 */
@Data
@Entity
@Table (name = "pms_comment")
@NoArgsConstructor
@AllArgsConstructor
public class PmsComment implements Serializable {

	private static final long serialVersionUID =  5912693024524957278L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "member_nick_name" )
	private String memberNickName = null;

   	@Column(name = "product_name" )
	private String productName = null;

	/**评价星数：0->5*/
   	@Column(name = "star" )
	private Integer star = 0;

	/**评价的ip*/
   	@Column(name = "member_ip" )
	private String memberIp = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "show_status" )
	private Integer showStatus = 0;

	/**购买时的商品属性*/
   	@Column(name = "product_attribute" )
	private String productAttribute = null;

   	@Column(name = "collect_couont" )
	private Integer collectCount = 0;

   	@Column(name = "read_count" )
	private Integer readCount = 0;

   	@Column(name = "content" )
	private String content = null;

	/**上传图片地址，以逗号隔开*/
   	@Column(name = "pics" )
	private String pics =null;

	/**评论用户头像*/
   	@Column(name = "member_icon" )
	private String memberIcon = null;

   	@Column(name = "replay_count" )
	private Integer replayCount = 0;

   	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false,unique = true)
   	private PmsProduct pmsProduct;

   	@OneToMany(mappedBy = "pmsComment",cascade = CascadeType.PERSIST)
	private List<PmsCommentReplay> commentReplayList = new ArrayList<>();

}
