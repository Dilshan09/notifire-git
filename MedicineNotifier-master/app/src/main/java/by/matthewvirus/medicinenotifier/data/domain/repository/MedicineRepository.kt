package by.matthewvirus.medicinenotifier.data.domain.repository

import androidx.lifecycle.LiveData
import by.matthewvirus.medicinenotifier.data.domain.dao.MedicineDao
import by.matthewvirus.medicinenotifier.data.model.Medicine

class MedicineRepository (private val medicineDao: MedicineDao) {

    // Retrieves a list of all medicines from the database
    fun getMedicines(): LiveData<List<Medicine>> =
        medicineDao.getMedicines()

    // Deletes a medicine from the database
    fun deleteMedicine(medicine: Medicine) =
        medicineDao.deleteMedicine(medicine)

    // Updates an existing medicine in the database
    fun updateMedicine(medicine: Medicine) =
        medicineDao.updateMedicine(medicine)

    // Adds a new medicine to the database
    fun addMedicine(medicine: Medicine) =
        medicineDao.addMedicine(medicine)
}