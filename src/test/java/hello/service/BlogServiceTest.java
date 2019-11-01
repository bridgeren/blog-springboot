package hello.service;

import hello.dao.BlogDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;

/**
 * creat by nickless
 *
 * @Date 2019/10/28 21:45
 */
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
    @Mock
    BlogDao blogDao;
    @InjectMocks
    BlogService blogService;



    @Test
    public void getBlogsFromDb() {
        blogService.getBlogs(1, 10, null);

        Mockito.verify(blogDao).getBlogs(1, 10, null);
    }

}
