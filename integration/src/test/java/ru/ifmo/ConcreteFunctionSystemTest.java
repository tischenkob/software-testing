package ru.ifmo;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.ifmo.math.logarithmic.Log;
import ru.ifmo.math.logarithmic.Logarithmic;
import ru.ifmo.math.logarithmic.NaturalLog;
import ru.ifmo.math.trigonometry.Cos;
import ru.ifmo.math.trigonometry.Cot;
import ru.ifmo.math.trigonometry.Sec;
import ru.ifmo.math.trigonometry.Sin;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConcreteFunctionSystemTest {
    private final double ACCURACY = 0.01;
    ConcreteFunctionSystem system;

    double[] mockedArguments = {-1000, -0.0001, 0, 0.0001, 1000};
    double[] mockedResults = {0, 0, 0, -3, -3};

    static CSVWriter writer;

    @SneakyThrows
    @BeforeAll
    public static void setupWriter() {
        Path filePath = Path.of("/home/bogdan/system.csv");
        if (!Files.exists(filePath)) Files.createFile(filePath);
        writer = new CSVWriter(new FileWriter(filePath.toString()));
    }

    private void mockedSetup() {
        var secMock = mock(Sec.class);
        var cotMock = mock(Cot.class);
        var log2Mock = mock(Log.class);
        var log3Mock = mock(Log.class);
        var log5Mock = mock(Log.class);
        var log10Mock = mock(Log.class);

        for (int i = 0; i < mockedArguments.length; i++) {
            var x = mockedArguments[i];
            when(secMock.of(x)).thenReturn(1.0);
            when(cotMock.of(x)).thenReturn(1.0);
            when(log2Mock.of(x)).thenReturn(1.0);
            when(log3Mock.of(x)).thenReturn(1.0);
            when(log5Mock.of(x)).thenReturn(1.0);
            when(log10Mock.of(x)).thenReturn(1.0);
        }

        system = ConcreteFunctionSystem.builder()
                .sec(secMock)
                .cot(cotMock)
                .log2(log2Mock)
                .log3(log3Mock)
                .log5(log5Mock)
                .log10(log10Mock)
                .build();
    }

    @Test
    public void mockedOfTest() {
        mockedSetup();
        for (int i = 0; i < mockedArguments.length; i++) {
            var x = mockedArguments[i];
            var expected = mockedResults[i];
            var actual = system.of(x);

            assertEquals(expected, actual);
        }
    }

    private void setup() {
        var sin = new Sin(ACCURACY);
        var cos = new Cos(sin);
        Logarithmic log = new NaturalLog(ACCURACY);

        system = ConcreteFunctionSystem.builder()
                .cot(new Cot(sin, cos))
                .log2(new Log(log, 2))
                .log3(new Log(log, 3))
                .log5(new Log(log, 5))
                .log10(new Log(log, 10))
                .sec(new Sec(cos))
                .build();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ConcreteFunctionSystem.csv")
    public void of(double expected, double x) {
        setup();
        var actual = system.of(x);
        assertEquals(expected, actual, ACCURACY);

        writer.writeNext(new String[]{String.valueOf(x), String.valueOf(actual)});
    }

    @SneakyThrows
    @AfterAll
    public static void shutdown() {
        writer.close();
    }
}
