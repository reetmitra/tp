//package seedu.address.logic.commands;
//
//import static javafx.beans.binding.Bindings.when;
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.awt.Toolkit;
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.StringSelection;
//import org.junit.jupiter.api.Test;
//import seedu.address.model.Model;
//
//public class CopyCommandTest {
//
//    @Test
//    public void execute_copySuccessful() throws Exception {
//        // Mock the Model class
//        Model model = mock(Model.class);
//        when(model.getEmail()).thenReturn("test@example.com");
//
//        // Mock the Clipboard class
//        Clipboard clipboard = mock(Clipboard.class);
//        Toolkit toolkit = mock(Toolkit.class);
//        when(toolkit.getSystemClipboard()).thenReturn(clipboard);
//
//        // Create a new CopyCommand and execute it
//        CopyCommand copyCommand = new CopyCommand();
//        copyCommand.execute(model);
//
//        // Verify that the correct email was copied to the clipboard
//        verify(clipboard).setContents(new StringSelection("test@example.com"), null);
//    }
//}
