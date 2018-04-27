package spittr.db.hibernate4;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class RepositoryTestConfig implements TransactionManagementConfigurer {

  @Inject
  private SessionFactory sessionFactory;

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
    edb.setType(EmbeddedDatabaseType.H2);
    edb.addScript("spittr/db/hibernate4/schema.sql");
    edb.addScript("spittr/db/hibernate4/test-data.sql");
    EmbeddedDatabase embeddedDatabase = edb.build();
    return embeddedDatabase;
  }

  public PlatformTransactionManager annotationDrivenTransactionManager() {
    System.out.println(sessionFactory);
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory);
    return transactionManager;
  }

  @Bean//SessionFactory主要负责 HibernateSession的打开、关闭、以及管理，
  // HibernateSession提供了基本的数据访问功能，例如保存 更新 删除以及数据库加载对象的功能
  public SessionFactory sessionFactoryBean() {
    try {
      // 总共三种获取SessionFactory选择。除了当前的hibernate4.LocalSessionFactoryBean,还有hibernate3的同名类 以及AnnotationSessionFactory
      LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
      lsfb.setDataSource(dataSource());
      lsfb.setPackagesToScan("spittr.domain");
      Properties props = new Properties();
      props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
      lsfb.setHibernateProperties(props);
      lsfb.afterPropertiesSet();
      SessionFactory object = lsfb.getObject();
      return object;
    } catch (IOException e) {
      return null;
    }
  }
}
