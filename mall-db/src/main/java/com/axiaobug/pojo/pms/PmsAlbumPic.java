package com.axiaobug.pojo.pms;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 * @Author: Yanxiao Fang
 * @Date: 2021/05/23 11:14
 */
@Data
@Entity
@Table (name = "pms_album_pic")
@NoArgsConstructor
@AllArgsConstructor
public class PmsAlbumPic implements Serializable {

	private static final long serialVersionUID =  78631445560191677L;

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

   	@Column(name = "album_id" )
	private Integer albumId = null;

	private String pic = null;

	@ManyToOne
	@JoinColumn(name = "album_id",insertable = false,updatable = false)
	private PmsAlbum pmsAlbum;

}
