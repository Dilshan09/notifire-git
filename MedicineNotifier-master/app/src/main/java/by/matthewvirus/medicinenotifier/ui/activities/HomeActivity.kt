package by.matthewvirus.medicinenotifier.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import by.matthewvirus.medicinenotifier.R
import by.matthewvirus.medicinenotifier.databinding.ActivityHomeBinding
import by.matthewvirus.medicinenotifier.ui.medicinesList.MedicineListFragment
import by.matthewvirus.medicinenotifier.util.INDEX_ARGUMENT

interface Communicator {
    fun passArgumentsBetweenFragments(index: Int): Bundle
}

class HomeActivity :
    AppCompatActivity(),
    MedicineListFragment.Callbacks,
    Communicator
{

    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onFragmentTransition(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_nav_host_fragment, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    interface Callbacks {
        fun hideBottomNavigationView(flag: Boolean)
    }

    override fun passArgumentsBetweenFragments(index: Int) : Bundle {
        val bundle = Bundle()
        bundle.putInt(INDEX_ARGUMENT, index)
        return bundle
    }

}