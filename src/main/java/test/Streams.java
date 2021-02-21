package test;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Streams {

    private final List<ConfigEntry> configEntries;

    public Streams(DataProvider dataProvider) {
        configEntries = dataProvider.loadConfig();
    }

    public Map<ConfigType, List<String>> getAllConfigurationCodes() {
        // TODO: return all configuration codes by the configuration type in alphabetical order
    }

    public Optional<String> findLabelForCode(String code) {
        // TODO: return the label for a given code
    }

    public Map<ConfigType, Long> countElementsByType() {
        // TODO: count how many entries are present per configType
    }

    public interface DataProvider {
        List<ConfigEntry> loadConfig();
    }

    public enum ConfigType {
        COUNTRIES, CURRENCIES, BANK_CODES
    }

    @Data
    @Builder
    public static class ConfigEntry {
        private ConfigType configType;
        private String code;
        private String label;
        private boolean isActive;
    }
}
