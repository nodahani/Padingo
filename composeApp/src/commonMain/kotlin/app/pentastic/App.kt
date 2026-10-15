package app.pentastic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import app.pentastic.data.AppCalendar
import app.pentastic.data.AppLanguage
import app.pentastic.data.WeekStart
import app.pentastic.data.DataStoreRepository
import app.pentastic.data.ThemeMode
import app.pentastic.nav.SetupNavGraph
import app.pentastic.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    val dataStoreRepository = koinInject<DataStoreRepository>()
    val themeOrdinal by dataStoreRepository.themeMode.collectAsState(initial = ThemeMode.DAY_NIGHT.ordinal)
    val themeMode = ThemeMode.fromOrdinal(themeOrdinal)

    val languageOrdinal by dataStoreRepository.language.collectAsState(initial = AppLanguage.FARSI.ordinal)
    val language = AppLanguage.fromOrdinal(languageOrdinal)

    val calendarOrdinal by dataStoreRepository.calendar.collectAsState(initial = AppCalendar.GREGORIAN.ordinal)
    val calendar = AppCalendar.fromOrdinal(calendarOrdinal)

    // Unset preference falls back by language: Saturday for Farsi, Monday otherwise
    val weekStartPref by dataStoreRepository.weekStart.collectAsState(initial = null)
    val weekStart = weekStartPref?.let { WeekStart.fromOrdinal(it) }
        ?: if (language == AppLanguage.FARSI) WeekStart.SATURDAY else WeekStart.MONDAY

    AppTheme(themeMode = themeMode, language = language, calendar = calendar, weekStart = weekStart) {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
}
