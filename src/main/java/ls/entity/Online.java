package ls.entity;

import ls.redis.RedisSet;
import org.springframework.data.annotation.Id;

/**
 * Created by leishu on 17-6-7.
 */
@RedisSet("onlines-")
public class Online {
    @Id
    String room;
    String user;

    public Online() {
    }

    public Online(String room) {
        this.room = room;
    }

    public Online(String room, String user) {
        this.room = room;
        this.user = user;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
