package app.pentastic.data

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun deviceAppLanguage(): AppLanguage =
    if (NSLocale.currentLocale.languageCode == "fa") AppLanguage.FARSI else AppLanguage.ENGLISH
