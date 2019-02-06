package vysogotaoc.petrkamaev.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.custom_list.*
import kotlinx.android.synthetic.main.fragment_short_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import vysogotaoc.petrkamaev.weather.R
import vysogotaoc.petrkamaev.weather.adapters.CustomAdapter
import vysogotaoc.petrkamaev.weather.info.APIService
import vysogotaoc.petrkamaev.weather.info.WeatherList

class ShortInfoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_short_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://icomms.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(APIService::class.java)
        val call = apiService.getMeteo(27)
        call.enqueue(object: Callback<List<WeatherList>> {
            override fun onFailure(call: Call<List<WeatherList>>, t: Throwable) {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<WeatherList>>, response: Response<List<WeatherList>>) {
                list_view_id.layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
                val adapter = CustomAdapter(requireContext(), response.body()!!.filter { it.tod == "0" })
                list_view_id.adapter = adapter
            }

        })
//        swipe_refresh_id.setOnRefreshListener {
//            val call = apiService.getMeteo(35)
//            call.enqueue(object: Callback<List<WeatherList>> {
//                override fun onFailure(call: Call<List<WeatherList>>, t: Throwable) {
//                    Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
//                }
//                override fun onResponse(call: Call<List<WeatherList>>, response: Response<List<WeatherList>>) {
//                    list_view_id.layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
//                    val adapter = CustomAdapter(requireContext(), response.body()!!.filter { it.tod == "0" })
//                    list_view_id.adapter = adapter
//                }
//            })
//            swipe_refresh_id.setRefreshing(false)
//        }
    }
}
