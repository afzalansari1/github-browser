package com.afzal.githubbrowser.database

import androidx.lifecycle.LiveData
import com.afzal.githubbrowser.entities.Repo

class RepoRepository(private val dao: RepoDao) {

    val allRepos: LiveData<List<Repo>> = dao.getClassItems()

    suspend fun insertRepo(repoItem: Repo) = dao.insertRepo(repoItem)

    suspend fun deleteRepo(repoItem: Repo) = dao.deleteRepo(repoItem)
}