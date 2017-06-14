package ls.repository;

import ls.entity.Online;
import ls.redis.RedisRepository;

/**
 * Created by leishu on 17-6-7.
 */
public interface LoginRepository extends RedisRepository<Online> {

}
