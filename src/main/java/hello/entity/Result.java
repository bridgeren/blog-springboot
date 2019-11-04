package hello.entity;

/**
 * creat by nickless
 *
 * @Date 2019/10/26 17:03
 */
public abstract class Result<T> {
    String status;
    String msg;
    /* boolean isLogin;*/
    T data;
   /* public static Result success(String message,User user){
        return  new Result("ok",message,true,user);
    }
    public static Result failure(String message){
        return  new Result("fail",message,false);
    }*/

    protected Result(String status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = (T) data;
    }

    protected Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

   /* public Result(String status, String msg, boolean isLogin) {
        this.status = status;
        this.msg = msg;
        this.isLogin = isLogin;
    }

    public Result(String status, String msg, boolean isLogin, Object data) {
        this.status = status;
        this.msg = msg;
        this.isLogin = isLogin;
        this.data = data;
    }*/

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    /*public boolean isLogin() {
        return isLogin;
    }*/

    public Object getData() {
        return data;
    }
}



