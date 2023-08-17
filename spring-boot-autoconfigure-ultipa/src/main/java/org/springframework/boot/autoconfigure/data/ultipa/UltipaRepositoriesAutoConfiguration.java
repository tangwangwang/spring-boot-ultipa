package org.springframework.boot.autoconfigure.data.ultipa;

import com.ultipa.sdk.connect.driver.UltipaClientDriver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.boot.autoconfigure.data.RepositoryType;
import org.springframework.context.annotation.Import;
import org.springframework.data.ultipa.repository.UltipaRepository;
import org.springframework.data.ultipa.repository.config.EnableUltipaRepositories;
import org.springframework.data.ultipa.repository.config.UltipaRepositoryConfigurationExtension;
import org.springframework.data.ultipa.repository.support.UltipaRepositoryFactoryBean;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Spring Data's Ultipa Repositories.
 * <p>
 * Activates when there is no bean of type {@link UltipaRepositoryFactoryBean} or
 * {@link UltipaRepositoryConfigurationExtension} configured in the context, the Spring
 * Data Ultipa {@link UltipaRepository} type is on the classpath, the Ultipa client driver
 * API is on the classpath, and there is no other configured {@link UltipaRepository}.
 * <p>
 * Once in effect, the auto-configuration is the equivalent of enabling Ultipa repositories
 * using the {@link EnableUltipaRepositories @EnableUltipaRepositories} annotation.
 *
 * @author Wangwang Tang
 * @since 1.0
 */
@AutoConfiguration(after = UltipaDataAutoConfiguration.class)
@ConditionalOnClass({UltipaClientDriver.class, UltipaRepository.class})
@ConditionalOnMissingBean({UltipaRepositoryFactoryBean.class, UltipaRepositoryConfigurationExtension.class})
@ConditionalOnRepositoryType(store = "ultipa", type = RepositoryType.IMPERATIVE)
@Import(UltipaRepositoriesRegistrar.class)
public class UltipaRepositoriesAutoConfiguration {
}
