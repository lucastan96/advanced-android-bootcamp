package com.example.roomdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract fun subscriberDao(): SubscriberDao
}