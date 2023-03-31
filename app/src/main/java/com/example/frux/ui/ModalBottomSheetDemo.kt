package com.example.frux.ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetDemo(
    bottomSheetState: ModalBottomSheetState,
    sheetContent: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    // Use the ModalBottomSheetLayout composable to wrap the scaffold and the bottom sheet content
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            // This is the content of the bottom sheet, you can put any composable here
            sheetContent.invoke()
        }
    ) {
        body.invoke()
    }
}