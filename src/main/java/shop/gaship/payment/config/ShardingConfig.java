package shop.gaship.payment.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.zip.CRC32;

/**
 *
 *
 * @author : 김세미
 * @since 1.0
 */
@Configuration
@RequiredArgsConstructor
public class ShardingConfig {
    @Qualifier("shard-0")
    private final DataSource shardZero;

    @Qualifier("shard-1")
    private final DataSource shardOne;

    @Primary
    @Bean
    public DataSource shardDataSource() throws SQLException {
        Map<String, DataSource> dataSources = Map.of(
                "shard-0", shardZero ,
                "shard-1", shardOne
        );

        PreciseShardingAlgorithm<?> shardingAlgorithm = new CustomShardingAlgorithm(dataSources.size());

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setTableRuleConfigs(Arrays.asList(
                createTableRuleConfig("easy_payments", "payment_key" ,shardingAlgorithm),
                createTableRuleConfig("card_payments", "payment_key",shardingAlgorithm),
                createTableRuleConfig("payment_histories", "payment_key",  shardingAlgorithm),
                createTableRuleConfig("payment_cancel_histories", "payment_key", "payment_cancel_history_no", shardingAlgorithm)
        ));

        return ShardingDataSourceFactory.createDataSource(dataSources, shardingRuleConfig, new Properties());
    }


    private TableRuleConfiguration createTableRuleConfig(String tableName, String primaryKeyName, String primaryKeyName2, PreciseShardingAlgorithm shardingAlgorithm) {
        Properties properties = new Properties();
        properties.setProperty("worker.id", "123"); // 프로세스별로 다른 ID값으로 지정

        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration(tableName);
//        tableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", primaryKeyName2, properties));
        tableRuleConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(primaryKeyName, shardingAlgorithm));

        return tableRuleConfig;
    }

    private TableRuleConfiguration createTableRuleConfig(String tableName, String primaryKeyName, PreciseShardingAlgorithm shardingAlgorithm) {
        Properties properties = new Properties();
        properties.setProperty("worker.id", "123"); // 프로세스별로 다른 ID값으로 지정

        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration(tableName);
//        tableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", primaryKeyName, properties));
        tableRuleConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration(primaryKeyName, shardingAlgorithm));

        return tableRuleConfig;
    }

    @Slf4j
    @RequiredArgsConstructor
    public static class CustomShardingAlgorithm<T extends Comparable<?>> implements PreciseShardingAlgorithm<T> {
        private final int shardSize;

        @Override
        public String doSharding(final Collection<String> shardNames, final PreciseShardingValue<T> shardingValue) {
            String shardName = "shard-" + Math.abs(crc32(String.valueOf(shardingValue.getValue()))) % shardSize;

            log.info("[DO_SHARDING] shardName : {}, shardingValue : {}", shardName, shardingValue);

            if (shardNames.contains(shardName)) {
                return shardName;
            }

            throw new UnsupportedOperationException();
        }

        private long crc32(String value) {
            CRC32 crc32 = new CRC32();
            crc32.update(value.getBytes());
            return crc32.getValue();
        }
    }
}
