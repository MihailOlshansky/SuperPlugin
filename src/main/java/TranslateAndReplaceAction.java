import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class TranslateAndReplaceAction extends AnAction {
    /*
    public TestAction() {
        super(new TranslatorActionHandler(new ActionHandler() {
            @Override
            public void handleResult(Editor editor, List<String> translated) {
                final SelectionModel selectionModel = editor.getSelectionModel();
                final int selectionStart = selectionModel.getSelectionStart();

                String oldText = selectionModel.getSelectedText();
                final String newText = translated.isEmpty() ? oldText : translated.get(0);

                EditorModificationUtil.deleteSelectedText(editor);
                EditorModificationUtil.insertStringAtCaret(editor, newText);
                selectionModel.setSelection(selectionStart, selectionStart + newText.length());
            }

            @Override
            public void handleError(Editor editor) {

            }
        }));
    }
*/

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();
        Document document = editor.getDocument();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    document.replaceString(
                            selectionModel.getSelectionStart(),
                            selectionModel.getSelectionEnd(),
                            Translator.translate(
                                    Config.getLangFrom(),
                                    Config.getLangTo(),
                                    selectionModel.getSelectedText()));
                } catch (IOException e) {
                    e.printStackTrace();
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
