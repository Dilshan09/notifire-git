package by.matthewvirus.medicinenotifier.data.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.matthewvirus.medicinenotifier.data.domain.dao.MedicineDao
import by.matthewvirus.medicinenotifier.data.domain.dataconverters.Converters
import by.matthewvirus.medicinenotifier.data.model.Medicine
import by.matthewvirus.medicinenotifier.util.DATABASE_NAME

// Define the Room database and specify the entities it contains
@Database(entities = [Medicine::class], version = 1)
@TypeConverters(Converters::class)
abstract class MedicineNotifierDatabase : RoomDatabase() {

    // Define an abstract method to retrieve the MedicineDao
    abstract fun medicineDao(): MedicineDao

    companion object {

        @Volatile
        private var INSTANCE: MedicineNotifierDatabase? = null

        // Get an instance of the database or create a new one if it doesn't exist
        fun getDatabase(context: Context) : MedicineNotifierDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicineNotifierDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}