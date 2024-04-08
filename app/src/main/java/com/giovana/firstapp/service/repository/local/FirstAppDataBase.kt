package com.giovana.firstapp.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.giovana.firstapp.service.model.Pessoa

@Database(entities = [Pessoa::class], version = 1)
abstract class FirstAppDataBase: RoomDatabase() {
    abstract fun  pessoaDao(): PessoaDAO
    companion object{
        @Volatile
        private lateinit var INSTANCE: FirstAppDataBase

        fun getDataBase(context: Context): FirstAppDataBase{
            if(!Companion::INSTANCE.isInitialized){
                synchronized(lock = this){
                    INSTANCE = Room.databaseBuilder(
                        context, FirstAppDataBase::class.java,
                        name = "firstapp_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}