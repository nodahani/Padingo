package app.pentastic

/**
 * Hands text to the platform's native share flow: the share sheet on
 * Android and iOS, the system clipboard on desktop.
 */
expect class AppSharer {
    fun share(text: String)
}
