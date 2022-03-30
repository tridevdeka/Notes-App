package com.tricoder.androidtask.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.tricoder.androidtask.db.NotesDatabase
import com.tricoder.androidtask.entities.Notes
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    private lateinit var database: NotesDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setUp(){
        database=Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()

        dao=database.noteDao()
    }

    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun insertNotes()= runBlocking{
        val notes=Notes("Note","Note subtitle","12","My notes are here","path",id = 1)
        dao.insertNotes(notes)
        val allNotes=dao.getAllNotes()

        assertThat(allNotes).contains(notes)
    }

    @Test
    fun deleteNotes()= runBlocking{
        val notes=Notes("Note","Note subtitle","12","My notes are here","path",id = 1)
        dao.insertNotes(notes)
        dao.deleteNote(notes)
        val allNotes=dao.getAllNotes()

        assertThat(allNotes).doesNotContain(notes)
    }

}