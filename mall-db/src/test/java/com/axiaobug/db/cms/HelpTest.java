package com.axiaobug.db.cms;

import com.axiaobug.pojo.cms.CmsHelp;
import com.axiaobug.pojo.cms.CmsHelpCategory;
import com.axiaobug.repository.cms.CmsHelpCategoryRepository;
import com.axiaobug.repository.cms.CmsHelpRepository;
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
public class HelpTest {

    @Autowired
    private CmsHelpCategoryRepository cmsHelpCategoryRepository;

    @Autowired
    private CmsHelpRepository cmsHelpRepository;


    @Test
    public void helpTest(){
        long expected = cmsHelpRepository.count();
        CmsHelp cmsHelp = new CmsHelp();
        cmsHelp.setTitle("haha");
        cmsHelpRepository.save(cmsHelp);
//        Assert.assertEquals(expected+1,cmsHelpRepository.count());
    }

    @Test
    public void helpCategoryTest(){
        long expected = cmsHelpCategoryRepository.count();
        CmsHelpCategory helpCategory = new CmsHelpCategory();
        helpCategory.setName("nana");
        cmsHelpCategoryRepository.save(helpCategory);
//        Assert.assertEquals(expected+1,cmsHelpCategoryRepository.count());
    }


}
