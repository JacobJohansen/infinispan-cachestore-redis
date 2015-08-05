package org.infinispan.persistence.redis.client;

import redis.clients.jedis.JedisPool;

public class RedisServerConnectionPool implements RedisConnectionPool
{
    private JedisPool connectionPool;
    private RedisMarshaller<String> marshaller;

    public RedisServerConnectionPool(RedisMarshaller<String> marshaller)
    {
//        final GenericObjectPoolConfig poolConfig,
//        final String host,
//        int port,
//        final int connectionTimeout,
//        final int soTimeout,
//        final String password,
//        final int database,
//        final String clientName

        this.connectionPool = new JedisPool();
        this.marshaller = marshaller;
    }

    @Override
    public RedisConnection getConnection()
    {
        return new RedisServerConnection(this.connectionPool.getResource(), this.marshaller);
    }

    @Override
    public void shutdown()
    {
        this.connectionPool.destroy();
    }
}
