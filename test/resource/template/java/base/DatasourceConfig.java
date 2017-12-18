package ${root.basePackage}.common.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@MapperScan(value = {"${root.basePackage}.*.dao"})
public class DatasourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private MybatisProperties mybatisProperties;

    @Autowired
    private com.ivxyz.um.common.config.PageHelperProperties pageHelperProperties;


    @Bean
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(dataSourceProperties.getDriverClassName());
        config.setJdbcUrl(dataSourceProperties.getUrl());
        config.setUsername(dataSourceProperties.getUsername());
        config.setPassword(dataSourceProperties.getPassword());
        config.setAutoCommit(false);
        return new HikariDataSource(config);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @PostConstruct
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());


        Interceptor[] interceptors = new Interceptor[]{pageHelperProperties.getPageInterceptor()};
        sqlSessionFactoryBean.setPlugins(interceptors);

        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.getMapperLocations());

        return sqlSessionFactoryBean.getObject();
    }

}
