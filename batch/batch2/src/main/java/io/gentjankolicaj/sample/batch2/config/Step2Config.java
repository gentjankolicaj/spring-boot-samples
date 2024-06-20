package io.gentjankolicaj.sample.batch2.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class Step2Config {

  @Bean
  public ItemReader<Map<Integer, Integer>> jdbcReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Map<Integer, Integer>>()
        .dataSource(dataSource)
        .name("jdbc-reader-step2")
        .sql("Select COUNT(age) c, age a from people group by age")
        .rowMapper(new RowMapper<Map<Integer, Integer>>() {
          @Override
          public Map<Integer, Integer> mapRow(ResultSet resultSet, int i) throws SQLException {
            return Collections.singletonMap(resultSet.getInt("a"), resultSet.getInt("b"));
          }
        }).build();
  }

  @Bean
  public ItemWriter<Map<Integer, Integer>> fileWriter(@Value("${output}") Resource resource) {
    return new FlatFileItemWriterBuilder<Map<Integer, Integer>>()
        .name("file-writer-step2")
        .resource(resource)
        .lineAggregator(new DelimitedLineAggregator<Map<Integer, Integer>>() {
          {
            setDelimiter(",");
            setFieldExtractor(new FieldExtractor<Map<Integer, Integer>>() {
              @Override
              public Object[] extract(Map<Integer, Integer> item) {
                Map.Entry<Integer, Integer> next = item.entrySet().iterator().next();
                return new Object[]{next.getKey(), next.getValue()};
              }
            });
          }
        }).build();
  }

}
