package com.axiaobug.db.pms;

import com.axiaobug.pojo.pms.PmsComment;
import com.axiaobug.pojo.pms.PmsCommentReplay;
import com.axiaobug.repository.pms.PmsCommentReplayRepository;
import com.axiaobug.repository.pms.PmsCommentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CommentTest {

    @Autowired
    private PmsCommentRepository pmsCommentRepository;

    @Autowired
    private PmsCommentReplayRepository pmsCommentReplayRepository;

    @Test
    public void commentReplayTest(){

        PmsCommentReplay commentReplay = new PmsCommentReplay();
        commentReplay.setCreateTime(new Date());
        pmsCommentReplayRepository.save(commentReplay);
        Assert.assertEquals(1,pmsCommentReplayRepository.count());
    }


    @Test
    public void commentTest(){
        PmsComment comment = new PmsComment();
        comment.setCreateTime(new Date());
        pmsCommentRepository.save(comment);
        Assert.assertEquals(1,pmsCommentRepository.count());
    }

}
