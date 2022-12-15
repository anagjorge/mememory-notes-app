package com.projetos.core.data

data class Note(
    var title: String,
    var content: String,
    var creatrionTime: Long,
    var updateTime: Long,
    var id: Long = 0,
    var wordCount: Int = 0
)