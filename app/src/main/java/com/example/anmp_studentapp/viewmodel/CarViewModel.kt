package com.example.anmp_studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_studentapp.model.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarViewModel(application: Application): AndroidViewModel(application) {
    var carsLD = MutableLiveData<ArrayList<Car>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        carLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        //use this if you are using mobile phone (dont forget to change IP address)
        val url = "http://192.168.87.132/datasets/cars.json"

        // use this if you are using emulator
        //val url = "http://10.0.2.2/datasets/cars.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<ArrayList<Car>>() {}.type
                val result = Gson().fromJson<List<Car>>(it, sType)
                carsLD.value = result as ArrayList<Car>?

                Log.d("showvolley", it)
                loadingLD.value = false
            }, {
                loadingLD.value = false
                carLoadErrorLD.value = false
                Log.d("error", it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}