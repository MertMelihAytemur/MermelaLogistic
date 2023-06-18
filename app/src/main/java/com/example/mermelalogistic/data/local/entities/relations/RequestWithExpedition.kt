package com.example.mermelalogistic.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mermelalogistic.data.local.entities.Expedition
import com.example.mermelalogistic.data.local.entities.Request

/**
 *Created by Mert Melih Aytemur on 6/15/2023.
 */
data class RequestWithExpedition(
    @Embedded val request: Request,
    @Relation(
        parentColumn = "requestId",
        entityColumn = "requestId"
    )
    val expedition: Expedition?
)