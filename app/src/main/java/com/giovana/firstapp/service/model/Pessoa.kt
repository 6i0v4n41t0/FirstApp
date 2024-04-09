package com.giovana.firstapp.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pessoa")
data class Pessoa(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nome: String = "",
    var idade: Int = 0,
    var sexo: String = "",
    var faixa: String = ""
)
