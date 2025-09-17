package com.chelo.todoadj.view.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.chelo.todoadj.model.entities.Task
import com.chelo.todoadj.ui.theme.Black
import com.chelo.todoadj.ui.theme.Grey
import com.chelo.todoadj.ui.theme.LightGrey
import com.chelo.todoadj.ui.theme.White
import com.chelo.todoadj.view.home.TaskViewModel


@Composable
fun DialogAddTask(
    viewmodel: TaskViewModel = hiltViewModel(),
    taskUpdate: Task?,
    onDismiss: () -> Unit = {},
) {
    val task by viewmodel.state.collectAsState()

    taskUpdate?.let {
        viewmodel.onTitleChanged(it.title)
        viewmodel.onDescriptionChanged(it.description)
    }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Nueva Tarea",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    "Titulo",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )
                OutlinedTextField(
                    value = task.title,
                    onValueChange = { viewmodel.onTitleChanged(it) },
                    singleLine = true,
                    placeholder = { Text("Que tienes que hacer?") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    ))
                Spacer(Modifier.height(4.dp))
                Text(
                    "Descripcion",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )
                OutlinedTextField(
                    value = task.description,
                    onValueChange = { viewmodel.onDescriptionChanged(it) },
                    minLines = 3,
                    label = { Text("Detalles adicionales ...(opcional)") },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Button(
                        onClick = { if (taskUpdate != null) viewmodel.updateTask(taskUpdate) else viewmodel.addTask(); onDismiss() },
                        modifier = Modifier.weight(2f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightGrey,
                            contentColor = White
                        )
                    ) { Text( if (taskUpdate != null )"Actualizar Tarea" else "Añadir Tarea", fontWeight = FontWeight.Bold) }
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier.weight(1.3f),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Grey),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White,
                            contentColor = Black
                        )
                    ) { Text("Cancelar", fontWeight = FontWeight.Bold) }

                }
            }

        }
    }
}