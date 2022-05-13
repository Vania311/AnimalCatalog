package com.animalcatalog.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("number") var number : String? = null,
    @SerializedName("language") var language : String? = null,
    @SerializedName("insult") var insult : String? = null,
    @SerializedName("created") var created : String? = null,
    @SerializedName("shown") var shown : String? = null,
    @SerializedName("createdby") var createdby : String? = null,
    @SerializedName("active") var active : String? = null,
    @SerializedName("comment") var comment : String? = null
)

