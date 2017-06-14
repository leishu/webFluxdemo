package ls.entity.output;

import ls.entity.Online;

import java.util.List;

/**
 * Created by leishu on 17-6-14.
 */
public class LoginResult {
    Integer result;
    String cause;
    List<Online> onlines;

    public LoginResult(Integer result) {
        this.result = result;
    }

    public LoginResult(Integer result, List<Online> onlines) {
        this.result = result;
        this.onlines = onlines;
    }

    public LoginResult(Integer result, String cause) {
        this.result = result;
        this.cause = cause;
    }

    public Integer getResult() {
        return result;
    }

    public String getCause() {
        return cause;
    }

    public List<Online> getOnlines() {
        return onlines;
    }
}
