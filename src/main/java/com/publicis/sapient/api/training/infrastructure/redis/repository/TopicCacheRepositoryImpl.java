package com.publicis.sapient.api.training.infrastructure.redis.repository;

import com.publicis.sapient.api.training.domain.entity.Topic;
import com.publicis.sapient.api.training.domain.repository.TopicCacheRepository;
import com.publicis.sapient.api.training.infrastructure.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TopicCacheRepositoryImpl implements TopicCacheRepository {

    private Logger logger = LoggerFactory.getLogger(TopicCacheRepositoryImpl.class);

    public static final String TABLE_TOPIC = "TABLE_TOPIC";
    public static final String TOPIC = "TOPIC_";

    private RedisUtil<Topic> redisUtil;

    @Autowired
    public TopicCacheRepositoryImpl(RedisUtil<Topic> redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public void putTopic(String key, Topic topic) {
        redisUtil.putMap(TABLE_TOPIC, TOPIC + key, topic);
    }

    @Override
    public Topic getTopic(String key) {
        return redisUtil.getMap(TABLE_TOPIC, TOPIC + key);
    }

    @Override
    public Long deleteTopic(String key) {
        return redisUtil.deleteMap(TABLE_TOPIC, TOPIC + key);
    }
}
