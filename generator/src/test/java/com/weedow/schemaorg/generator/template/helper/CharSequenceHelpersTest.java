package com.weedow.schemaorg.generator.template.helper;

import com.github.jknack.handlebars.Options;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

class CharSequenceHelpersTest {

    @ParameterizedTest
    @MethodSource
    void test(String input, String expected) throws IOException {
        final Object result = CharSequenceHelpers.capitalizeWithUnderscore.apply(input, mock(Options.class));
        Assertions.assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> test() {
        return Stream.of(
                Arguments.of("Monday", "MONDAY"),
                Arguments.of("OTC", "OTC"),
                Arguments.of("WritePermission", "WRITE_PERMISSION"),
                Arguments.of("DesktopWebPlatform", "DESKTOP_WEB_PLATFORM"),
                Arguments.of("IOSPlatform", "IOS_PLATFORM"),
                Arguments.of("FDAcategoryA", "FD_ACATEGORY_A"),
                Arguments.of("EvidenceLevelA", "EVIDENCE_LEVEL_A"),
                Arguments.of("EUEnergyEfficiencyCategoryD", "EU_ENERGY_EFFICIENCY_CATEGORY_D"),
                Arguments.of("EUEnergyEfficiencyCategoryA2Plus", "EU_ENERGY_EFFICIENCY_CATEGORY_A2_PLUS"),
                Arguments.of("CoOp", "CO_OP"),
                Arguments.of("XRay", "X_RAY"),
                Arguments.of("Ultrasound", "ULTRASOUND"),
                Arguments.of("EPRelease", "EP_RELEASE"),
                Arguments.of("NonprofitANBI", "NONPROFIT_ANBI"),
                Arguments.of("Nonprofit501c4", "NONPROFIT_501C4"),
                Arguments.of("Nonprofit501c23", "NONPROFIT_501C23"),
                Arguments.of("WearableSizeSystemJP", "WEARABLE_SIZE_SYSTEM_JP"),
                Arguments.of("WearableSizeSystemEurope", "WEARABLE_SIZE_SYSTEM_EUROPE"),
                Arguments.of("WearableSizeSystemGS1", "WEARABLE_SIZE_SYSTEM_GS1"),
                Arguments.of("WearableSizeSystemEN13402", "WEARABLE_SIZE_SYSTEM_EN13402")
        );
    }

}