package com.axiaobug.repository.pms;

import com.axiaobug.pojo.pms.PmsAlbumPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
public interface PmsAlbumPicRepository extends JpaRepository<PmsAlbumPic,Integer>, JpaSpecificationExecutor<PmsAlbumPic> {
}
