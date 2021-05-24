package com.axiaobug.pojo.oms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/24 20:58
 */
@Data
@Entity
@Table (name = "oms_order_return_reason")
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderReturnReason implements Serializable {

	private static final long serialVersionUID =  5056206531984394841L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * reason of return the product
	 * 退货类型
	 * */
	private String name = null;

	private Integer sort = null;

	/**
	 * 0:no
	 * 1:yes
	 * 状态：0->不启用；1->启用
	 * */
	private Integer status = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

	@Override
	public String toString() {
		return "OmsOrderReturnReason{" +
				"id=" + id +
				", name='" + name + '\'' +
				", sort=" + sort +
				", status=" + status +
				", createTime=" + createTime +
				'}';
	}
}
