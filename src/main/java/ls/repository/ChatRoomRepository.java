package ls.repository;

import ls.entity.ChatRoom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Created by leishu on 17-6-6.
 */
public interface ChatRoomRepository extends ReactiveCrudRepository<ChatRoom, String> {

}
