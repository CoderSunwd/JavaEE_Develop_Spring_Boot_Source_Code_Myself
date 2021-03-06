package com.wisely.ch9_2.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.wisely.ch9_2.domain.Person;

@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {
    @Bean
    public ItemReader<Person> reader() throws Exception {
        // 使用 FlatFileItemReader 读取文件。
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        // 使用 FlatFileItemReader 的 setResource 方法设置 csv 文件的路径。
        reader.setResource(new ClassPathResource("people.csv"));
        // 在此处对 cvs 文件的数据和领域模型类做对应映射。
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "age", "nation", "address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});

        return reader;
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        CsvItemProcessor processor = new CsvItemProcessor(); // 使用我们自己定义的 ItemProcessor 的实现 CsvItemProcessor。
        processor.setValidator(csvBeanValidator()); // 为 processor 指定校验器为 CsvBeanValidator。
        return processor;
    }

    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) { // Spring 能让容器中已有的 Bean 以参数的形式注入，Spring Boot 已为我们定义了 dataSource。
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>(); // 我们使用 JDBC 批处理的 JdbcBatchItemWriter 来写数据到数据库。
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        String sql = "insert into person " + "(name,age,nation,address) "
                + "values(:name, :age, :nation,:address)";
        writer.setSql(sql); // 在此设置要执行批处理的 SQL 语句。
        writer.setDataSource(dataSource);
        return writer;
    }


    /*
     * jobRepository 的定义需要 dataSource 和 transactionManager， Spring Boot 已为我们自动配置了这两个类，Spring 可通过方法注入已有的Bean。
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1) // 为 Job 指定 Step。
                .end()
                .listener(csvJobListener()) // 绑定监听器 csvJobListener。
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
            ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory
                .get("step1")
                .<Person, Person>chunk(65000) // 批处理每次提交 65000 条数据。
                .reader(reader) // 给 step 绑定 reader。
                .processor(processor) // 给 step 绑定 processor。
                .writer(writer) // 给 step 绑定 writer。
                .build();
    }

    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<>();
}
}
