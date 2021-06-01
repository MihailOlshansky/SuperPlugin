import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Config {
    public static final int LANGUAGE_LENGTH = 2;

    private static final int LANGUAGE_FROM_SKIP = 0;
    public static final String DEF_LANGUAGE_FROM = "ru";
    private static final int LANGUAGE_TO_SKIP = 3;
    public static final String DEF_LANGUAGE_TO = "en";
    private static final Path filepath = Paths.get("settings.txt").toAbsolutePath();

    private static String langFrom;
    private static String langTo;

    private static List<String> languages = new LinkedList<String>();

    public static String getLangFrom() throws IOException {
        createIfNotExists();
        if (langFrom == null) {
            char[] lang = new char[LANGUAGE_LENGTH];
            try (BufferedReader br = new BufferedReader(new FileReader(filepath.toString()))) {
                br.skip(LANGUAGE_FROM_SKIP);
                br.read(lang);
            }
            langFrom = new String(lang);
        }
        return langFrom;
    }

    public static String getLangTo() throws IOException {
        createIfNotExists();
        if (langTo == null) {
            char[] lang = new char[LANGUAGE_LENGTH];
            try (BufferedReader br = new BufferedReader(new FileReader(filepath.toString()))) {
                br.skip(LANGUAGE_TO_SKIP);
                br.read(lang);
            }
            langTo = new String(lang);
        }
        return langTo;
    }

    public static void setLangFromTo(String newLangFrom, String newLangTo) throws IOException {
        createIfNotExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath.toString()))) {
            bw.write(newLangFrom + "\n" + newLangTo);
            langFrom = newLangFrom;
            langTo = newLangTo;
        }
    }

    private static void createIfNotExists() throws IOException {
        if (Files.notExists(filepath)) {
            Files.createFile(filepath);
            langFrom = DEF_LANGUAGE_FROM;
            langTo = DEF_LANGUAGE_TO;
        }
    }
}
