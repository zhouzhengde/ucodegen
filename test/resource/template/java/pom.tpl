<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.7.RELEASE</version>
        <relativePath/>
    </parent>

    <modelVersion>4.0.0</modelVersion>
    <groupId>${root.basePackage}</groupId>
    <artifactId>${root.appName}</artifactId>
    <packaging>jar</packaging>

    <name>console Maven Webapp</name>
    <url>http://maven.apache.org</url>



    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <start.class>${root.applicationRunner}</start.class>
        <version>0.0.1-SNAPSHOT</version>
        <java.version>1.8</java.version>
        <shiro.version>1.3.2</shiro.version>
        <jackson.version>2.6.5</jackson.version>
        <swagger.version>2.4.0</swagger.version>
        <freemarker.version>2.3.23</freemarker.version>
        <junit.version>4.11</junit.version>
        <mybatis.version>3.3.0</mybatis.version>
        <validation.version>5.3.5.Final</validation.version>
        <spring.boot.version>1.3.7.RELEASE</spring.boot.version>
        <spring.cloud.netfix.version>1.3.1.RELEASE</spring.cloud.netfix.version>
        <spring.cloud.version>Brixton.SR5</spring.cloud.version>
        <hystrix.version>1.5.3</hystrix.version>
        <lombox.version>1.16.18</lombox.version>
        <pinyin4j.version>2.5.0</pinyin4j.version>
        <javaee.version>7.0</javaee.version>
        <httpclient.version>4.5.3</httpclient.version>
        <springside.version>5.0.0-RC1</springside.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>${root.dollar}{spring.boot.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>${root.dollar}{spring.boot.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
            <version>${root.dollar}{spring.boot.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${root.dollar}{spring.boot.version}</version>
            <type>pom</type>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${root.dollar}{junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- mybatis & mysql -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${root.dollar}{mybatis.version}</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.21</version>
        </dependency>
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP-java6</artifactId>
            <version>2.3.12</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.0.0</version>
        </dependency>

        <!-- json -->
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <version>2.2.0</version>
        </dependency>
        <dependency>
            <groupId>net.sf.jxls</groupId>
            <artifactId>jxls-core</artifactId>
            <version>1.0.6</version>
        </dependency>
        <dependency>
            <groupId>net.sf.jxls</groupId>
            <artifactId>jxls-reader</artifactId>
            <version>1.0.6</version>
        </dependency>
        <dependency>
            <groupId>net.sf.json-lib</groupId>
            <artifactId>json-lib</artifactId>
            <version>2.2.2</version>
            <classifier>jdk15</classifier>
        </dependency>

        <!-- freemarker -->
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>${root.dollar}{freemarker.version}</version>
        </dependency>

        <!-- 加密 -->
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-ext-jdk15on</artifactId>
            <version>1.54</version>
        </dependency>
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.6</version>
        </dependency>

        <dependency>
            <groupId>io.springside</groupId>
            <artifactId>springside-utils</artifactId>
            <version>${root.dollar}{springside.version}</version>
        </dependency>

        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>${root.dollar}{httpclient.version}</version>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${root.dollar}{swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${root.dollar}{swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${root.dollar}{jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${root.dollar}{jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${root.dollar}{jackson.version}</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>${root.dollar}{validation.version}</version>
        </dependency>

        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>${root.dollar}{javaee.version}</version>
        </dependency>

        <dependency>
            <groupId>com.belerweb</groupId>
            <artifactId>pinyin4j</artifactId>
            <version>${root.dollar}{pinyin4j.version}</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${root.dollar}{lombox.version}</version>
        </dependency>

    </dependencies>
    <build>
        <finalName>${root.appName}</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${root.dollar}{java.version}</source>
                    <target>${root.dollar}{java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${root.dollar}{spring.boot.version}</version>
                <configuration>
                    <mainClass>${root.dollar}{start.class}</mainClass>
                    <layout>JAR</layout>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>