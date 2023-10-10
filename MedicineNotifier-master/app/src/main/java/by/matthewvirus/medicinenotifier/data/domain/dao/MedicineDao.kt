package by.matthewvirus.medicinenotifier.data.domain.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.matthewvirus.medicinenotifier.data.model.Medicine
import java.util.*

@Dao
interface MedicineDao {

    // Retrieves a list of all medicines from the database
    @Query("SELECT * FROM medicine")
    fun getMedicines(): LiveData<List<Medicine>>

    // Inserts a new medicine into the database
    @Insert
    fun addMedicine(medicine: Medicine)

    // Deletes a medicine from the database
    @Delete
    fun deleteMedicine(medicine: Medicine)

    // Updates an existing medicine in the database
    @Update
    fun updateMedicine(medicine: Medicine)
}