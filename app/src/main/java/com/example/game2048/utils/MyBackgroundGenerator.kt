package com.example.game2048.utils

import com.example.game2048.R


class MyBackgroundGenerator {
    private val map = hashMapOf(
        0 to R.drawable.bg_item_0,
        2 to R.drawable.bg_item_2,
        4 to R.drawable.bg_item_4,
        8 to R.drawable.bg_item_8,
        16 to R.drawable.bg_item_16,
        32 to R.drawable.bg_item_32,
        64 to R.drawable.bg_item_64,
        128 to R.drawable.bg_item_128,
        256 to R.drawable.bg_item_256,
        512 to R.drawable.bg_item_512,
        1024 to R.drawable.bg_item_1024,
    )

    fun backgroundByAmount(amount: Int) = map.getOrDefault(amount, R.drawable.bg_item_1024)
}