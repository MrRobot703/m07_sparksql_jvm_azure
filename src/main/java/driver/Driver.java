package driver;

import config.Constants;
import config.ParquetIOConfig;
import config.SparkConfig;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import services.impl.SecurityServiceImpl;

public class Driver {

    private final SparkSession sparkSession;

    private final SecurityServiceImpl securityService = new SecurityServiceImpl();

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.start();
    }

    public Driver() {
        sparkSession = SparkSession.builder()
                .config(SparkConfig.getSparkConfig(securityService))
                .getOrCreate();
    }

    public void start() {

        /*
         *                          Extracting stage.
         * Retrieving the data from external blob storage (with according fine-tuning of
         * parquet and orc format retrieval) to internal blob storage.
         * */
        Dataset<Row> expediaDF = getAvroData(Constants.InputStorage.EXPEDIA);
        Dataset<Row> hotelWeatherDF = getParquetData(Constants.InputStorage.HOTEL_WEATHER_URL);

        expediaDF.show(5);
        expediaDF.printSchema();

        hotelWeatherDF.show(5);
        hotelWeatherDF.printSchema();

        expediaDF.write()
                .mode("overwrite")
                .format("avro")
                .options(securityService.getOutPutStorageCredentials())
                .save(Constants.OutputStorage.EXPEDIA);
        hotelWeatherDF.write()
                .mode("overwrite")
                .format("parquet")
                .options(securityService.getOutPutStorageCredentials())
                .partitionBy("year", "month", "day")
                .save(Constants.OutputStorage.HOTEL_WEATHER_URL);

        sparkSession.stop();
    }

    private Dataset<Row> getAvroData(String url) {
        return sparkSession.read()
                .format("avro")
                .load(url);
    }

    private Dataset<Row> getParquetData(String url) {
        return sparkSession.read()
                .format("parquet")
                .options(ParquetIOConfig.getOptimalPerformanceConfig())
                .load(url);
    }
}
