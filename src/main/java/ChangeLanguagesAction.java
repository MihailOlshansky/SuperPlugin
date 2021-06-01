import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ChangeLanguagesAction extends AnAction {

    private boolean contains(String[] arr, String el) {
        for(String i : arr) {
            if(i.equals(el)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        String[] languages = {"cz", "de", "dn", "en", "fr", "ru"};
        StringBuilder msg = new StringBuilder("Languages available:\n");
        for (String i : languages) {
            msg.append(i).append("\n");
        }

        String result = Messages.showMultilineInputDialog(
                project,
                msg.toString(),
                "Change languages",
                Config.getLangFrom(project) + "\n" + Config.getLangTo(project),
                Messages.getInformationIcon(),
                new InputValidator() {
                    @Override
                    public boolean checkInput(@NlsSafe String inputString) {
                        String[] split = inputString.split("\\s+");

                        if(split.length != 2) {
                            return false;
                        }

                        for (String i : split) {
                            if(!contains(languages, i)) {
                                return false;
                            }
                        }

                        return true;
                    }

                    @Override
                    public boolean canClose(@NlsSafe String inputString) {
                        return true;
                    }
                }
        );
        if (result != null) {
            System.out.println(result);
            Config.setLangFromTo(project, result.split("\\s+")[0], result.split("\\s+")[1]);
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
