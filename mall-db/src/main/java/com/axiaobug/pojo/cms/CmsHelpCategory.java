package com.axiaobug.pojo.cms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Discription: 帮助管理分类(帮助管理是从表)
 * fk which in table named cms_help point to this table
 * @Author: Yanxiao Fang
 * @Date: 2021/05/25 17:14
 */
@Data
@Entity
@Table (name = "cms_help_category")
@NoArgsConstructor
@AllArgsConstructor
public class CmsHelpCategory implements Serializable {

	private static final long serialVersionUID =  2616445215833455749L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name = null;

	private String icon = null;

   	@Column(name = "help_count" )
	private Integer helpCount = null;

   	@Column(name = "show_status" )
	private Integer showStatus = null;

	private Integer sort = null;

	@Override
	public String toString() {
		return "CmsHelpCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", icon='" + icon + '\'' +
				", helpCount=" + helpCount +
				", showStatus=" + showStatus +
				", sort=" + sort +
				'}';
	}

	/***********************************************************/

	@OneToMany(mappedBy = "cmsHelpCategory")
	private List<CmsHelp> helps = new ArrayList<>();

}
