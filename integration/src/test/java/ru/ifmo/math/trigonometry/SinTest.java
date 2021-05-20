package ru.ifmo.math.trigonometry;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {

    private final double ACCURACY = 0.01;
    private SineFunction sin;
    static CSVWriter writer;

    @SneakyThrows
    @BeforeAll
    public static void setup() {
        Path filePath = Path.of("/home/bogdan/sin.csv");
        if (!Files.exists(filePath)) Files.createFile(filePath);
        writer = new CSVWriter(new FileWriter(filePath.toString()));
    }

    @BeforeEach
    public void setUp() throws Exception {
        sin = new Sin(ACCURACY);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Sine.csv")
    public void of(Double expected, Double x) {
        Double actual = sin.of(x);
        assertEquals(expected, actual, ACCURACY);
        writer.writeNext(new String[]{String.valueOf(x), String.valueOf(actual)});
    }

    @SneakyThrows
    @AfterAll
    public static void shutdown() {
        writer.close();
    }
}
