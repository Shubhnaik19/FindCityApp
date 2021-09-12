package com.findCity.projectFindCity

import org.springframework.stereotype.Service

@Service
class FindCityServiceImpl(val findCityClient: FindCityClient) : FindCityService {
    override fun findNoOfCity(initialLetter:String?): FindCityDataClass? {
        val findCity = findCityClient.findNoOfCityFromApi()
        if(findCity.cod == "200") {
            val findCityFiltered =
                findCity.list?.filter { city -> city.name?.startsWith(initialLetter!!, ignoreCase = true) == true }
            return FindCityDataClass(cod = findCity.cod, list = findCityFiltered)
        }
        return findCity
    }
}