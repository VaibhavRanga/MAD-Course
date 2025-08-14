package com.hadiyarajesh.week7.day3_notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun NotificationScreen() {
    val context = LocalContext.current
    val notificationPermission = rememberPermissionState("android.permission.POST_NOTIFICATIONS")

    LaunchedEffect(Unit) {
        if (notificationPermission.status != PermissionStatus.Granted) {
            notificationPermission.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "NotificationScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { NotificationHelper.createBasicNotification(context) }) {
                Text("Show Basic notification")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { NotificationHelper.createClickableNotification(context) }) {
                Text("Show Clickable notification")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { NotificationHelper.createActionableNotification(context) }) {
                Text("Show Actionable notification")
            }
        }
    }
}
