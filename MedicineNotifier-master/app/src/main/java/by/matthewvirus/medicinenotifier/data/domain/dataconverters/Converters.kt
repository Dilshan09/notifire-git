package by.matthewvirus.medicinenotifier.data.domain.dataconverters

import androidx.room.TypeConverter
import java.util.*

class Converters {

    // Converts a Date object to a Long value representing milliseconds
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // Converts a Long value representing milliseconds
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
}