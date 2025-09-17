package com.chelo.todoadj.view.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chelo.todoadj.R
import com.chelo.todoadj.ui.theme.Black
import com.chelo.todoadj.ui.theme.Grey
import com.chelo.todoadj.ui.theme.LightGrey
import com.chelo.todoadj.view.home.components.DialogAddTask
import com.chelo.todoadj.view.home.components.EmptyViewScreen
import com.chelo.todoadj.view.home.components.TaskItem
import com.chelo.todoadj.view.home.components.ToggleButtons


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewmodel: HomeViewModel = hiltViewModel()) {
    val homeState by viewmodel.state.collectAsState()
    var listState = rememberLazyListState()

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Grey,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_icon_app),
                        contentDescription = "Icon App",
                        modifier = Modifier
                            .padding(4.dp),
                        tint = Black
                    )

                }
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text("To Do App ADJ")
                    Text("Organiza tu dia", color = LightGrey)
                }

            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewmodel.openCreateDialog()},
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(74.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ToggleButtons(
                selectedTab = homeState.selectedTab,
                onTabSelected = { viewmodel.changeTab(it) })

            val tasksToShow = when (homeState.selectedTab) {
                "Pendientes" -> homeState.pendingTasks
                "Completadas" -> homeState.completedTasks
                else -> emptyList()
            }
            if (tasksToShow.isEmpty()) EmptyViewScreen()
            LazyColumn(state = listState, modifier = Modifier.padding(vertical = 32.dp)) {
                itemsIndexed(tasksToShow) { index, item ->
                    TaskItem(
                        task = item,
                        isChecked = item.isCompleted,
                        onCheckValueChange = { viewmodel.updateTask(item.copy(isCompleted = it)) },
                        onDeleteClick = { viewmodel.changeDialogDelete(); viewmodel.selectTask(item) },
                        onUpdateClick = { viewmodel.selectTask(item); viewmodel.changeDialogState() }
                    )
                }
            }
            if (homeState.showDialogDelete) {
                AlertDialog(
                    onDismissRequest = { viewmodel.changeDialogDelete() },
                    title = { Text("Desea eliminar esta tarea?") },
                    confirmButton = {
                        TextButton(onClick = { viewmodel.deleteTask(homeState.selectedTask!!); viewmodel.changeDialogDelete() }) {
                            Text(
                                "Eliminar",
                                color = Red
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { viewmodel.changeDialogDelete() }) {
                            Text(
                                "Cancelar",
                                color = Blue
                            )
                        }
                    },
                )
            }

            if (homeState.showDialog) {
                DialogAddTask(taskUpdate = homeState.selectedTask) {
                    viewmodel.changeDialogState()
                }
            }
        }
    }
}


