package ru.ifmo.math.trigonometry;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CosTest {

    private Cos cos;
    private final double accuracy = 0.01;

    private static Stream<Arguments> provideValuesForOfTest() {
        return Stream.of(
                Arguments.of(1, 0, 0),
                Arguments.of(0.866, PI / 6, 0.500),
                Arguments.of(0.707, PI / 4, 0.707),
                Arguments.of(0.500, PI / 3, 0.866),
                Arguments.of(0, PI / 2, 1),
                Arguments.of(-0.500, 2 * PI / 3, 0.866)
        );
    }

    static CSVWriter writer;

    @SneakyThrows
    @BeforeAll
    public static void setup() {
        Path filePath = Path.of("/home/bogdan/cos.csv");
        if (!Files.exists(filePath)) Files.createFile(filePath);
        writer = new CSVWriter(new FileWriter(filePath.toString()));
    }

    @ParameterizedTest
    @MethodSource("provideValuesForOfTest")
    public void mockedOfTest(double expected, double x, double sinValue) {
        var sin = mock(SineFunction.class);
        when(sin.of(x)).thenReturn(sinValue);
        cos = new Cos(sin);
        var actual = cos.of(x);
        assertEquals(expected, actual, accuracy);
    }

    @ParameterizedTest
    @MethodSource("provideValuesForOfTest")
    public void integrationTest(double expected, double x, double sinValue) {
        var sin = new Sin(accuracy);
        cos = new Cos(sin);
        var actual = cos.of(x);
        assertEquals(expected, actual, accuracy);

        writer.writeNext(new String[]{String.valueOf(x), String.valueOf(actual)});
    }

    @SneakyThrows
    @AfterAll
    public static void shutdown() {
        writer.close();
    }
}
