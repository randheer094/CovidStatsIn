package me.randheer.covidstatsin.android.states

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.randheer.covidstatsin.android.databinding.ItemStateInfoBinding
import me.randheer.covidstatsin.domain.model.StateUiModel

class StateInfoAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<StateInfoAdapter.ViewHolder>() {

    private val items = mutableListOf<StateUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemStateInfoBinding =
            ItemStateInfoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        Color.LTGRAY
        with(holder.binding) {
            cardView.setCardBackgroundColor(Color.parseColor(data.cardBgColor))
            root.setOnClickListener {
                if (data.clickable) {
                    onItemClick.invoke(data.code)
                }
            }
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
            updatedAtTv.text = data.updatedAt
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<StateUiModel>) {
        with(items) {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemStateInfoBinding) : RecyclerView.ViewHolder(binding.root)

}