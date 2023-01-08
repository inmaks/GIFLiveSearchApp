package com.chili.GIFLiveSearch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chili.GIFLiveSearch.API.GifRepo
import com.chili.GIFLiveSearch.Data.GifArray
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private var loadingNew: Boolean = false

    //LiveData because it was in google Developers tutorials
    private val _q: MutableLiveData<String> = MutableLiveData("")

    val q: LiveData<String> = _q

    private val _gifs: MutableLiveData<GifArray?> = MutableLiveData(null)

    val gifs: LiveData<GifArray?> = _gifs

    fun onTextChange(newText: String) {

        _q.value = newText

    }

    fun onScrolledToTheEnd() {
        //isScrolledToTheEnd triggers all the time it is scrolled to the end
        //so I need check
        if (!loadingNew) {
            loadingNew = true
            //For request not to block other threads
            viewModelScope.launch {
                kotlin.runCatching {
                    GifRepo.getGifs(q.value!!, offset = _gifs.value!!.gifs.size)
                }.onSuccess {
                    _gifs.value!!.gifs.addAll(it.gifs)
                    loadingNew = false
                }.onFailure {
                    loadingNew = false
                }
            }
        }
    }

    //Background check for updated search
    private fun startRepeatingJob(timeInterval: Long): Job {
        var latestSearched = ""
        return CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                //Pause to not send request on every input in searchView
                delay(timeInterval)
                if (latestSearched != q.value!! && q.value!! != "") {
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

    fun onCrossClick() {
        _q.value = ""
    }

    //It just works, decided not to break anything :D
    init {
        startRepeatingJob(600)
    }
}