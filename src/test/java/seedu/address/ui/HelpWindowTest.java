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
        helpWindow.focus();
        robot.press(KeyCode.ESCAPE);
        WaitForAsyncUtils.waitForFxEvents();
        Thread.sleep(2000); // Buffer time for key press to register and close window.
        assertFalse(helpWindow.isShowing());
    }

    @Test
    public void remainOpenOnNonEscapeKeyPress(FxRobot robot) throws Exception {
        robot.press(KeyCode.A);
        robot.press(KeyCode.SOFTKEY_0);
        robot.press(KeyCode.L);
        robot.press(KeyCode.BACK_SPACE);
        robot.press(KeyCode.DELETE);

        Thread.sleep(2000); // Buffer time for key press to register.
        assertTrue(helpWindow.isShowing());
    }

    @Test
    public void focusCall() throws Exception {
        Platform.runLater(() -> helpWindow.focus());
        Thread.sleep(2000);
        assertTrue(helpWindow.isShowing());
    }
}
