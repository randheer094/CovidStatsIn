package me.randheer.covidstatsin.android.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.randheer.covidstatsin.android.databinding.ItemDistrictInfoBinding
import me.randheer.covidstatsin.domain.model.DistrictUiModel

class DistrictListAdapter : RecyclerView.Adapter<DistrictListAdapter.ViewHolder>() {

    private val items = mutableListOf<DistrictUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemDistrictInfoBinding =
            ItemDistrictInfoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        with(holder.binding) {
            nameTV.text = data.name
            confirmedTitleTv.text = data.confirmedTitle
            confirmedTv.text = data.confirmed
            deceasedTitleTv.text = data.deceasedTitle
            deceasedTv.text = data.deceased
            recoveredTitleTv.text = data.recoveredTitle
            recoveredTv.text = data.recovered
            testedTitleTv.text = data.testedTitle
            testedTv.text = data.tested
            vaccinated1TitleTv.text = data.vaccinated1Title
            vaccinated1Tv.text = data.vaccinated1
            vaccinated2TitleTv.text = data.vaccinated2Title
            vaccinated2Tv.text = data.vaccinated2
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<DistrictUiModel>) {
        with(items) {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemDistrictInfoBinding) : RecyclerView.ViewHolder(binding.root)

}