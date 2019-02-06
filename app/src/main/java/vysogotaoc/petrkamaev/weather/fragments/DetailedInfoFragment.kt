package vysogotaoc.petrkamaev.weather.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_detailed_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import vysogotaoc.petrkamaev.weather.R
import vysogotaoc.petrkamaev.weather.info.*

class DetailedInfoFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detailed_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter<FullWeatherInfo>(context, android.R.layout.simple_list_item_1)
        detailed_list_view_id.adapter = adapter
        val retrofit = Retrofit.Builder()
            .baseUrl("http://icomms.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(APIServiceSecond::class.java)
        val call = apiService.getMeteo(24)
        call.enqueue(object: Callback<List<FullWeatherInfo>> {
            override fun onFailure(call: Call<List<FullWeatherInfo>>, t: Throwable) {
                Toast.makeText(context, "Connection error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<FullWeatherInfo>>, response: Response<List<FullWeatherInfo>>) {
                adapter.addAll(response.body()!!.filter { it.date == DataFile.current_date })
                adapter.notifyDataSetChanged()
            }
        })
    }
}