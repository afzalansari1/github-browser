package com.afzal.githubbrowser.entities

data class Commits(
    var sha: String,
    var date: String,
    var msg: String,
    var userName: String,
    var fullName: String,
    var imgUrl: String
)