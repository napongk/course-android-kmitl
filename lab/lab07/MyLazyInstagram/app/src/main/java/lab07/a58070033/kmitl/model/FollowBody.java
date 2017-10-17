package lab07.a58070033.kmitl.model;

/**
 * Created by napkkk on 18/10/2560.
 */

public class FollowBody {
    private String user;
    private boolean isFollow;

    public FollowBody(String android, boolean b) {
        user = android;
        isFollow = b;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }
}
