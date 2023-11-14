package ua.hyperbeard.idlepocketplanel.domain

data class Element(
    val identity: Int,
    val frontImage: Int,
    val rearImage: Int,
    val description: String = "test",
    val menu: Menu
)