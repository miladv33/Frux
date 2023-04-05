package com.example.frux.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the new 'hits' table with the desired schema
        database.execSQL(
            "CREATE TABLE hits_new (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "comments INTEGER NOT NULL," +
                    "downloads INTEGER NOT NULL," +
                    "large_image_url TEXT NOT NULL," +
                    "likes INTEGER NOT NULL," +
                    "preview_url TEXT NOT NULL," + // Add the new 'preview_url' column
                    "tags TEXT NOT NULL," +
                    "user TEXT NOT NULL," +
                    "user_image_url TEXT NOT NULL," +
                    "user_id INTEGER NOT NULL," +
                    "views INTEGER NOT NULL," +
                    "searchedKey TEXT DEFAULT NULL" +
                    ")"
        )

        // Copy the data from the old table to the new table
        database.execSQL(
            "INSERT INTO hits_new " +
                    "(" +
                    "id, comments, downloads, large_image_url, likes, preview_url, tags, user, user_image_url, user_id, views" +
                    ")" +
                    "SELECT " +
                    "(SELECT COUNT(*) FROM hits) + ROW_NUMBER() OVER (ORDER BY id), " + // Generate new 'id' values
                    "comments, downloads, large_image_url, likes, preview_height, tags, user, user_image_url, user_id, views " +
                    "FROM hits"
        )

        // Remove the old 'hits' table
        database.execSQL("DROP TABLE hits")

        // Rename the new table to the original table name
        database.execSQL("ALTER TABLE hits_new RENAME TO hits")
    }

}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the new table for Theme entity
        database.execSQL("CREATE TABLE IF NOT EXISTS `theme` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `theme_is_dark` INTEGER NOT NULL)")
    }
}
