package com.example.salud.services

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.salud.BuildConfig
import com.example.salud.viewmodel.ActivityViewModel
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionResult
import com.google.android.gms.location.DetectedActivity

class DetectedActivityReceiver(val viewModel:ActivityViewModel) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent)
            for (event in result!!.transitionEvents) {
                val activity = activityType(event.activityType).toString()
                val transition = transitionType(event.transitionType).toString()
                val message = "Transition: $activity ($transition)"
                Log.d("DetectedActivityReceiver", "$message $activity $transition")
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                viewModel.message.value=message
            }
        }

        Log.d("DetectedActivityReceiver", ActivityTransitionResult.hasResult(intent).toString())
    }

    private fun transitionType(transitionType: Int): String {
        return when (transitionType) {
            ActivityTransition.ACTIVITY_TRANSITION_ENTER -> "ENTER"
            ActivityTransition.ACTIVITY_TRANSITION_EXIT -> "EXIT"
            else -> "UNKNOWN"
        }
    }

    private fun activityType(activity: Int): String? {
        return when (activity) {
            DetectedActivity.IN_VEHICLE -> "IN_VEHICLE"
            DetectedActivity.STILL -> "STILL"
            DetectedActivity.WALKING -> "WALKING"
            else -> "UNKNOWN"
        }
    }
}