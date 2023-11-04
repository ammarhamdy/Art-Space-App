package com.ammar.artspace

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtWork(
    @DrawableRes val resourceId: Int,
    @StringRes val titleId: Int,
    @StringRes val artistId: Int
)