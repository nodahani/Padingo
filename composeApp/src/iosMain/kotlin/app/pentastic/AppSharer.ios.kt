package app.pentastic

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class AppSharer {
    actual fun share(text: String) {
        val root = UIApplication.sharedApplication.keyWindow?.rootViewController
        val sheet = UIActivityViewController(
            activityItems = listOf(text),
            applicationActivities = null,
        )
        root?.presentViewController(sheet, animated = true, completion = null)
    }
}
