package com.kaderkayaarslan.kotlincountryslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kaderkayaarslan.kotlincountryslist.R
import com.kaderkayaarslan.kotlincountryslist.model.Country
import com.kaderkayaarslan.kotlincountryslist.util.downloadFFromUrl
import com.kaderkayaarslan.kotlincountryslist.util.placeholderProgressBar
import com.kaderkayaarslan.kotlincountryslist.view.CountryFragmentArgs
import com.kaderkayaarslan.kotlincountryslist.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdpter (val countryList: ArrayList<Country>):RecyclerView.Adapter<CountryAdpter.CountryViewHolder>(){

    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
     val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()

            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.downloadFFromUrl(countryList[position].imageUrl,
            placeholderProgressBar(holder.view.context))
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}