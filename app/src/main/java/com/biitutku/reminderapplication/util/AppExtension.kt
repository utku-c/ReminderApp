package com.biitutku.reminderapplication.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun LocalDateTime.formatDateTime(formatter: DateTimeFormatter): String {
   return this.format(formatter)
}

val DATA_FORMATTER_DATE_YYYY_MM_DD: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
val DATA_FORMATTER_DATE_YYYY_MM: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM")
val DATA_FORMATTER_TIME_HH_MM: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
