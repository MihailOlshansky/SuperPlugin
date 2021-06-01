public class Parse {

    public static String MakeResultString(String data) {
        int counter = 0;
        StringBuilder Result = new StringBuilder();

        while (counter < data.length() - 1) {
            int start = counter;
            if (data.charAt(counter) == '/' && data.charAt(counter + 1) == '*') {
                counter += 2;
                while (counter < data.length() - 1 && data.charAt(counter) == '*' && data.charAt(counter + 1) == '/') {
                    counter++;
                }
                int end = counter + 1;
                String toTranslate = data.substring(start, end);
                Result.append(Translator.translate(Config.GetLangFrom(), Config.GetLangTo(), toTranslate));
                counter += 2;
                continue;
            }
            if (data.charAt(counter) == '/' && data.charAt(counter + 1) == '/') {
                counter += 2;
                while (counter < data.length() - 1 && data.charAt(counter) == '\n') {
                    counter++;
                }
                int end = counter;
                String toTranslate = data.substring(start, end);
                Result.append(Translator.translate(Config.GetLangFrom(), Config.GetLangTo(), toTranslate));
                counter += 1;
                continue;
            }
            if (data.charAt(counter) == '\"') {
                counter += 1;
                while (counter < data.length() && data.charAt(counter) == '\"') {
                    counter++;
                }
                int end = counter;
                String toTranslate = data.substring(start, end);
                Result.append(Translator.translate(Config.GetLangFrom(), Config.GetLangTo(), toTranslate));
                counter += 1;
                continue;
            }
            Result.append(data.charAt(counter++));
        }
        if (counter == data.length() - 1)
            Result.append(data.charAt(data.length()));
        return Result.toString();
    }
}
