package org.springframework.boot.autoconfigure.data.ultipa;

import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.data.ultipa.repository.config.EnableUltipaRepositories;
import org.springframework.data.ultipa.repository.config.UltipaRepositoryConfigurationExtension;

import java.lang.annotation.Annotation;

/**
 * {@link ImportBeanDefinitionRegistrar} used to auto-configure Spring Data Ultipa Repositories.
 *
 * @author Wangwang Tang
 * @since 1.0
 */
public class UltipaRepositoriesRegistrar extends AbstractRepositoryConfigurationSourceSupport {
    protected Class<? extends Annotation> getAnnotation() {
        return EnableUltipaRepositories.class;
    }

    protected Class<?> getConfiguration() {
        return EnableUltipaRepositoriesConfiguration.class;
    }

    protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
        return new UltipaRepositoryConfigurationExtension();
    }

    @EnableUltipaRepositories
    private static class EnableUltipaRepositoriesConfiguration {

    }
}
