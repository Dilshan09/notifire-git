package by.matthewvirus.medicinenotifier.ui.aboutMedicine

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import by.matthewvirus.medicinenotifier.data.domain.MedicineNotifierDatabase
import by.matthewvirus.medicinenotifier.data.model.Medicine
import by.matthewvirus.medicinenotifier.data.domain.repository.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutMedicineViewModel(application: Application) : AndroidViewModel(application) {

    // Create a reference to the MedicineRepository and LiveData for all medicines
    private val medicineRepository: MedicineRepository
    private val readAll: LiveData<List<Medicine>>

    // Initialize the ViewModel
    init {
        //Create a MedicineRepository
        val medicineDB = MedicineNotifierDatabase.getDatabase(application)
        medicineRepository = MedicineRepository(medicineDB.medicineDao())
        // Retrieve LiveData containing all medicines
        readAll = medicineRepository.getMedicines()
    }

    // Update a medicine in the database
    fun updateMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            medicineRepository.updateMedicine(medicine)
        }
    }

    // Delete a medicine from the database
    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            medicineRepository.deleteMedicine(medicine)
        }
    }

    // Retrieve LiveData containing all medicines
    fun getMedicines(): LiveData<List<Medicine>> = readAll
}