package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @Discription: 会员被举报的表
 * Member Reported Form
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 16:45
 */
@Data
@Entity
@Table (name = "cms_member_report")
@NoArgsConstructor
@AllArgsConstructor
public class CmsMemberReport implements Serializable {

	private static final long serialVersionUID =  1475052136624550352L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 0: product comment
	 * 1: topic content
	 * 2: user comment
	 * 举报类型：0->商品评价；1->话题内容；2->用户评论
	 * */
   	@Column(name = "report_type" )
	private Integer reportType = null;

	/**
	 * who report
	 * 举报人
	 * */
   	@Column(name = "report_member_name" )
	private String reportMemberName = null;

   	@Column(name = "create_time" )
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = null;

   	@Column(name = "report_object" )
	private String reportObject = null;

	/**
	 * 0: Untreated
	 * 1: Treated
	 * 举报状态：0->未处理；1->已处理
	 * */
   	@Column(name = "report_status" )
	private Integer reportStatus = null;

	/**
	 * result 0: invalid 1: valid 2: Malicious
	 * 处理结果：0->无效；1->有效；2->恶意
	 * */
   	@Column(name = "handle_status" )
	private Integer result = null;

	private String note = null;

	@Override
	public String toString() {
		return "CmsMemberReport{" +
				"id=" + id +
				", reportType=" + reportType +
				", reportMemberName='" + reportMemberName + '\'' +
				", createTime=" + createTime +
				", reportObject='" + reportObject + '\'' +
				", reportStatus=" + reportStatus +
				", result=" + result +
				", note='" + note + '\'' +
				'}';
	}

	/*******************************************************************/
}
