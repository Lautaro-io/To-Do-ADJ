package com.chelo.todoadj.view.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.chelo.todoadj.model.entities.Task
import com.chelo.todoadj.utils.NoteIcons

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskItem(
    task: Task,
    isChecked: Boolean,
    onCheckValueChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
) {
    var style = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        border = BorderStroke(1.dp, LightGray),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(
                        MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                    )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TaskHeader(
                        task = task,
                        onCheckClick = { onCheckValueChange(it) },
                        style = style,
                        onDeleteClick = { onDeleteClick() },
                        onUpdateClick = { onUpdateClick() })
                    TaskContent(task = task, style)
                    Spacer(Modifier.height(8.dp))


                }
            }

        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskHeader(
    task: Task,
    style: TextDecoration,
    onCheckClick: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onCheckClick(it) },
        )
        Text(
            text = task.title,
            style = TextStyle(textDecoration = style)
        )
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "MoreVert",
                tint = MaterialTheme.colorScheme.onSurface,
            )
            if (expanded) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("Eliminar") },
                        onClick = { onDeleteClick() ; expanded = false},
                        leadingIcon = {
                            Icon(
                                NoteIcons.delete(),
                                contentDescription = "Delete Icon",
                                tint = Red
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Editar") },
                        onClick = { onUpdateClick() },
                        leadingIcon = {
                            Icon(
                                NoteIcons.updateIcon(),
                                contentDescription = "Edit Icon",
                            )
                        }
                    )
                }
            }

        }

    }
}

@Composable
fun TaskContent(task: Task, style: TextDecoration) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(task.description, textDecoration = style)
    }
}

