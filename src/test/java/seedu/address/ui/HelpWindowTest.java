package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

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

    @DisabledOnOs(OS.LINUX)
    @Test
    public void closeOnEscapeKeyPress(FxRobot robot) {
        assertTrue(helpWindow.isShowing());
        robot.press(KeyCode.ESCAPE);
        assertFalse(helpWindow.isShowing());
    }

    @Test
    public void remainOpenOnNonEscapeKeyPress(FxRobot robot) {
        robot.press(KeyCode.A);
        robot.press(KeyCode.SOFTKEY_0);
        robot.press(KeyCode.L);
        robot.press(KeyCode.BACK_SPACE);
        robot.press(KeyCode.DELETE);
        assertTrue(helpWindow.isShowing());
    }

    @Test
    public void focusCall() {
        Platform.runLater(() -> helpWindow.focus());

        try {
            Thread.sleep(2000);
            assertTrue(helpWindow.isShowing());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
