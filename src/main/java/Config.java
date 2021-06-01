import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;

public class Config {
    public static final String DEF_LANGUAGE_FROM = "ru";
    public static final String DEF_LANGUAGE_TO = "en";

    private static final String langFromName = "langFrom";
    private static final String langToName = "langTo";

    public static String getLangFrom(Project project) {
        if(!PropertiesComponent.getInstance(project).isValueSet(langFromName)) {
            PropertiesComponent.getInstance(project).setValue(langFromName, DEF_LANGUAGE_FROM);
        }
        return PropertiesComponent.getInstance(project).getValue(langFromName);
    }

    public static String getLangTo(Project project) {
        if(!PropertiesComponent.getInstance(project).isValueSet(langToName)) {
            PropertiesComponent.getInstance(project).setValue(langToName, DEF_LANGUAGE_TO);
        }
        return PropertiesComponent.getInstance(project).getValue(langToName);
    }

    public static void setLangFromTo(Project project, String newLangFrom, String newLangTo) {
        PropertiesComponent.getInstance(project).unsetValue(langFromName);
        PropertiesComponent.getInstance(project).unsetValue(langToName);
        PropertiesComponent.getInstance(project).setValue(langFromName, newLangFrom);
        PropertiesComponent.getInstance(project).setValue(langToName, newLangTo);
    }
}
