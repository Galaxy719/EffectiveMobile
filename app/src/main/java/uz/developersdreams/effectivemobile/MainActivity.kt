package uz.developersdreams.effectivemobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.developersdreams.effectivemobile.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        initializing()
        setContentView(binding.root)
    }

    private fun initializing() {
        val nav = supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        binding.mainBottomNav.setupWithNavController(nav.navController)
    }
}