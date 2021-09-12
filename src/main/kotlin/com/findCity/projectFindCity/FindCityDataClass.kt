package com.findCity.projectFindCity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class FindCityDataClass(
     val cod: String? = null,
     val list: List<CityList>? = null,
     val message: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityList(
    val id: String? = null,
    val name: String? = null
)