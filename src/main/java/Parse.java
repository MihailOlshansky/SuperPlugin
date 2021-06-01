import com.intellij.openapi.project.Project;

import java.io.IOException;

public class Parse {

    public static String makeResultString(Project project, String data) {
        int counter = 0;
        StringBuilder result = new StringBuilder();

        try {
            while (counter < data.length() - 1) {
                int start = counter;
                if (data.charAt(counter) == '/' && data.charAt(counter + 1) == '*') {
                    counter += 2;
                    while (counter < data.length() - 1 && !(data.charAt(counter) == '*' && data.charAt(counter + 1) == '/')) {
                        counter++;
                    }
                    int end = counter + 1;
                    String toTranslate = data.substring(start + 2, end - 2);
                    result
                            .append("/*")
                            .append(Translator.translate(Config.getLangFrom(project), Config.getLangTo(project), toTranslate))
                            .append("*/");
                    counter += 2;
                    continue;
                }
                if (data.charAt(counter) == '/' && data.charAt(counter + 1) == '/') {
                    counter += 2;
                    while (counter < data.length() && data.charAt(counter) != '\n') {
                        counter++;
                    }
                    int end = counter;
                    String toTranslate = data.substring(start + 2, end - 1);
                    result
                            .append("//")
                            .append(Translator.translate(Config.getLangFrom(project), Config.getLangTo(project), toTranslate))
                            .append("\n");
                    counter += 1;
                    continue;
                }
                if (data.charAt(counter) == '\"') {
                    counter += 1;
                    while (counter < data.length() && data.charAt(counter) != '\"') {
                        counter++;
                    }
                    int end = counter;
                    String toTranslate = data.substring(start + 1, end - 1);
                    result
                            .append("\"")
                            .append(Translator.translate(Config.getLangFrom(project), Config.getLangTo(project), toTranslate))
                            .append("\"");
                    counter += 1;
                    continue;
                }
                result.append(data.charAt(counter++));
            }
        } catch (IOException ioext) {
            ioext.printStackTrace();
        }
        if (counter == data.length() - 1)
            result.append(data.charAt(counter));
        return result.toString();
    }
}
