package com.afzal.githubbrowser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.afzal.githubbrowser.database.RepoDatabase
import com.afzal.githubbrowser.database.RepoRepository
import com.afzal.githubbrowser.entities.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoViewModel(application: Application) : AndroidViewModel(application) {

    val allRepoItems: LiveData<List<Repo>>

    private val repository: RepoRepository

    init {
        val dao = RepoDatabase.getDatabase(application).getDao()
        repository = RepoRepository(dao)
        allRepoItems = repository.allRepos
    }

    fun insertRepo(repoItem: Repo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRepo(repoItem)
    }

    fun deleteRepo(repoItem: Repo) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteRepo(repoItem)
    }
}