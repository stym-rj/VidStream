package com.example.vidstream.networkUtils

import kotlinx.serialization.Serializable

@Serializable
data class ItemsData(
    val video: String,
    val thumbnail: String,
    val title: String,
    val channel: String,
    val description: String,
    val likes: Int
)


/**

CREATE TABLE items (
id SERIAL PRIMARY KEY,
video TEXT NOT NULL,
thumbnail TEXT NOT NULL,
title VARCHAR(255) NOT NULL,
channel VARCHAR(255) NOT NULL,
description TEXT,
likes INT DEFAULT 0
);

 * **/