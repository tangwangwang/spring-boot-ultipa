package com.tangwangwang.spring.boot.autoconfigure.data.ultipa;

import com.tangwangwang.spring.boot.autoconfigure.ultipa.UltipaAutoConfiguration;
import com.tangwangwang.spring.boot.autoconfigure.ultipa.UltipaProperties;
import com.tangwangwang.spring.data.ultipa.annotation.Edge;
import com.tangwangwang.spring.data.ultipa.annotation.Node;
import com.tangwangwang.spring.data.ultipa.core.UltipaOperations;
import com.tangwangwang.spring.data.ultipa.core.UltipaTemplate;
import com.tangwangwang.spring.data.ultipa.core.convert.MappingUltipaConverter;
import com.tangwangwang.spring.data.ultipa.core.convert.UltipaConverter;
import com.tangwangwang.spring.data.ultipa.core.convert.UltipaCustomConversions;
import com.tangwangwang.spring.data.ultipa.core.mapping.UltipaMappingContext;
import com.tangwangwang.spring.data.ultipa.repository.config.UltipaRepositoryConfigurationExtension;
import com.ultipa.sdk.connect.driver.UltipaClientDriver;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mapping.model.FieldNamingStrategy;

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
    public UltipaMappingContext ultipaMappingContext(UltipaProperties properties,
                                                     ApplicationContext applicationContext, UltipaCustomConversions conversions) throws ClassNotFoundException {
        UltipaMappingContext context = new UltipaMappingContext();

        context.setValidate(properties.getValidateSchema());
        context.setGenerate(properties.getGenerateSchema());

        context.setInitialEntitySet(new EntityScanner(applicationContext).scan(Node.class, Edge.class));
        Class<? extends FieldNamingStrategy> strategyClass = properties.getFieldNamingStrategy();
        if (strategyClass != null) {
            context.setFieldNamingStrategy(BeanUtils.instantiateClass(strategyClass));
        }

        context.setSimpleTypeHolder(conversions.getSimpleTypeHolder());
        return context;
    }

    @Bean
    @ConditionalOnMissingBean
    public UltipaConverter ultipaConverter(UltipaMappingContext mappingContext) {
        return new MappingUltipaConverter(mappingContext);
    }

    @Bean(UltipaRepositoryConfigurationExtension.DEFAULT_ULTIPA_TEMPLATE_BEAN_NAME)
    @ConditionalOnMissingBean(value = UltipaOperations.class, name = UltipaRepositoryConfigurationExtension.DEFAULT_ULTIPA_TEMPLATE_BEAN_NAME)
    public UltipaTemplate ultipaTemplate(UltipaProperties properties,
                                         UltipaClientDriver clientDriver,
                                         UltipaConverter converter) {
        boolean useLeader = Optional.ofNullable(properties.getUseLeader()).orElse(false);
        return new UltipaTemplate(clientDriver, converter, useLeader);
    }

}
