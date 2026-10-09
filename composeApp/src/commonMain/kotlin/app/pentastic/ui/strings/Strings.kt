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

    // Index page
    val index: String
    val reorder: String
    val done: String
    val options: String
    val showSubPages: String
    val hideSubPages: String
    val timeline: String
    val enable: String
    val disable: String
    val addSubPage: String
    val switchToNotes: String
    val switchToTasks: String
    val rename: String
    val archive: String
    val unarchive: String
    val delete: String
    val defaultPageName: String
    val defaultSubPageName: String
    val notesPage: String
    val tasksPage: String
    val editPageName: String
    val moveToTrash: String
    val subPageName: String
    val add: String
    val limitReached: String
    val subPageLimitMessage: String
    val ok: String

    fun archiveCount(count: Int): String

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
    
        override val index = "Index"
    override val reorder = "Reorder"
    override val done = "Done"
    override val options = "Options"
    override val showSubPages = "Show sub-pages"
    override val hideSubPages = "Hide sub-pages"
    override val timeline = "Timeline"
    override val enable = "Enable"
    override val disable = "Disable"
    override val addSubPage = "Add sub-page"
    override val switchToNotes = "Switch to Notes"
    override val switchToTasks = "Switch to Tasks"
    override val rename = "Rename"
    override val archive = "Archive"
    override val unarchive = "Unarchive"
    override val delete = "Delete"
    override val defaultPageName = "Page"
    override val defaultSubPageName = "Sub-page"
    override val notesPage = "Notes page"
    override val tasksPage = "Tasks page"
    override val editPageName = "Edit page name"
    override val moveToTrash = "Move to trash"
    override val subPageName = "Sub-page name"
    override val add = "Add"
    override val limitReached = "Limit reached"
    override val subPageLimitMessage = "You can only add 10 sub-pages per page."
    override val ok = "OK"

    override fun archiveCount(count: Int) = "Archive ($count)"

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

    override val index = "فهرست"
    override val reorder = "مرتب‌سازی"
    override val done = "تمام"
    override val options = "گزینه‌ها"
    override val showSubPages = "نمایش زیرصفحه‌ها"
    override val hideSubPages = "پنهان کردن زیرصفحه‌ها"
    override val timeline = "خط زمانی"
    override val enable = "فعال کردن"
    override val disable = "غیرفعال کردن"
    override val addSubPage = "افزودن زیرصفحه"
    override val switchToNotes = "تبدیل به یادداشت‌ها"
    override val switchToTasks = "تبدیل به کارها"
    override val rename = "تغییر نام"
    override val archive = "بایگانی"
    override val unarchive = "خروج از بایگانی"
    override val delete = "حذف"
    override val defaultPageName = "صفحه"
    override val defaultSubPageName = "زیرصفحه"
    override val notesPage = "صفحه یادداشت"
    override val tasksPage = "صفحه کارها"
    override val editPageName = "ویرایش نام صفحه"
    override val moveToTrash = "انتقال به سطل زباله"
    override val subPageName = "نام زیرصفحه"
    override val add = "افزودن"
    override val limitReached = "به حد مجاز رسیدید"
    override val subPageLimitMessage = "برای هر صفحه حداکثر ۱۰ زیرصفحه می‌توانید اضافه کنید."
    override val ok = "تأیید"

    override fun archiveCount(count: Int) = "بایگانی ($count)"
    
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