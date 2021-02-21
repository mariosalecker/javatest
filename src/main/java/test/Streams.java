package test;

import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class Streams {

    private final List<ConfigEntry> configEntries;

    public Streams(DataProvider dataProvider) {
        configEntries = dataProvider.loadConfig();
    }

    public Map<ConfigType, List<String>> getAllConfigurationCodes() {
        // return all configuration codes by the configuration type in alphabetical order
        return configEntries.stream().filter(ConfigEntry::isActive).sorted(Comparator.comparing(ConfigEntry::getCode))
                .collect(Collectors.groupingBy(ConfigEntry::getConfigType, TreeMap::new, Collectors.mapping(ConfigEntry::getCode, Collectors.toList())));
    }

    public Optional<String> findLabelForCode(String code) {
        // return the label for a given code
        return configEntries.stream().filter(entry -> code.equals(entry.getCode())).map(ConfigEntry::getLabel).findFirst();
    }

    public Map<ConfigType, Long> countElementsByType() {
        // count how many entries are present per configType
        return configEntries.stream().collect(Collectors.groupingBy(ConfigEntry::getConfigType, Collectors.counting()));
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
