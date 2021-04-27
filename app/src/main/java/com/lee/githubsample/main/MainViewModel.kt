package com.lee.githubsample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lee.githubsample.Event
import com.lee.githubsample.data.Repository
import com.lee.githubsample.data.source.GithubRepository
import com.lee.githubsample.util.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val githubRepository: GithubRepository) :
    BaseViewModel() {

    var repositories: List<Repository>? = null

    val query = MutableLiveData<String>()

    private val _loadRepositories = MutableLiveData<Event<Unit>>()
    val loadRepositories: LiveData<Event<Unit>> get() = _loadRepositories

    fun getRepository() {
        query.value?.let {
            viewModelScope.launch(ioDispatcher) {
                githubRepository.getRepositories(it)?.let {
                    repositories = it.items
                    _loadRepositories.postValue(Event(Unit))
                }
            }
        }

    }
}