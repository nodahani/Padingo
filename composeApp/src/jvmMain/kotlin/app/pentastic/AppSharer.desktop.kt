package app.pentastic

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

/**
 * The desktop has no share sheet; hand the text (link included) to the
 * system clipboard so pasting into a chat or mail client is one keystroke away.
 */
actual class AppSharer {
    actual fun share(text: String) {
        Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(text), null)
    }
}
