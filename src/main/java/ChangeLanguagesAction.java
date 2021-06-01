import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ChangeLanguagesAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        String[] langFromValues = {"cs", "de", "dn", "en", "ru"};
//        String[] langToValues = {"cs", "de", "dn", "en", "ru"};
        int langFromInd =
                Messages.showChooseDialog(
                        event.getProject(),
                "Choose translated language",
                "Translated language",
                Messages.getInformationIcon(),
                langFromValues,
                "ru"
                );
/*
        int langToInd =
                Messages.showChooseDialog(
                        event.getProject(),
                        "Choose target language",
                        "Target language",
                        Messages.getInformationIcon(),
                        langToValues,
                        "en"
                );
/*
        System.out.println(values[langFromInd] + " " + values[langToInd]);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Config.setLangFromTo(
                            values[langFromInd],
                            values[langToInd]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
/*        Messages.showMultilineInputDialog(
                event.getProject(),
                "Some msg",
                "itmo",
                "",
                Messages.getInformationIcon(),
                new InputValidator() {
                    @Override
                    public boolean checkInput(@NlsSafe String inputString) {
                        return inputString.length() == 4;
                    }

                    @Override
                    public boolean canClose(@NlsSafe String inputString) {
                        System.out.println(inputString);
                        return true;
                    }
                });
*/
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
