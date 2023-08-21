package org.springframework.boot.autoconfigure.ultipa;

import com.ultipa.sdk.operate.constant.Constant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.PropertyNameFieldNamingStrategy;

/**
 * Configuration properties for Ultipa.
 *
 * @author Wangwang Tang
 * @since 1.0
 */
@ConfigurationProperties(prefix = "spring.data.ultipa")
public class UltipaProperties {

    private String hosts;

    /**
     * query timeout, seconds
     */
    private Integer timeout = Constant.DEFAULT_TIME_OUT;

    private Boolean consistency = Boolean.FALSE;

    private String username;

    private String password;

    private String crt;

    /**
     * when use ssl, will set domain of the crt in to this.
     */
    private String overrideAuthority;

    /**
     * if heartbeat > 0,will enable heartbeat
     */
    private Integer heartbeat = Constant.DEFAULT_HEARTBEAT;

    /**
     * how long will the channel keep alive, in seconds
     */
    private Integer keepAlive;

    /**
     * if true, enable keepAliveWithoutCalls of channel
     */
    private Boolean keepAliveWithoutCalls;

    /**
     * max message size in bytes in a frame
     */
    private Integer maxRecvSize;

    /**
     * Default graph set.
     */
    private String defaultGraph;

    /**
     * Current graph set.
     */
    private String currentGraph;

    /**
     * Whether to use master node query
     */
    private Boolean useLeader = false;

    /**
     * Fully qualified name of the FieldNamingStrategy to use. Defaults to a strategy using the plain property name {@link PropertyNameFieldNamingStrategy}.
     */
    private Class<? extends FieldNamingStrategy> fieldNamingStrategy;

    /**
     * Whether to validate schemas and properties
     */
    public Boolean validateSchema = false;

    /**
     * Whether to auto generate non-existent schemas and properties
     */
    public Boolean generateSchema = false;

    /**
     * pool config
     */
    private UltipaPoolProperties pool = new UltipaPoolProperties();

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean getConsistency() {
        return consistency;
    }

    public void setConsistency(Boolean consistency) {
        this.consistency = consistency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }

    public String getOverrideAuthority() {
        return overrideAuthority;
    }

    public void setOverrideAuthority(String overrideAuthority) {
        this.overrideAuthority = overrideAuthority;
    }

    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getKeepAliveWithoutCalls() {
        return keepAliveWithoutCalls;
    }

    public void setKeepAliveWithoutCalls(Boolean keepAliveWithoutCalls) {
        this.keepAliveWithoutCalls = keepAliveWithoutCalls;
    }

    public Integer getMaxRecvSize() {
        return maxRecvSize;
    }

    public void setMaxRecvSize(Integer maxRecvSize) {
        this.maxRecvSize = maxRecvSize;
    }

    public String getDefaultGraph() {
        return defaultGraph;
    }

    public void setDefaultGraph(String defaultGraph) {
        this.defaultGraph = defaultGraph;
    }

    public String getCurrentGraph() {
        return currentGraph;
    }

    public void setCurrentGraph(String currentGraph) {
        this.currentGraph = currentGraph;
    }

    public Boolean getUseLeader() {
        return useLeader;
    }

    public void setUseLeader(Boolean useLeader) {
        this.useLeader = useLeader;
    }

    public Class<? extends FieldNamingStrategy> getFieldNamingStrategy() {
        return fieldNamingStrategy;
    }

    public void setFieldNamingStrategy(Class<? extends FieldNamingStrategy> fieldNamingStrategy) {
        this.fieldNamingStrategy = fieldNamingStrategy;
    }

    public Boolean getValidateSchema() {
        return validateSchema;
    }

    public void setValidateSchema(Boolean validateSchema) {
        this.validateSchema = validateSchema;
    }

    public Boolean getGenerateSchema() {
        return generateSchema;
    }

    public void setGenerateSchema(Boolean generateSchema) {
        this.generateSchema = generateSchema;
    }

    public UltipaPoolProperties getPool() {
        return pool;
    }

    public void setPool(UltipaPoolProperties pool) {
        this.pool = pool;
    }

    public static class UltipaPoolProperties {
        /**
         * maxTotal configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private int maxTotal = 8;

        /**
         * maxIdle configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private int maxIdle = 8;

        /**
         * minIdle configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private int minIdle = 0;

        /**
         * minEvictableIdleTimeMillis configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private long minEvictableIdleTimeMillis = 1800000L;

        /**
         * timeBetweenEvictionRunsMillis configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private long timeBetweenEvictionRunsMillis = -1;

        /**
         * testOnBorrow configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private boolean testOnBorrow;

        /**
         * testOnReturn configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private boolean testOnReturn;

        /**
         * testWhileIdle configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private boolean testWhileIdle;

        /**
         * maxWaitMillis configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private long maxWaitMillis = -1;

        /**
         * lifo configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private boolean lifo = true;

        /**
         * blockWhenExhausted configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private boolean blockWhenExhausted = true;

        /**
         * numTestsPerEvictionRun configuration for pool,See commons-pool2 GenericObjectPoolConfig
         */
        private int numTestsPerEvictionRun = 3;

        public int getMaxTotal() {
            return maxTotal;
        }

        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public long getMinEvictableIdleTimeMillis() {
            return minEvictableIdleTimeMillis;
        }

        public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }

        public long getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public boolean isTestOnReturn() {
            return testOnReturn;
        }

        public void setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
        }

        public boolean isTestWhileIdle() {
            return testWhileIdle;
        }

        public void setTestWhileIdle(boolean testWhileIdle) {
            this.testWhileIdle = testWhileIdle;
        }

        public long getMaxWaitMillis() {
            return maxWaitMillis;
        }

        public void setMaxWaitMillis(long maxWaitMillis) {
            this.maxWaitMillis = maxWaitMillis;
        }

        public boolean isLifo() {
            return lifo;
        }

        public void setLifo(boolean lifo) {
            this.lifo = lifo;
        }

        public boolean isBlockWhenExhausted() {
            return blockWhenExhausted;
        }

        public void setBlockWhenExhausted(boolean blockWhenExhausted) {
            this.blockWhenExhausted = blockWhenExhausted;
        }

        public int getNumTestsPerEvictionRun() {
            return numTestsPerEvictionRun;
        }

        public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
            this.numTestsPerEvictionRun = numTestsPerEvictionRun;
        }
    }

}
