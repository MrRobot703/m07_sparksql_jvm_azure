package config;

public class Constants {
    public static class InputStorage {
        public static final String HOTEL_WEATHER_URL = "abfss://m07sparksql@bd201stacc.dfs.core.windows.net/hotel-weather";
        public static final String EXPEDIA = "abfss://m07sparksql@bd201stacc.dfs.core.windows.net/expedia";
        public static final String SECRET_VAULT_URL = "https://keycredentials.vault.azure.net/";
    }

    public static class OutputStorage {
        public static final String HOTEL_WEATHER_URL = "abfss://data-sparksql@sttislenkowesteurope.dfs.core.windows.net/hotel-weather";
        public static final String EXPEDIA = "abfss://data-sparksql@sttislenkowesteurope.dfs.core.windows.net/expedia";
    }

    public static class Security {
        //put your secret names from azure key-vault here for late retrieval at runtime by security service.
        public static final String OUTPUT_STORAGE_ACCOUNT_NAME="sttislenkowesteurope"; //replace with your own name
        public static final String INPUT_STORAGE_OAUTH_SECRET_NAME = "inputStorageOAuth2ClientSecret"; //replace with your own name
    }
}
