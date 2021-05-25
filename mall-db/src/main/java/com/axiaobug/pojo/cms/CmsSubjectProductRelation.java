package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Discription: subject and product relation (joint table)
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 19:54
 */
@Data
@Entity
@Table (name = "cms_subject_product_relation")
@NoArgsConstructor
@AllArgsConstructor
public class CmsSubjectProductRelation implements Serializable {

	private static final long serialVersionUID =  3236104488444211128L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "subject_id" )
	private Integer subjectId = null;

   	@Column(name = "product_id" )
	private Integer productId = null;

	@Override
	public String toString() {
		return "CmsSubjectProductRelation{" +
				"id=" + id +
				", subjectId=" + subjectId +
				", productId=" + productId +
				'}';
	}

	/************************************************************/

	@ManyToOne
	@JoinColumn(name = "subject_id",insertable = false,updatable = false)
	private CmsSubject cmsSubject;
}
