package com.yousefagawin.codingchallengeapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yousefagawin.codingchallengeapp.data.db.entities.SongDao
import com.yousefagawin.codingchallengeapp.datamodels.SongEntity

//whenever you make a database class on room you need to make it abstract
//you put all the entities that you have in the @Database()
@Database(
    entities = [SongEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase(){

    //you do this because you need to create an abstract function in all of the DAOs used
    abstract fun songDao(): SongDao


    //this will create the AppDatabase
    companion object{

        @Volatile //@Volatile in kotlin means this variable is immediately visible to all other threads
        private var instance: AppDatabase? = null
        private val LOCK = Any()    //this will be used to ensure we dont create two instance of our database

        //this invoke will check if the instance is null
        //if(instance != null) --> it will immediately return the instance
        //else --> it will create the synchronized(LOCK)
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

            //also{} is used to assign the buildDatabase return value to the instance
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        //this will build the database for us
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,    //this is the abstract class that is extending the RoomDatabase()
                "MyDatabase.db"
            ).build()
    }
}