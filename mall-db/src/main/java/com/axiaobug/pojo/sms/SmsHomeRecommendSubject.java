package com.axiaobug.pojo.sms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: 首页专题推荐表 用于管理首页显示的专题精选信息。
 * Homepage Feature Recommendation Table
 * Used to manage the featured information displayed on the homepage.
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 12:35
 */
@Data
@Entity
@Table (name = "sms_home_recommend_subject")
@NoArgsConstructor
@AllArgsConstructor
public class SmsHomeRecommendSubject implements Serializable {

	private static final long serialVersionUID =  5794716779374118992L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "subject_id" )
	private Integer subjectId = null;

   	@Column(name = "subject_name" )
	private String subjectName = null;

   	@Column(name = "recommend_status" )
	private Integer recommendStatus = null;

	private Integer sort = null;

	@Override
	public String toString() {
		return "SmsHomeRecommendSubject{" +
				"id=" + id +
				", subjectId=" + subjectId +
				", subjectName='" + subjectName + '\'' +
				", recommendStatus=" + recommendStatus +
				", sort=" + sort +
				'}';
	}

	/**************************************************************************************/
}
