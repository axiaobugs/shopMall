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
 * @Date: 2021/05/23 12:23
 */
@Data
@Entity
@Table (name = "pms_product_vertify_record")
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductVertifyRecord implements Serializable {

	private static final long serialVersionUID =  3273325409090278515L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "product_id" )
	private Integer productId = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	/**
	 * Reviewer name
	 * 审核人
	 * */
   	@Column(name = "vertify_man" )
	private String verifyMan = null;

	private Integer status = null;

	/**
	 * detail
	 * 反馈详情
	 * */
	private String detail = null;

	@Override
	public String toString() {
		return "PmsProductVertifyRecord{" +
				"id=" + id +
				", productId=" + productId +
				", createTime=" + createTime +
				", verifyMan='" + verifyMan + '\'' +
				", status=" + status +
				", detail='" + detail + '\'' +
				'}';
	}

	/*************************************************/

	@ManyToOne
	@JoinColumn(name = "product_id",insertable = false,updatable = false)
	private PmsProduct pmsProduct;

}
