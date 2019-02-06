package vysogotaoc.petrkamaev.weather.info

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServiceSecond {
    @GET("inf/meteo.php")
    fun getMeteo(@Query("tid") tid: Int): Call<List<FullWeatherInfo>>
}