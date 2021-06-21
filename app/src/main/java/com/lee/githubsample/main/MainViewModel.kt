package com.lee.githubsample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee.githubsample.Event
import com.lee.githubsample.R
import com.lee.githubsample.data.Repository
import com.lee.githubsample.data.source.GithubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val githubRepository: GithubRepository) :
    ViewModel() {

    var repositories: List<Repository>? = null

    val query = MutableLiveData<String>()

    val resultState = MutableLiveData<Boolean>(true)

    private val _loadRepositories = MutableLiveData<Event<Unit>>()
    val loadRepositories: LiveData<Event<Unit>> get() = _loadRepositories

    private val _toast = MutableLiveData<Event<Int>>()
    val toast: LiveData<Event<Int>> get() = _toast

    fun getRepository() {
        query.value?.let {
            viewModelScope.launch {
                githubRepository.getRepositories(it)?.let {
                    repositories = it.items
                    _loadRepositories.postValue(Event(Unit))
                    if (it.items.isEmpty()) {
                        _toast.postValue(Event(R.string.empty_search_result))
                        resultState.postValue(false)
                    } else {
                        resultState.postValue(true)
                    }
                }
            }
        }
    }
}