package com.asteriatech.mobile.di

import android.util.Log
import javax.inject.Inject

class ExampleDependency @Inject constructor() {
    fun a(){
        Log.d("called","called")
    }
}