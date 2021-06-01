import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class TranslateAndReplaceAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();
        Document document = editor.getDocument();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (selectionModel.hasSelection()) {
                    document.replaceString(
                            selectionModel.getSelectionStart(),
                            selectionModel.getSelectionEnd(),
                            Parse.makeResultString(
                                    event.getData(PlatformDataKeys.PROJECT),
                                    selectionModel.getSelectedText()));
                }
            }
        };
        WriteCommandAction.runWriteCommandAction(editor.getProject(), runnable);
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
