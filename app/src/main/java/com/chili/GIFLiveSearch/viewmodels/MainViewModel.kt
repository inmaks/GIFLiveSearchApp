package com.chili.GIFLiveSearch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chili.GIFLiveSearch.API.GifRepo
import com.chili.GIFLiveSearch.Data.GifArray
import kotlinx.coroutines.*

class MainViewModel : ViewModel(){

    private val _q : MutableLiveData<String> = MutableLiveData("")

    val q: LiveData<String> = _q

    private val _gifs : MutableLiveData<GifArray?> = MutableLiveData(null)

    val gifs: LiveData<GifArray?> = _gifs

    fun onTextChange(newText: String){

        _q.value = newText

    }

    private fun startRepeatingJob(timeInterval: Long): Job {
        var latestSearched = ""
        return CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(timeInterval)
                if(latestSearched != q.value!! && q.value!! != "") {
                    latestSearched = q.value!!
                    viewModelScope.launch {
                        kotlin.runCatching {
                            GifRepo.getGifs(q.value!!)
                        }.onSuccess {
                            _gifs.value = it
                        }.onFailure {
                            _gifs.value = null
                        }
                    }
                }
            }
        }
    }

    fun onCrossClick(){
        _q.value = ""
    }

    init {
        startRepeatingJob(600)
    }
}