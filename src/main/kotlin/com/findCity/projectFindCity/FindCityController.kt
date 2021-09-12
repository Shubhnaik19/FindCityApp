package com.findCity.projectFindCity

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@CrossOrigin(origins = ["http://localhost:3000/"])
@RestController
class FindCityController(val findCityService: FindCityService) {

    @GetMapping("/city")
    fun getCity(@RequestParam letter:String? = null): ResponseEntity<FindCityDataClass>{
            val findcity = findCityService.findNoOfCity(letter)
            return ResponseEntity(findcity, HttpStatus.OK)
    }
}