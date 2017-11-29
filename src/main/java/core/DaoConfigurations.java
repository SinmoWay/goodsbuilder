package core;

import db.dao.ContentDao;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DaoConfigurations {

    @Bean
    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("application.yml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(ssrb.build());
    }

    @Bean
    public ContentDao getContentDao() {
        return new ContentDao();
    }

}