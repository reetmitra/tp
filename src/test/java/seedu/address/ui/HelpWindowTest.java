package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class HelpWindowTest {

    private HelpWindow helpWindow;

    @Start
    public void start(Stage stage) {
        helpWindow = new HelpWindow(stage);
        helpWindow.show();
    }

    @Test
    public void closeOnEscapeKeyPress(FxRobot robot) throws Exception {
        Platform.runLater(() -> helpWindow.focus());
        WaitForAsyncUtils.waitForFxEvents(); // Wait for help window to be focused.

        robot.press(KeyCode.ESCAPE);
        WaitForAsyncUtils.waitForFxEvents(); // Wait for any new events in javaFx to be completed.
        assertFalse(helpWindow.isShowing());
    }

    @Test
    public void remainOpenOnNonEscapeKeyPress(FxRobot robot) throws Exception {
        robot.press(KeyCode.A);
        robot.press(KeyCode.SOFTKEY_0);
        robot.press(KeyCode.L);
        robot.press(KeyCode.BACK_SPACE);
        robot.press(KeyCode.DELETE);

        WaitForAsyncUtils.waitForFxEvents(); // Wait for any new events in javaFx to be completed.
        assertTrue(helpWindow.isShowing());
    }

    @Test
    public void focusCall() throws Exception {
        Platform.runLater(() -> helpWindow.focus());
        WaitForAsyncUtils.waitForFxEvents(); // Wait for any new events in javaFx to be completed.
        assertTrue(helpWindow.isShowing());
    }
}
