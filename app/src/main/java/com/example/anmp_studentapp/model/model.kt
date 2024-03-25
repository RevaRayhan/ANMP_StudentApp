package com.example.anmp_studentapp.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)

data class Car(
    var id:Int,
    var make:String?,
    var model:String?,
    var year:String?,
    var color:String?,
    var price:Int?,
    var features:List<String>?,
    var specs:Specs?
)

data class Specs(
    var engine:String?,
    var transmission:String?,
    var fuel_type:String?
)