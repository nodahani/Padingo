package app.pentastic.data

/**
 * The language of the device/OS. Used for first-launch content such as the
 * onboarding seed, which is written before the user can pick a language in
 * the app. The in-app language setting stays the source of truth for UI.
 */
expect fun deviceAppLanguage(): AppLanguage
