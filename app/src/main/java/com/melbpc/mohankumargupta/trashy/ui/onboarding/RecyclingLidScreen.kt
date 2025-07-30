package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.ui.components.BinColor

@Composable
fun RecyclingLidScreen(
    modifier: Modifier = Modifier,
    onRecyclingLidColorChosen: () -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel(
        LocalContext.current as ViewModelStoreOwner
    )
) {
    BinColor(modifier = modifier, binType = BinType.RECYCLING, onClick = { color ->
        viewModel.handle(ScheduleIntent.RecyclingLidColor(color))
        onRecyclingLidColorChosen()
    })
}
