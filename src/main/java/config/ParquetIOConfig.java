package config;

import java.util.HashMap;
import java.util.Map;

public class ParquetIOConfig {

    /*
    * Parquet config properties for IO optimization
    * */
    public static Map<String, String> getOptimalPerformanceConfig() {
        HashMap<String, String> config = new HashMap<>();
        config.put("spark.hadoop.parquet.enable.summary-metadata", "false");
        config.put("spark.sql.parquet.mergeSchema", "false");
        config.put("spark.sql.parquet.filterPushdown", "true");
        config.put("spark.sql.hive.metastorePartitionPruning", "true");
        return config;
    }
}
