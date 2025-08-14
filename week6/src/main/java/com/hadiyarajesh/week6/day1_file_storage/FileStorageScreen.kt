package com.hadiyarajesh.week6.day1_file_storage

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileStorageScreen() {
    val context = LocalContext.current
    val cacheDirectoryFileName = "cache_directory_file.txt"
    val fileDirectoryFileName = "file_directory_file.txt"

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "FileStorageScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    FileManager.writeToFile(
                        context = context,
                        directoryType = DirectoryType.Cache,
                        storageType = StorageType.Internal,
                        fileName = cacheDirectoryFileName,
                        content = "This is content of internal cache directory file",
                        onSuccess = {
                            Toast.makeText(
                                context,
                                "Wrote to internal cache directory",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            ) {
                Text("Write to Internal Cache directory")
            }

            Button(
                onClick = {
                    FileManager.writeToFile(
                        context = context,
                        directoryType = DirectoryType.Files,
                        storageType = StorageType.Internal,
                        fileName = fileDirectoryFileName,
                        content = "This is content of internal files directory file",
                        onSuccess = {
                            Toast.makeText(
                                context,
                                "Wrote to internal files directory",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            ) {
                Text("Write to Internal Files directory")
            }

            Button(
                onClick = {
                    FileManager.writeToFile(
                        context = context,
                        directoryType = DirectoryType.Cache,
                        storageType = StorageType.External,
                        fileName = cacheDirectoryFileName,
                        content = "This is content of external cache directory file",
                        onSuccess = {
                            Toast.makeText(
                                context,
                                "Wrote to external cache directory",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            ) {
                Text("Write to External Cache directory")
            }

            Button(
                onClick = {
                    val fileContent = FileManager.readFromFile(
                        context = context,
                        directoryType = DirectoryType.Cache,
                        storageType = StorageType.Internal,
                        fileName = cacheDirectoryFileName
                    )

                    Toast.makeText(
                        context,
                        fileContent,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("Read from Internal Cache directory")
            }
        }
    }
}
