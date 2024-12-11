package com.mamarek123.domain.energyCalculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Api pozwala nam zwrócić zarówno sunlight_duration jak i daylight_duration, zakładamy że panele będą działąc tylko przy sunight na potrzeby tego taska
 */
@Service
public class ExpectedEnergyGenerationCalculator {

    private final float photovoltaicInstallationPower;
    private final float panelEfficiency;


    public ExpectedEnergyGenerationCalculator(@Value("${photovoltaicInstallationPower}") float photovoltaicInstallationPower,
                                              @Value("${panelEfficiency}") float panelEfficiency) {
        this.photovoltaicInstallationPower = photovoltaicInstallationPower;
        this.panelEfficiency = panelEfficiency;
    }

    public double calculate(double sunlightDuration){
        return sunlightDuration/60/60 * photovoltaicInstallationPower * panelEfficiency;
    }
}
