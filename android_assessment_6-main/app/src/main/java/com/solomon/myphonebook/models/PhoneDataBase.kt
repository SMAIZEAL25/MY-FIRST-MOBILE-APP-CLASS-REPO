package com.solomon.myphonebook.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class PhoneDataBase: RoomDatabase() {
    abstract fun contactDoa(): ContactDAO
}