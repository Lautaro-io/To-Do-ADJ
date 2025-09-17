package com.chelo.todoadj.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.chelo.todoadj.R

object NoteIcons {

    @Composable
    fun delete() = painterResource(R.drawable.ic_delete)

    @Composable
    fun updateIcon ()= painterResource(R.drawable.ic_update)
}