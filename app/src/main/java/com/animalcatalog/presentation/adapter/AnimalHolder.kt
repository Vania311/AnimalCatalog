package com.animalcatalog.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.animalcatalog.databinding.AnimalItemBinding
import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.presentation.interfaces.AddAndRemoveFavorite

class AnimalHolder(itemView: View, private val addAndRemoveFavorite: AddAndRemoveFavorite): RecyclerView.ViewHolder(itemView) {

    private val binding = AnimalItemBinding.bind(itemView)
    fun bind(item: AnimalItem) = with(binding) {
        nameTextview.text = item.name
        categoryTextview.text = item.category
        timeTextview.text = item.time
        favoritesCheckbox.isChecked = item.favorite.toBoolean()

        favoritesCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addAndRemoveFavorite.addFavorite(item)

            } else {
                addAndRemoveFavorite.removeFavorite(item)
            }
        }
    }
}