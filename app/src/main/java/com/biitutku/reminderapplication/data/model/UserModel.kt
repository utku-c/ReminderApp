package com.biitutku.reminderapplication.data.model

import androidx.compose.ui.graphics.painter.Painter

data class UserModel(
    val id: Long = 0,
    val ad: String,
    val soyad: String,
    val kayitTarihi: String,
    val premiumVarMi: Boolean = false,
    val iconId: Painter
)