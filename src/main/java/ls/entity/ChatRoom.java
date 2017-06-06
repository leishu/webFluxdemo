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

    public ChatRoom() {
    }

    public ChatRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
