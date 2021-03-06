package com.example.cache.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** */
@Entity(tableName = "Counter")
data class Counter(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "title") val title: String
){
}