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

import android.app.*
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat.getSystemService
import android.widget.RemoteViews
import android.app.NotificationManager




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

    //////////////////////////////////////
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "com.example.vicky.notificationexample"
    private val description = "Test notification"
    //////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_calendar, container, false)


        ////////////////////////////
        //notificationManager = getSystemService(context.NOTIFICATION_SERVICE) as NotificationManager
        //notificationManager = Context!!.getSystemService(Context!!.NOTIFICATION_SERVICE) as NotificationManager
        ////////////////////////////

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

/////////////////////////////////////////////////////////////////////////////////////
       /* v.recyclerView2.setOnClickListener {
            var trans = requireActivity().supportFragmentManager.beginTransaction()
            trans.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
            trans.replace(R.id.main_frame,ChooseReminderFragment())
            trans.addToBackStack(null)
            trans.commit()

            val pendingIntent = Pen(requireContext(),0,trans,PendingIntent.FLAG_UPDATE_CURRENT)

            val contentView = RemoteViews(packageName,R.layout.notification_layout)
            contentView.setTextViewText(R.id.tv_title,"CodeAndroid")
            contentView.setTextViewText(R.id.tv_content,"Text notification")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(requireContext(),channelId)
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.applogo)
                    .setLargeIcon(BitmapFactory.decodeResource(requireActivity().resources,R.drawable.applogo))
                    .setContentIntent(pendingIntent)
            }else{

                builder = Notification.Builder(requireContext())
                    .setContent(contentView)
                    .setSmallIcon(R.drawable.applogo)
                    .setLargeIcon(BitmapFactory.decodeResource(requireActivity().resources,R.drawable.applogo))
                    .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234,builder.build())
        }*/
/////////////////////////////////////////////////////////////////////////////////////////

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

