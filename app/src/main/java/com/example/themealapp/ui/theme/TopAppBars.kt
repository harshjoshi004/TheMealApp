package com.example.themealapp.ui.theme

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCenteredTopBar(header:@Composable ()->Unit, scrollBehavior: TopAppBarScrollBehavior, navIcon:@Composable ()->Unit, actionIcon:@Composable ()->Unit){
   TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            header()
        },
        navigationIcon = {
            navIcon()
        },
        actions = {
            actionIcon()
        },
        scrollBehavior = scrollBehavior
    )
}