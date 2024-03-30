package com.kenkoro.taurus.client.feature.sewing.presentation.screen.dashboard.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kenkoro.taurus.client.R
import com.kenkoro.taurus.client.feature.sewing.presentation.screen.dashboard.util.TabItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBarHost(
  modifier: Modifier = Modifier,
  content: @Composable (index: Int) -> Unit,
) {
  val tabItems =
    listOf(
      TabItem(
        title = stringResource(id = R.string.bottom_bar_host_orders),
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
      ),
      TabItem(
        title = stringResource(id = R.string.bottom_bar_host_profile),
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
      )
    )

  val pagerState =
    rememberPagerState {
      tabItems.size
    }

  BottomBarContent(
    modifier =
    modifier
      .statusBarsPadding()
      .navigationBarsPadding(),
    tabItems = tabItems,
    pagerState = pagerState,
  ) { index ->
    content(index)
  }
}