package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsAlbum;
import com.axiaobug.pojo.pms.PmsAlbumPic;
import com.axiaobug.repository.pms.PmsAlbumPicRepository;
import com.axiaobug.repository.pms.PmsAlbumRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AlbumTest {

    @Autowired
    private PmsAlbumPicRepository pmsAlbumPicRepository;

    @Autowired
    private PmsAlbumRepository pmsAlbumRepository;

    @Test
    public void albumTest(){
        PmsAlbum album = new PmsAlbum();
        album.setName("测试一");
        album.setPicCount(3);
        pmsAlbumRepository.save(album);
        PmsAlbumPic albumPic = new PmsAlbumPic();
        albumPic.setAlbumId(1);
        pmsAlbumPicRepository.save(albumPic);
    }

}
