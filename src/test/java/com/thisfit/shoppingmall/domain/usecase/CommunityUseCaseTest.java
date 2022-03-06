package com.thisfit.shoppingmall.domain.usecase;

import com.thisfit.shoppingmall.user.community.domain.usecase.CommunityUseCase;
import com.thisfit.shoppingmall.user.community.domain.vo.CommunityDetailVO;
import com.thisfit.shoppingmall.user.community.domain.vo.CommunityVO;
import com.thisfit.shoppingmall.user.community.repository.datasource.Community;
import com.thisfit.shoppingmall.user.community.repository.datasource.CommunityJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityUseCaseTest {

    @Autowired
    private CommunityUseCase communityUseCase;

    @Autowired
    private CommunityJpaRepository communityJpaRepository;

    private String title = "title";
    private String content = "content";
    private String writer = "writer";
    private String category = "category";

    @Before
    public void saveCommunity() {
        Community community = Community.builder()
                                       .title(title)
                                       .content(content)
                                       .writer(writer)
                                       .category(category)
                                       .build();

        communityJpaRepository.save(community);
    }

    @After
    public void cleanup() {
        communityJpaRepository.deleteAll();
    }

    // 커뮤니티 리스트
    @Test
    public void getCommListTest() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        List<CommunityVO> communityVOList = communityUseCase.getCommList(category, pageable);

        // then
        CommunityVO communityVO = communityVOList.get(0);

        assertThat(communityVO.getTitle()).isEqualTo(title);
        assertThat(communityVO.getWriter()).isEqualTo(writer);
        assertThat(communityVO.getCategory()).isEqualTo(category);
    }

    // 커뮤니티 디테일
    @Test
    public void getCommDetailTest() {
        // given
        int no = communityJpaRepository.findLastNo();

        // when
        CommunityDetailVO communityDetailVO = communityUseCase.getCommDetail(no);

        // then
        assertThat(communityDetailVO.getTitle()).isEqualTo(title);
        assertThat(communityDetailVO.getContent()).isEqualTo(content);
        assertThat(communityDetailVO.getWriter()).isEqualTo(writer);
        assertThat(communityDetailVO.getCategory()).isEqualTo(category);
    }

}
