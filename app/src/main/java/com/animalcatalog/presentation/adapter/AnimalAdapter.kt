package com.animalcatalog.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.animalcatalog.R
import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.usecase.RemoveAnimalItemFromDatabaseUseCase
import com.animalcatalog.presentation.interfaces.AddAndRemoveFavorite
import com.animalcatalog.presentation.interfaces.SetDataToAnimalItem

class AnimalAdapter(
    private val setData: SetDataToAnimalItem,
    private val addAndRemoveFavorite: AddAndRemoveFavorite,
    private val animalArray: ArrayList<AnimalItem>
): RecyclerView.Adapter<AnimalHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AnimalHolder(inflater.inflate(R.layout.animal_item, parent,false), addAndRemoveFavorite)
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        holder.bind(animalArray[position])
        holder.itemView.setOnClickListener {
            setData.setDataToAnimalItem(animalArray[position])
        }
    }

    override fun getItemCount(): Int {
        return animalArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAnimalAdapter(listItems: List<AnimalItem>) {

        animalArray.clear()
        animalArray.addAll(listItems)
        notifyDataSetChanged()
    }

    fun removeAnimalItem(position: Int, removeAnimalItemFromDatabaseUseCase: RemoveAnimalItemFromDatabaseUseCase) {

        removeAnimalItemFromDatabaseUseCase.removeAnimalItemFromDatabase(animalArray[position].id.toString())
        animalArray.removeAt(position)
        notifyItemRangeChanged(0, animalArray.size)
        notifyItemRemoved(position)
    }
}