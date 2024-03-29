package com.jm.springboot.web.domain.posts;

import com.jm.springboot.domain.posts.PostRepository;
import com.jm.springboot.domain.posts.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanUp(){
        postRepository.deleteAll();
    }

    @Test
    public void getArticle(){
        String title = "테스트게시글";
        String content = "테스트 본문";
        postRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("drivenbeat@gmail.com")
                        .build());

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("drivenbeat@kakao.com")
                .build());

        //when
        List<Posts> postsList = postRepository.findAll();
        for (Posts post : postsList){
            System.out.println(post.getAuthor());
        }

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
