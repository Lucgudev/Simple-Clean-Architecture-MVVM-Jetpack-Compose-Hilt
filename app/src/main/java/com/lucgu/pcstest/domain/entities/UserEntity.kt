package com.lucgu.pcstest.domain.entities

data class UserEntity(
    val id: String,
    val name: String,
    val avatar: String,
    val city: String,
    val country: String,
    val addressNo: String,
    val street: String,
    val zipCode: String,
)
