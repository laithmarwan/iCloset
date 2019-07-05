package com.example.icloset


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */



class CalendarFragment : Fragment() {
    lateinit var cats:ArrayList<Reminder>
    lateinit var adapter:ReminderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_calendar, container, false)

        v.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            
            AppInfo.day = dayOfMonth.toString()
            AppInfo.month = month.toString()
            AppInfo.year = year.toString()
            var trans = requireActivity().supportFragmentManager.beginTransaction()
            trans.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
            trans.replace(R.id.main_frame,ChooseReminderFragment())
            trans.addToBackStack(null)
            trans.commit()
        }

        val obj = icloset(requireActivity())
        val db = obj.readableDatabase
        val cur = db.rawQuery("select * from reminders", arrayOf())
        cats = ArrayList()
        cur.moveToFirst()
        while (!cur.isAfterLast){

            cats.add(Reminder(cur.getString(cur.getColumnIndex("Reminder_ID"))
                ,cur.getString(cur.getColumnIndex("Date")),cur.getString(cur.getColumnIndex("Outfit_ID"))))

            cur.moveToNext()
        }
        v.recyclerView2.layoutManager = LinearLayoutManager(activity)
        adapter = ReminderAdapter(cats,requireContext())
        v.recyclerView2.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.RIGHT ,
            ItemTouchHelper.RIGHT or ItemTouchHelper.RIGHT
        ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteItem(viewHolder.adapterPosition)
                Toast.makeText(activity,"Reminder deleted!", Toast.LENGTH_SHORT).show()
            }
        })


        itemTouchHelper.attachToRecyclerView(v.recyclerView2)

        return  v
    }
    fun deleteItem(position: Int) {
        val obj = icloset(requireActivity())
        val db = obj.writableDatabase
        db.execSQL("delete from reminders where Reminder_ID = ?" , arrayOf(cats[position].ID))
        cats.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}

