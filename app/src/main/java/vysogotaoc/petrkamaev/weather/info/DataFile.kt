package vysogotaoc.petrkamaev.weather.info


data class WeatherList(val date: String,
                       val tod: String,
                       val temp: String) {
}
data class FullWeatherInfo (val date: String,
                            val tod: String,
                            val pressure: String,
                            val temp: String,
                            val feel: String,
                            val humidity: String,
                            val wind: String,
                            val cloud: String) {
    override fun toString(): String {
        return "Date: " + date + "\n" + "TOD: " + tod + "\n" +
                "Pressure: " + pressure + "\n" + "Temperature: " + temp + "\n" +
                "Feel: " + feel + "\n" + "Humidity: " + humidity + "\n" +
                "Wind: " + wind + "\n" + "Cloud: " + cloud + "\n"
    }
}

object DataFile {
    var current_date: String = "2019-02-04"
    lateinit var orientation: String
}