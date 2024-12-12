package pl.put.poznan.building.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.put.poznan.building.logic.BuildingInfo;

@Configuration
public class AppConfig {
    @Bean
    public BuildingInfo buildingInfo() {
        return new BuildingInfo();
    }
}
