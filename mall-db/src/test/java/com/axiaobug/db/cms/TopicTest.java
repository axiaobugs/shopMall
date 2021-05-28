package com.axiaobug.db.cms;

import com.axiaobug.pojo.cms.CmsTopic;
import com.axiaobug.pojo.cms.CmsTopicCategory;
import com.axiaobug.pojo.cms.CmsTopicComment;
import com.axiaobug.repository.cms.CmsTopicCategoryRepository;
import com.axiaobug.repository.cms.CmsTopicCommentRepository;
import com.axiaobug.repository.cms.CmsTopicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootTest

@Transactional
public class TopicTest {
    
    @Autowired
    private CmsTopicRepository cmsTopicRepository;
    
    @Autowired
    private CmsTopicCommentRepository cmsTopicCommentRepository;

    @Autowired
    private CmsTopicCategoryRepository cmsTopicCategoryRepository;
    
    
    @Test
    public void topicTest(){
        long expected = cmsTopicRepository.count();
        CmsTopic topic = new CmsTopic();
        topic.setAwardName("axiaobug");
        cmsTopicRepository.save(topic);
//        Assert.assertEquals(expected+1,cmsTopicRepository.count());
    }

    @Test
    public void topicCommentTest(){
        long expected = cmsTopicCommentRepository.count();
        CmsTopicComment topicComment = new CmsTopicComment();
        topicComment.setMemberNickname("Yanxiao Fang");
        cmsTopicCommentRepository.save(topicComment);
//        Assert.assertEquals(expected+1,cmsTopicCommentRepository.count());
    }

    @Test
    public void topicCategoryTest(){
        long expected = cmsTopicCategoryRepository.count();
        CmsTopicCategory topicCategory = new CmsTopicCategory();
        topicCategory.setName("Happy");
        cmsTopicCategoryRepository.save(topicCategory);
//        Assert.assertEquals(expected+1,cmsTopicCategoryRepository.count());
    }
}
