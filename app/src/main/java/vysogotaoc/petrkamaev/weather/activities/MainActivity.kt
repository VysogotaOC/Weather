package vysogotaoc.petrkamaev.weather.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import vysogotaoc.petrkamaev.weather.R
import vysogotaoc.petrkamaev.weather.adapters.CustomAdapter
import vysogotaoc.petrkamaev.weather.fragments.DetailedInfoFragment
import vysogotaoc.petrkamaev.weather.fragments.ShortInfoFragment
import vysogotaoc.petrkamaev.weather.info.DataFile
import vysogotaoc.petrkamaev.weather.info.WeatherList



class MainActivity : AppCompatActivity() {
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        БАГИ:
        1) Ломается приложение при 2 поворотах экрана portrait - landscape - portrait
        2) При нажатии на объект recyclerview не перезапускается фрагмент DetailedInfoFragment
        3) При нажатии на объект recyclerview не запускается фрагмент DetailedInfoFragment в portrait
        */

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DataFile.orientation = "landscape"
            setContentView(vysogotaoc.petrkamaev.weather.R.layout.activity_main_landscape)
            createDetailedFragment()
        } else {
            DataFile.orientation = "portrait"
            setContentView(vysogotaoc.petrkamaev.weather.R.layout.activity_main)
            createShortFragment()
        }
    }
    private fun createDetailedFragment() {
        val transaction = manager.beginTransaction()
        val fragmentFirst = ShortInfoFragment()
        val fragmentSecond = DetailedInfoFragment()
        transaction.replace(R.id.fragment_holder_landscape_first, fragmentFirst)
        transaction.replace(R.id.fragment_holder_landscape_second, fragmentSecond)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createShortFragment() {
        val transaction = manager.beginTransaction()
        val fragment = ShortInfoFragment()
        transaction.replace(R.id.fragment_holder_portrait, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

//    private fun createShortFragment_Second() {
//        val transaction = manager.beginTransaction()
//        val fragment = DetailedInfoFragment()
//        transaction.replace(R.id.fragment_holder_portrait, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}
