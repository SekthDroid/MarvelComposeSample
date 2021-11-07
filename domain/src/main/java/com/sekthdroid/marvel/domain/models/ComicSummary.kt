package com.sekthdroid.marvel.domain.models

// This ComicSummary, StorySummary and SerieSummary are similar right now, maybe we can do
// something to encapsulate it in a single one and avoid 3 classes
data class ComicSummary(
    val resourceURI: String,
    val name: String
) {
    fun resourceId(): String {
        return resourceURI.split("/").lastOrNull().orEmpty()
    }
}
