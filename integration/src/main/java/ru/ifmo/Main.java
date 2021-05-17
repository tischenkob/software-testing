package ru.ifmo;

import com.opencsv.CSVWriter;
import ru.ifmo.math.Computable;
import ru.ifmo.math.trigonometry.Sin;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    Computable function = new Sin(0.01);
    double in = 1;
    double out = 3;
    double step = 0.5;
    String filePath = "/home/bogdan/results.csv";

    private void run(String[] args) throws IOException {
        var path = Path.of(filePath);
        if (!Files.exists(path)) Files.createFile(path);
        try (var writer = new CSVWriter(new FileWriter(filePath))) {
            while (in < out) {
                writer.writeNext(new String[]{String.valueOf(in), String.valueOf(function.of(in))});
                in += step;
            }
        }

    }


    public static void main(String[] args) {
        try {
            new Main().run(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
