package com.tricoder.androidtask.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tricoder.androidtask.R
import com.tricoder.androidtask.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*
import kotlin.collections.ArrayList

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var listener: OnItemClickListener? = null
    private var items = ArrayList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(arrNotesList: ArrayList<Notes>) {
        items = arrNotesList as ArrayList<Notes>
    }

    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.itemView.tvTitle.text = items[position].title
        holder.itemView.tvDesc.text = items[position].noteText
        holder.itemView.tvDateTime.text = items[position].dateTime



        if (items[position].imgPath != null) {
            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(items[position].imgPath))
            holder.itemView.imgNote.visibility = View.VISIBLE
        } else {
            holder.itemView.imgNote.visibility = View.GONE
        }


        holder.itemView.cardView.setOnClickListener {
            listener!!.onClicked(items[position].id!!)
        }

    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view)


    interface OnItemClickListener {
        fun onClicked(noteId: Int)
    }

}