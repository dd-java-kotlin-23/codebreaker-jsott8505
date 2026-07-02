package edu.cnm.deepdive.codebreaker.app.repesitory

import androidx.lifecycle.LiveData

interface PreferencesRepository {

    val codeLength: LiveData<Int>

    val poolSize: LiveData<Int>

    val showText: LiveData<Boolean>

}