package com.example.samplegithubrepository.data.mapper

import com.example.samplegithubrepository.data.model.repo_list_dto.OwnerDTO
import com.example.samplegithubrepository.data.model.repo_list_dto.RepoListDTOItem
import com.example.samplegithubrepository.domain.model.repo_list.Owner
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem

fun List<RepoListDTOItem>.toDomain(): List<RepoListItem> {
    return map {
        RepoListItem(
            description = it.description ?: "",
            fork = it.fork ?: false,
            full_name = it.fullName ?: "",
            id = it.id ?: 0,
            name = it.name ?: "",
            owner = it.ownerDTO?.toDomain() ?: Owner(0,"","","",""),
            url = it.url ?: ""
        )

    }
}

fun OwnerDTO.toDomain(): Owner {
    return Owner(
        id = id?:0, login = login?:"", starred_url = starredUrl?:"",type=type?:"",url=url?:""

    )
}