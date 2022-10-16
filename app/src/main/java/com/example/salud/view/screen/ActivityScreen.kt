package com.example.salud.view.screen

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.salud.services.DetectedActivityReceiver
import com.example.salud.viewmodel.ActivityViewModel
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.first


@Composable
fun ActivityScreen(viewModel: ActivityViewModel= hiltViewModel()) {

    val ctx = LocalContext.current
    val transitionsList = mutableListOf<ActivityTransition>()

    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.RUNNING)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build()
    )

    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.RUNNING)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
            .build()
    )
    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.STILL)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build()
    )

    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.STILL)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
            .build()
    )
    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.WALKING)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build()
    )

    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.WALKING)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
            .build()
    )
    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.IN_VEHICLE)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build()
    )

    transitionsList.add(
        ActivityTransition.Builder()
            .setActivityType(DetectedActivity.IN_VEHICLE)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
            .build()
    )

    val finalRequest = ActivityTransitionRequest(transitionsList)

//
//// creating the receiver
//    val receiver = DetectedActivityReceiver()
////
////// registring the receiver
////    LocalBroadcastManager.getInstance(ctx).registerReceiver(
////        receiver, IntentFilter(
////            DetectedActivityReceiver()
////                .RECEIVER_ACTION
////        )
////    )

    // creating the pending intent
    val intent = Intent(ctx, DetectedActivityReceiver(viewModel)::class.java)
    val pendingIntent =
        PendingIntent.getBroadcast(ctx, 139, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    val client = ActivityRecognition.getClient(ctx.findActivity() as Activity)
    client
        .requestActivityTransitionUpdates(finalRequest, pendingIntent)
        .addOnSuccessListener {
            Log.d("ActivityRecognition", "Transitions Api registered with success")
        }
        .addOnFailureListener { e: Exception ->
            Log.d(
                "ActivityRecognition",
                "Transitions Api could NOT be registered ${e.localizedMessage}"
            )
        }
        Toast.makeText(ctx,viewModel.message.value.toString(),Toast.LENGTH_SHORT).show()
}

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}