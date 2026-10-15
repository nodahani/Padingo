package app.pentastic.data

import java.util.Locale

actual fun deviceAppLanguage(): AppLanguage =
    if (Locale.getDefault().language == "fa") AppLanguage.FARSI else AppLanguage.ENGLISH
