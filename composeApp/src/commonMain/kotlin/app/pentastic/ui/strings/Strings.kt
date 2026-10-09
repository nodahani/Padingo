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

    // Home, Archive, Trash
    val addTask: String
    val addNote: String
    val addPage: String
    val archiveEmpty: String
    val empty: String
    val trashEmpty: String
    val pages: String
    val tasks: String
    val emptyTrash: String
    val emptyTrashMessage: String
    val deleteAll: String
    val deletePermanently: String
    val deleteTaskMessage: String
    val subPage: String
    val restore: String
    val deleteForever: String

    // Note page
    val repeatingTask: String
    val upcomingReminder: String
    val dueDate: String
    val moreOptions: String
    val deleteCompleted: String
    val markDone: String
    val markTodo: String
    val priority: String
    val copy: String
    val edit: String
    val repeatLabel: String
    val moveTo: String
    val reminder: String
    val current: String
    val repeatTask: String
    val startFrom: String
    val remindMe: String
    val clear: String
    val selectStartDate: String

    // Timeline, Reminder, Due date
    val timelineRepeating: String
    val timelineUnscheduled: String
    val timelineCompleted: String
    val timelineEmpty: String
    val setReminder: String
    val changeDateTime: String
    val enabled: String
    val remove: String
    val selectDate: String
    val back: String
    val permissionRequired: String
    val permissionMessage: String
    val continueLabel: String

    // Functions
    fun themeLabel(mode: ThemeMode): String
    fun archiveCount(count: Int): String
    fun deletePageMessage(pageName: String): String
    fun completedTasks(count: Int): String
    fun moveCompletedMessage(count: Int): String
}

object EnglishStrings : AppStrings {
    // Common
    override val cancel = "Cancel"
    override val save = "Save"

    // Settings
    override val settings = "Settings"
    override val theme = "Theme"
    override val language = "Language"
    override val trash = "Trash"
    override val follow = "Follow"
    override val share = "Share"
    override val rate = "Rate"
    override val shareText =
        "Get things done with Pentastic!\nhttps://play.google.com/store/apps/details?id=app.pentastic"

    // Index page
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

    // Home, Archive, Trash
    override val addTask = "Add a task..."
    override val addNote = "Add a note..."
    override val addPage = "Add a new page..."
    override val archiveEmpty = "Archive is empty"
    override val empty = "Empty"
    override val trashEmpty = "Trash is empty"
    override val pages = "Pages"
    override val tasks = "Tasks"
    override val emptyTrash = "Empty trash"
    override val emptyTrashMessage = "Permanently delete all items in trash? This cannot be undone."
    override val deleteAll = "Delete all"
    override val deletePermanently = "Delete permanently"
    override val deleteTaskMessage = "Permanently delete this task? This cannot be undone."
    override val subPage = "Sub-page"
    override val restore = "Restore"
    override val deleteForever = "Delete forever"

    // Note page
    override val repeatingTask = "Repeating task"
    override val upcomingReminder = "Upcoming reminder"
    override val dueDate = "Due date"
    override val moreOptions = "More options"
    override val deleteCompleted = "Delete completed"
    override val markDone = "Done"
    override val markTodo = "Todo"
    override val priority = "Priority"
    override val copy = "Copy"
    override val edit = "Edit"
    override val repeatLabel = "Repeat"
    override val moveTo = "Move to"
    override val reminder = "Reminder"
    override val current = "Current"
    override val repeatTask = "Repeat task"
    override val startFrom = "Start from"
    override val remindMe = "Remind me"
    override val clear = "Clear"
    override val selectStartDate = "Select start date"

    // Timeline, Reminder, Due date
    override val timelineRepeating = "Repeating"
    override val timelineUnscheduled = "Unscheduled"
    override val timelineCompleted = "Completed"
    override val timelineEmpty =
        "Nothing scheduled yet.\n\nAdd a task below, or open any task's menu and set a due date — it will show up here."
    override val setReminder = "Set Reminder"
    override val changeDateTime = "Change date & time"
    override val enabled = "Enabled"
    override val remove = "Remove"
    override val selectDate = "Select date"
    override val back = "Back"
    override val permissionRequired = "Permission required"
    override val permissionMessage =
        "To set reminders, we need permission to send notifications and schedule alarms."
    override val continueLabel = "Continue"

    // Functions
    override fun themeLabel(mode: ThemeMode) = when (mode) {
        ThemeMode.LIGHT -> "Light"
        ThemeMode.DARK -> "Dark"
        ThemeMode.SYSTEM -> "System"
        ThemeMode.DAY_NIGHT -> "Day/Night"
    }

    override fun archiveCount(count: Int) = "Archive ($count)"

    override fun deletePageMessage(pageName: String) =
        "Permanently delete page '$pageName' and all its notes? This cannot be undone."

    override fun completedTasks(count: Int) = "Completed tasks ($count)"

    override fun moveCompletedMessage(count: Int) = "Move all $count completed tasks to trash?"
}

object FarsiStrings : AppStrings {
    // Common
    override val cancel = "لغو"
    override val save = "ذخیره"

    // Settings
    override val settings = "تنظیمات"
    override val theme = "پوسته"
    override val language = "زبان"
    override val trash = "سطل زباله"
    override val follow = "دنبال کردن"
    override val share = "اشتراک‌گذاری"
    override val rate = "امتیاز دادن"
    override val shareText =
        "با Pentastic کارهات رو انجام بده!\nhttps://play.google.com/store/apps/details?id=app.pentastic"

    // Index page
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

    // Home, Archive, Trash
    override val addTask = "یک کار اضافه کن..."
    override val addNote = "یک یادداشت اضافه کن..."
    override val addPage = "یک صفحه‌ی جدید اضافه کن..."
    override val archiveEmpty = "بایگانی خالی است"
    override val empty = "خالی کردن"
    override val trashEmpty = "سطل زباله خالی است"
    override val pages = "صفحه‌ها"
    override val tasks = "کارها"
    override val emptyTrash = "خالی کردن سطل زباله"
    override val emptyTrashMessage = "همه‌ی موارد سطل زباله برای همیشه حذف شوند؟ این عمل قابل بازگشت نیست."
    override val deleteAll = "حذف همه"
    override val deletePermanently = "حذف دائمی"
    override val deleteTaskMessage = "این مورد برای همیشه حذف شود؟ این عمل قابل بازگشت نیست."
    override val subPage = "زیرصفحه"
    override val restore = "بازیابی"
    override val deleteForever = "حذف برای همیشه"

    // Note page
    override val repeatingTask = "کار تکرارشونده"
    override val upcomingReminder = "یادآور پیش‌رو"
    override val dueDate = "تاریخ سررسید"
    override val moreOptions = "گزینه‌های بیشتر"
    override val deleteCompleted = "حذف انجام‌شده‌ها"
    override val markDone = "انجام شد"
    override val markTodo = "انجام نشده"
    override val priority = "اولویت"
    override val copy = "کپی"
    override val edit = "ویرایش"
    override val repeatLabel = "تکرار"
    override val moveTo = "انتقال به"
    override val reminder = "یادآور"
    override val current = "فعلی"
    override val repeatTask = "تکرار کار"
    override val startFrom = "شروع از"
    override val remindMe = "یادآوری کن"
    override val clear = "پاک کردن"
    override val selectStartDate = "انتخاب تاریخ شروع"

    // Timeline, Reminder, Due date
    override val timelineRepeating = "تکرارشونده"
    override val timelineUnscheduled = "بدون برنامه"
    override val timelineCompleted = "انجام‌شده"
    override val timelineEmpty =
        "هنوز چیزی برنامه‌ریزی نشده.\n\nپایین یک کار اضافه کن، یا منوی هر کار را باز کن و تاریخ سررسید بگذار؛ اینجا نمایش داده می‌شود."
    override val setReminder = "تنظیم یادآور"
    override val changeDateTime = "تغییر تاریخ و زمان"
    override val enabled = "فعال"
    override val remove = "برداشتن"
    override val selectDate = "انتخاب تاریخ"
    override val back = "بازگشت"
    override val permissionRequired = "نیاز به دسترسی"
    override val permissionMessage =
        "برای تنظیم یادآور، به اجازه‌ی ارسال اعلان و زمان‌بندی هشدار نیاز داریم."
    override val continueLabel = "ادامه"

    // Functions
    override fun themeLabel(mode: ThemeMode) = when (mode) {
        ThemeMode.LIGHT -> "روشن"
        ThemeMode.DARK -> "تیره"
        ThemeMode.SYSTEM -> "سیستم"
        ThemeMode.DAY_NIGHT -> "روز/شب"
    }

    override fun archiveCount(count: Int) = "بایگانی ($count)"

    override fun deletePageMessage(pageName: String) =
        "صفحه‌ی «$pageName» و همه‌ی یادداشت‌های آن برای همیشه حذف شود؟ این عمل قابل بازگشت نیست."

    override fun completedTasks(count: Int) = "کارهای انجام‌شده ($count)"

    override fun moveCompletedMessage(count: Int) = "همه‌ی $count کار انجام‌شده به سطل زباله منتقل شوند؟"
}

fun stringsFor(language: AppLanguage): AppStrings = when (language) {
    AppLanguage.ENGLISH -> EnglishStrings
    AppLanguage.FARSI -> FarsiStrings
}

val LocalStrings = staticCompositionLocalOf<AppStrings> { EnglishStrings }