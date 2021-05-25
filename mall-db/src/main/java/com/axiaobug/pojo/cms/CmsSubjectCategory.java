package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Discription: subject category (one to many)
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 20:02
 */
@Data
@Entity
@Table (name = "cms_subject_category")
@NoArgsConstructor
@AllArgsConstructor
public class CmsSubjectCategory implements Serializable {

	private static final long serialVersionUID =  2749868589961100752L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "name" )
	private String name = null;

   	@Column(name = "icon" )
	private String icon = null;

	/**
     * subject amount
     * 专题数量
     * */
   	@Column(name = "subject_count" )
	private Integer subjectCount = null;

   	@Column(name = "show_status" )
	private Integer showStatus = null;

	private Integer sort = null;

    @Override
    public String toString() {
        return "CmsSubjectCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", subjectCount=" + subjectCount +
                ", showStatus=" + showStatus +
                ", sort=" + sort +
                '}';
    }

    /**********************************************************/

    @OneToMany(mappedBy = "cmsSubjectCategory")
    private List<CmsSubject> cmsSubjects = new ArrayList<>();
}
