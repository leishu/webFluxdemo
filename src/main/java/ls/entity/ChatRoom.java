package ls.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by leishu on 17-6-6.
 */
@Document
public class ChatRoom {
    @Id
    String room;

    String desc;

    public ChatRoom() {
    }

    public ChatRoom(String room, String desc) {
        this.room = room;
        this.desc = desc;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
