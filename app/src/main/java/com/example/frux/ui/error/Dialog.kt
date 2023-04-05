package com.example.frux.ui.error

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.frux.R
import com.example.frux.presentation.delegate.error.ShowErrorDelegate

/**
 * Dialog
 *
 * @param mainViewModel
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun Dialog(mainViewModel: ShowErrorDelegate, message: String) {
    val openDialog = mainViewModel.getErrorDialogState().observeAsState()
    if (openDialog.value == true) {
        AlertDialog(
            onDismissRequest = {
                mainViewModel.hideDialog()
            },
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = message,
                    color = MaterialTheme.colors.primary
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { false.also { mainViewModel.hideDialog() } }
                    ) {
                        Text(stringResource(R.string.dismiss))
                    }
                }
            }
        )
    }
}