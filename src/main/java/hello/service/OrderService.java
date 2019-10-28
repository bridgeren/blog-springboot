package hello.service;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * creat by nickless
 *
 * @Date 2019/10/19 8:54
 */
@Service
public class OrderService {
    private UserService userService;

    @Inject
    public OrderService(UserService userService) {
        this.userService = userService;
    }

 /*   public void placeOrder(Integer userId, String item) {

        UserService.getUserById(userId);
    }
*/
}
