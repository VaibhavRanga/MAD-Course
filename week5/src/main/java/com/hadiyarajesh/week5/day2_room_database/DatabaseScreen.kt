package com.hadiyarajesh.week5.day2_room_database

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseScreen(
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
//    val users by userViewModel.users.collectAsState()
//    val users by userViewModel.usersFlow.collectAsState()
    val usersAndAadhaarCard by userViewModel.usersAndAadhaarCardFlow.collectAsState()

    LaunchedEffect(Unit) {
//        userViewModel.getAllUsers()
//        userViewModel.getAllUsersFlow()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "DatabaseScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (usersAndAadhaarCard.isEmpty()) {
                NoUsersView(
                    modifier = Modifier.weight(1f)
                )
            } else {
                UsersList(
                    modifier = Modifier.weight(1f),
                    usersAndAadhaarCard = usersAndAadhaarCard,
                    onUserDeleteClick = { user ->
                        userViewModel.deleteUser(user.user) {
                            Toast
                                .makeText(context, "User deleted successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            }

            HorizontalDivider()

            UserActions(
                modifier = Modifier.fillMaxWidth(),
                onUserAddClick = {
                    userViewModel.addUser {
                        Toast
                            .makeText(context, "User added successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                onGetAllUsersClick = {
                    userViewModel.getAllUsers()
                }
            )
        }
    }
}

@Composable
private fun UsersList(
    modifier: Modifier = Modifier,
    usersAndAadhaarCard: List<UserAndAadhaarCard>,
    onUserDeleteClick: (UserAndAadhaarCard) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(usersAndAadhaarCard) { usersAndAadhaarCard ->
            UserItem(
                modifier = Modifier.fillMaxWidth(),
                usersAndAadhaarCard = usersAndAadhaarCard,
                onUserDeleteClick = onUserDeleteClick
            )
        }
    }
}

@Composable
fun NoUsersView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )

            Text(
                text = "No Users found. Click on \"Add User\" button to add a user",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun UserItem(
    modifier: Modifier = Modifier,
    usersAndAadhaarCard: UserAndAadhaarCard,
    onUserDeleteClick: (UserAndAadhaarCard) -> Unit
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "ID: ${usersAndAadhaarCard.user.id}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Name: ${usersAndAadhaarCard.user.name}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Email: ${usersAndAadhaarCard.user.email}",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "City: ${usersAndAadhaarCard.user.address.city}",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "Created at: ${usersAndAadhaarCard.user.createdAt}",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "Aadhaar Number: ${usersAndAadhaarCard.aadhaarCard.number}",
                    style = MaterialTheme.typography.titleSmall
                )
            }

            IconButton(onClick = { onUserDeleteClick(usersAndAadhaarCard) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
private fun UserActions(
    modifier: Modifier = Modifier,
    onUserAddClick: () -> Unit,
    onGetAllUsersClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onUserAddClick) {
            Text("Add User")
        }

        Button(
//            onClick = { onGetAllUsersClick() },
            onClick = onGetAllUsersClick
        ) {
            Text("Get all Users")
        }
    }
}
