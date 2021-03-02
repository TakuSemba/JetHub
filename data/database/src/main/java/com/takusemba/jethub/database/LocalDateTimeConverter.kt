package com.takusemba.jethub.database

import androidx.room.TypeConverter
import com.takusemba.jethub.model.DateFormatters
import java.time.LocalDateTime

class LocalDateTimeConverter {

  @TypeConverter
  fun fromTimeToDate(localDateTime: String): LocalDateTime {
    return LocalDateTime.from(DateFormatters.ofApiResult().parse(localDateTime))
  }

  @TypeConverter
  fun fromDateToTime(localDateTime: LocalDateTime): String {
    return localDateTime.format(DateFormatters.ofDatabase())
  }
}
