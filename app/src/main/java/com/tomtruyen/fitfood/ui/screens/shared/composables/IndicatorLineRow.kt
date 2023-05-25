package com.tomtruyen.fitfood.ui.screens.shared.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tomtruyen.fitfood.Dimens
import com.tomtruyen.fitfood.ui.theme.DarkGunMetal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndicatorLineRow(
    pagerState: PagerState,
    pageIndex: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(Dimens.PaddingNormal),
    ) {
        repeat(pageIndex) { pageIndex ->
            val isActive = pageIndex <= pagerState.currentPage

            Box(
                modifier = Modifier
                    .padding(horizontal = Dimens.PaddingTiny)
                    .weight(1f)
                    .height(6.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(if (isActive) MaterialTheme.colorScheme.primary else DarkGunMetal)
            )
        }
    }
}