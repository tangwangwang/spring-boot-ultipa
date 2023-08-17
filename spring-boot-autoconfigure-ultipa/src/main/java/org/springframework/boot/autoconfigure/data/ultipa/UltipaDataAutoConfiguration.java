package org.springframework.boot.autoconfigure.data.ultipa;

import com.ultipa.sdk.connect.driver.UltipaClientDriver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.ultipa.UltipaAutoConfiguration;
import org.springframework.boot.autoconfigure.ultipa.UltipaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.ultipa.core.UltipaOperations;
import org.springframework.data.ultipa.core.UltipaTemplate;
import org.springframework.data.ultipa.core.convert.MappingUltipaConverter;
import org.springframework.data.ultipa.core.convert.UltipaConverter;
import org.springframework.data.ultipa.core.convert.UltipaCustomConversions;
import org.springframework.data.ultipa.core.mapping.UltipaMappingContext;
import org.springframework.data.ultipa.repository.config.UltipaRepositoryConfigurationExtension;

import java.util.Optional;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Data Ultipa.
 *
 * @author Wangwang Tang
 * @since 1.0
 */
@AutoConfiguration(after = UltipaAutoConfiguration.class)
@ConditionalOnClass({UltipaClientDriver.class, UltipaOperations.class})
@EnableConfigurationProperties(UltipaProperties.class)
@ConditionalOnBean(UltipaClientDriver.class)
public class UltipaDataAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    UltipaCustomConversions ultipaCustomConversions() {
        return new UltipaCustomConversions();
    }

    @Bean
    @ConditionalOnMissingBean
    public UltipaMappingContext ultipaMappingContext() {
        return new UltipaMappingContext();
    }

    @Bean
    @ConditionalOnMissingBean
    public UltipaConverter ultipaConverter(UltipaMappingContext mappingContext) {
        return new MappingUltipaConverter(mappingContext);
    }

    @Bean(UltipaRepositoryConfigurationExtension.DEFAULT_ULTIPA_TEMPLATE_BEAN_NAME)
    @ConditionalOnMissingBean(value = UltipaOperations.class, name = UltipaRepositoryConfigurationExtension.DEFAULT_ULTIPA_TEMPLATE_BEAN_NAME)
    public UltipaTemplate ultipaTemplate(UltipaProperties ultipaProperties,
                                         UltipaClientDriver clientDriver,
                                         UltipaConverter converter) {
        boolean useLeader = Optional.ofNullable(ultipaProperties.getUseLeader()).orElse(false);
        return new UltipaTemplate(clientDriver, converter, useLeader);
    }

}
