package com.jyldyzferr.composemoviesapp.domain.models

data class ActorsDomain(
    val peopleCloud: List<PeopleDomain>,
    val crewCloud: List<PeopleDomain>,
    val id: Int
)