/*
package hello;

import hello.entity.User;
import hello.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * creat by nickless
 *
 * @Date 2019/10/18 10:45
 *//*

@RestController
public class HelloController {
    */
/*  private OrderService orderService;

      @Inject
      public HelloController(OrderService orderService) {
          this.orderService = orderService;
      }

      @RequestMapping("/")
      public String index() {
          return "hello nickless";
      }*//*

    private UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public User index() {
        return this.userService.getUserById(1);
    }

}
*/
