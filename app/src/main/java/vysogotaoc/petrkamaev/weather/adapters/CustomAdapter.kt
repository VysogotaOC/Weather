package vysogotaoc.petrkamaev.weather.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import vysogotaoc.petrkamaev.weather.R
import vysogotaoc.petrkamaev.weather.activities.MainActivity
import vysogotaoc.petrkamaev.weather.fragments.DetailedInfoFragment
import vysogotaoc.petrkamaev.weather.fragments.ShortInfoFragment
import vysogotaoc.petrkamaev.weather.info.DataFile
import vysogotaoc.petrkamaev.weather.info.DataFile.current_date
import vysogotaoc.petrkamaev.weather.info.WeatherList
import android.app.Activity
import android.app.Fragment


class CustomAdapter (val context: Context, val objects: List<WeatherList>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    var manager = (context as Activity).fragmentManager

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(vysogotaoc.petrkamaev.weather.R.layout.custom_list, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list: WeatherList = objects[position]

        holder.dateText.text = "" + list.date
        holder.tempText.text = "" + list.temp
        holder.card.setOnClickListener{
//            val intent = Intent(context, DetailedInfoFragment::class.java)
//            intent.putExtra("date", list.date)
//            holder.itemView.context.startActivity(intent)
            DataFile.current_date = list.date
            Log.i("test", DataFile.current_date)
            if (DataFile.orientation == "portrait") {
                val transaction = manager.beginTransaction()
                val fragment = DetailedInfoFragment()
                //Здесь почему-то не работает замена фрагмента. На форумах пишут, что это из-за импорта import android.support.v4.app.Fragment.
                //Мол нужно использовать другой
                //transaction.replace(vysogotaoc.petrkamaev.weather.R.id.fragment_holder_portrait, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateText = itemView.findViewById(vysogotaoc.petrkamaev.weather.R.id.date_text_view_id) as TextView
        val tempText = itemView.findViewById(vysogotaoc.petrkamaev.weather.R.id.temp_text_view_id) as TextView
        val card = itemView.findViewById(vysogotaoc.petrkamaev.weather.R.id.cardView) as CardView
    }
}