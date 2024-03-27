package com.kenkoro.taurus.client.feature.sewing.presentation.screen.order

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kenkoro.taurus.client.core.connectivity.Status
import com.kenkoro.taurus.client.feature.sewing.presentation.screen.order.components.OrderContent
import com.kenkoro.taurus.client.feature.sewing.presentation.shared.components.ErrorSnackbar
import com.kenkoro.taurus.client.ui.theme.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderScreen(
  orderViewModel: OrderViewModel = hiltViewModel(),
  networkStatus: Status
) {
  val snackbarHostState = remember { SnackbarHostState() }
  val orders = orderViewModel.orderPagingFlow.collectAsLazyPagingItems()

  AppTheme {
    Scaffold(
      snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) {
          ErrorSnackbar(
            modifier = Modifier.padding(bottom = 20.dp),
            snackbarData = it,
          )
        }
      },
    ) {
      Surface(
        modifier =
        Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.background),
      ) {
        OrderContent(orders)
      }
    }
  }
}