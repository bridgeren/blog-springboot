package hello.entity;

/**
 * creat by nickless
 *
 * @Date 2019/11/3 9:05
 */
public class LoginResult extends Result<User> {
    boolean isLogin;


    public static LoginResult success(String msg, User user, boolean isLogin) {
        return new LoginResult("ok", msg, user, isLogin);
    }

    public static LoginResult success(String msg, boolean isLogin) {
        return success(msg, null, isLogin);
    }

    public static LoginResult success(String msg, User user) {
        return success(msg, user, true);
    }

    public static LoginResult failure(String message) {
        return new LoginResult("fail", message, null, false);
    }

    public LoginResult(String status, String msg, User user, boolean isLogin) {
        super(status, msg, user);
        this.isLogin = isLogin;
    }


    public boolean isLogin() {
        return isLogin;
    }
}
