package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/23 11:14
 */
@Data
@Entity
@Table (name = "pms_album")
@NoArgsConstructor
@AllArgsConstructor
public class PmsAlbum implements Serializable {

	private static final long serialVersionUID =  6488513129769155546L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name = null;

   	@Column(name = "cover_pic" )
	private String coverPic = null;

   	@Column(name = "pic_count" )
	private Integer picCount = null;

	private Integer sort = null;

	private String description = null;

	/***********************************************/

	@OneToMany(mappedBy = "pmsAlbum")
	private List<PmsAlbumPic> albumPics = new ArrayList<>();

}
