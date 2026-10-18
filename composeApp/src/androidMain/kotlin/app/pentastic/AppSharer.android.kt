package app.pentastic

import android.content.Context
import android.content.Intent

actual class AppSharer(private val context: Context) {
    actual fun share(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        // NEW_TASK: an application context has no activity stack to anchor the chooser to
        context.startActivity(
            Intent.createChooser(intent, null).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
