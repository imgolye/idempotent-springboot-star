package org.link.redis.config;

import org.link.core.constant.IdempotentConstant;
import org.link.core.utils.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis 缓存配置
 * @Author: Gol
 */
@Configuration
public class IdempotentRedisAutoConfiguration {


    @Value("${spring.redis.cluster.nodes:}")
    private String[] clusterNodes;

    @Value("${spring.redis.sentinel.nodes:}")
    private String[] sentinelNodes;

    @Value("${spring.redis.sentinel.master:}")
    private String masterName;

    @Value("${spring.redis.password:}")
    private String password;

    @Value("${spring.redis.host:127.0.0.1}")
    private String singleHost;

    @Value("${spring.redis.port:6379}")
    private String singlePort;

    @Value("${spring.redis.database:0}")
    private Integer singleDatabase;

    /**
     * Redisson 配置
     *
     * @return org.redisson.api.RedissonClient
     */
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient creatRedissonClient() {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec());
        if (null != clusterNodes && clusterNodes.length > 0) {
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            clusterServersConfig.addNodeAddress(clusterNodes);
            clusterServersConfig.setConnectTimeout(3000).setSubscriptionsPerConnection(50000);
            if (StringUtils.isNotBlank(password)) {
                clusterServersConfig.setPassword(password);
            }
            return Redisson.create(config);
        }
        if (null != sentinelNodes && sentinelNodes.length > 0) {
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            sentinelServersConfig.addSentinelAddress(sentinelNodes);
            if (StringUtils.isNotBlank(password)) {
                sentinelServersConfig.setPassword(password);
            }
            if (StringUtils.isNotBlank(masterName)) {
                sentinelServersConfig.setMasterName(masterName);
            }
            sentinelServersConfig.setConnectTimeout(3000).setSubscriptionsPerConnection(50000);
            return Redisson.create(config);
        }
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(StringUtils.join(IdempotentConstant.DEFAULT_REDIS_LINK, singleHost, IdempotentConstant.IDEMPOTENT_SPLIT, singlePort));
        if (StringUtils.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        singleServerConfig.setDatabase(singleDatabase == null ? 0 : singleDatabase);
        singleServerConfig.setConnectTimeout(3000).setSubscriptionsPerConnection(5000);
        return Redisson.create(config);
    }
}
