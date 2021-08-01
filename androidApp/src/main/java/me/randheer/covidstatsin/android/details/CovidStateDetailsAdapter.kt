package me.randheer.covidstatsin.android.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import me.randheer.covidstatsin.android.databinding.ItemCovidInfoBinding
import me.randheer.covidstatsin.db.CovidDistrictStats

class CovidStateDetailsAdapter : RecyclerView.Adapter<CovidStateDetailsAdapter.ViewHolder>() {

    private val items = mutableListOf<CovidDistrictStats>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCovidInfoBinding =
            ItemCovidInfoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        with(holder.binding) {
            nameTV.text = data.name

            confirmedTitleTv.text = "Confirmed"
            confirmedTv.text = data.confirmed.displayValue()

            deceasedTitleTv.text = "Deceased"
            deceasedTv.text = data.deceased.displayValue()

            recoveredTitleTv.text = "Recovered"
            recoveredTv.text = data.recovered.displayValue()

            testedTitleTv.text = "Tested"
            testedTv.text = data.tested.displayValue()

            vaccinated1TitleTv.text = "Partially Vaccinated"
            vaccinated1Tv.text = data.vaccinated1.displayValue()

            vaccinated2TitleTv.text = "Fully Vaccinated"
            vaccinated2Tv.text = data.vaccinated2.displayValue()

            updatedAtTv.isVisible = false
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<CovidDistrictStats>) {
        with(items) {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    private fun Long?.displayValue(): String {
        return if (this == null || this == 0L) "Not Available" else this.toString()
    }

    class ViewHolder(val binding: ItemCovidInfoBinding) : RecyclerView.ViewHolder(binding.root)

}