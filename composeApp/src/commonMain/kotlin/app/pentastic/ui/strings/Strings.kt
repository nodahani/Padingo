package app.pentastic.ui.strings

import androidx.compose.runtime.staticCompositionLocalOf
import app.pentastic.data.AppLanguage
import app.pentastic.data.ThemeMode

interface AppStrings {
    // Common
    val cancel: String
    val save: String

    // Settings
    val settings: String
    val theme: String
    val language: String
    val trash: String
    val follow: String
    val share: String
    val rate: String
    val shareText: String

    fun themeLabel(mode: ThemeMode): String
}

object EnglishStrings : AppStrings {
    override val cancel = "Cancel"
    override val save = "Save"

    override val settings = "Settings"
    override val theme = "Theme"
    override val language = "Language"
    override val trash = "Trash"
    override val follow = "Follow"
    override val share = "Share"
    override val rate = "Rate"
    override val shareText =
        "Get things done with Pentastic!\nhttps://play.google.com/store/apps/details?id=app.pentastic"

    override fun themeLabel(mode: ThemeMode) = when (mode) {
        ThemeMode.LIGHT -> "Light"
        ThemeMode.DARK -> "Dark"
        ThemeMode.SYSTEM -> "System"
        ThemeMode.DAY_NIGHT -> "Day/Night"
    }
}

object FarsiStrings : AppStrings {
    override val cancel = "لغو"
    override val save = "ذخیره"

    override val settings = "تنظیمات"
    override val theme = "پوسته"
    override val language = "زبان"
    override val trash = "سطل زباله"
    override val follow = "دنبال کردن"
    override val share = "اشتراک‌گذاری"
    override val rate = "امتیاز دادن"
    override val shareText =
        "با Pentastic کارهات رو انجام بده!\nhttps://play.google.com/store/apps/details?id=app.pentastic"

    override fun themeLabel(mode: ThemeMode) = when (mode) {
        ThemeMode.LIGHT -> "روشن"
        ThemeMode.DARK -> "تیره"
        ThemeMode.SYSTEM -> "سیستم"
        ThemeMode.DAY_NIGHT -> "روز/شب"
    }
}

fun stringsFor(language: AppLanguage): AppStrings = when (language) {
    AppLanguage.ENGLISH -> EnglishStrings
    AppLanguage.FARSI -> FarsiStrings
}

val LocalStrings = staticCompositionLocalOf<AppStrings> { EnglishStrings }