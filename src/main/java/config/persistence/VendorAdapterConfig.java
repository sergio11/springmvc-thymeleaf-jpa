/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author sergio
 */
@Configuration
public class VendorAdapterConfig {
    
    @Autowired
    private Environment env;
    
    @Profile("development")
    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter provideJpaVendorAdapterDev() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.DERBY);
        adapter.setShowSql(env.getProperty("hibernate.dev.show_sql", Boolean.class));
        adapter.setGenerateDdl(env.getProperty("hibernate.dev.hbm2ddl.auto", Boolean.class));
        adapter.setDatabasePlatform(env.getProperty("hibernate.dev.dialect"));
        return adapter;
    }
}
