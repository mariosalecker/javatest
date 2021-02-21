package test;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StreamsTest {

    @Test
    void testGetAllConfigurationCodesEmpty() {
        Streams streams = new Streams(Collections::emptyList);

        Map<Streams.ConfigType, List<String>> allConfigurationCodes = streams.getAllConfigurationCodes();
        assertThat(allConfigurationCodes).isEmpty();
    }

    @Test
    void testGetAllConfigurationCodes() {
        Streams streams = new Streams(new SampleData());
        Map<Streams.ConfigType, List<String>> allConfigurationCodes = streams.getAllConfigurationCodes();

        assertThat(allConfigurationCodes).containsOnlyKeys(Streams.ConfigType.COUNTRIES, Streams.ConfigType.CURRENCIES);
        assertThat(allConfigurationCodes.get(Streams.ConfigType.COUNTRIES)).containsExactly("AT", "CH", "DE", "RU");
        assertThat(allConfigurationCodes.get(Streams.ConfigType.CURRENCIES)).containsExactly("CHF", "EUR", "GBP");
    }

    public static class SampleData implements Streams.DataProvider {
        public List<Streams.ConfigEntry> loadConfig() {
            return List.of(
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.COUNTRIES).code("CH").label("Switzerland").isActive(true).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.COUNTRIES).code("DE").label("Germany").isActive(true).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.COUNTRIES).code("AT").label("Austria").isActive(true).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.COUNTRIES).code("YU").label("Yugoslavia").isActive(false).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.COUNTRIES).code("SU").label("Soviet Union").isActive(false).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.COUNTRIES).code("RU").label("Russia").isActive(true).build(),

                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.CURRENCIES).code("CHF").label("Swiss Franc").isActive(true).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.CURRENCIES).code("EUR").label("Euro").isActive(true).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.CURRENCIES).code("GBP").label("British Pound").isActive(true).build(),
                    Streams.ConfigEntry.builder().configType(Streams.ConfigType.CURRENCIES).code("DEM").label("German Mark").isActive(false).build()
            );
        }
    }
}
