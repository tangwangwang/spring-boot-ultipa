package org.springframework.boot.autoconfigure.ultipa;

import com.ultipa.sdk.connect.driver.DataSource;
import com.ultipa.sdk.connect.driver.UltipaClientDriver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Ultipa.
 *
 * @author Wangwang Tang
 * @since 1.0
 */
@AutoConfiguration
@ConditionalOnClass({UltipaClientDriver.class})
@EnableConfigurationProperties(UltipaProperties.class)
public class UltipaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(UltipaClientDriver.class)
    public UltipaClientDriver ultipaClientDriver(UltipaProperties properties) {
        DataSource dataSource = generateUltipaDataSource(properties);
        return new UltipaClientDriver(dataSource);
    }

    private DataSource generateUltipaDataSource(UltipaProperties properties) {
        DataSource dataSource = new DataSource();
        dataSource.setTimeout(properties.getTimeout());
        dataSource.setUrl(properties.getHosts());
        dataSource.setUsername(properties.getUsername());
        dataSource.setCrt(properties.getCrt());
        dataSource.setOverrideAuthority(properties.getOverrideAuthority());
        dataSource.setPassword(properties.getPassword());
        dataSource.setConsistency(properties.getConsistency());
        dataSource.setKeepAlive(properties.getKeepAlive());
        dataSource.setKeepAliveWithoutCalls(properties.getKeepAliveWithoutCalls());
        dataSource.setHeartbeat(properties.getHeartbeat());
        dataSource.setDefaultGraph(properties.getDefaultGraph());
        dataSource.getUltipaConfiguration().setCurrentGraph(properties.getCurrentGraph());
        dataSource.setMaxRecvSize(properties.getMaxRecvSize());
        //连接池相关属性
        if (properties.getPool() != null) {
            dataSource.setTimeBetweenEvictionRunsMillis(properties.getPool().getTimeBetweenEvictionRunsMillis());
            dataSource.setMaxIdle(properties.getPool().getMaxIdle());
            dataSource.setMinIdle(properties.getPool().getMinIdle());
            dataSource.setMaxTotal(properties.getPool().getMaxTotal());
            dataSource.setMinEvictableIdleTimeMillis(properties.getPool().getMinEvictableIdleTimeMillis());
            dataSource.setTestOnBorrow(properties.getPool().isTestOnBorrow());
            dataSource.setTestOnReturn(properties.getPool().isTestOnReturn());
            dataSource.setTestWhileIdle(properties.getPool().isTestWhileIdle());
            dataSource.setMaxWaitMillis(properties.getPool().getMaxIdle());
            dataSource.setLifo(properties.getPool().isLifo());
            dataSource.setBlockWhenExhausted(properties.getPool().isBlockWhenExhausted());
            dataSource.setNumTestsPerEvictionRun(properties.getPool().getNumTestsPerEvictionRun());
        }
        return dataSource;
    }

}
