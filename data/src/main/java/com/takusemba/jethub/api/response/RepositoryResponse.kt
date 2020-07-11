package com.takusemba.jethub.api.response

import com.takusemba.jethub.model.DateFormatters
import com.takusemba.jethub.model.Repo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Repository response data.
 */
@Serializable
class RepositoryResponse {

  @SerialName("id")
  var id: Int? = null

  @SerialName("node_id")
  var nodeId: String? = null

  @SerialName("name")
  var name: String? = null

  @SerialName("full_name")
  var fullName: String? = null

  @SerialName("private")
  var private: Boolean? = null

  @SerialName("owner")
  var owner: OwnerResponse? = null

  @SerialName("html_url")
  var htmlUrl: String? = null

  @SerialName("description")
  var description: String? = null

  @SerialName("fork")
  var fork: Boolean? = null

  @SerialName("url")
  var url: String? = null

  @SerialName("forks_url")
  var forksUrl: String? = null

  @SerialName("keys_url")
  var keysUrl: String? = null

  @SerialName("collaborators_url")
  var collaboratorsUrl: String? = null

  @SerialName("teams_url")
  var teamsUrl: String? = null

  @SerialName("hooks_url")
  var hooksUrl: String? = null

  @SerialName("issue_events_url")
  var issueEventsUrl: String? = null

  @SerialName("events_url")
  var eventsUrl: String? = null

  @SerialName("assignees_url")
  var assigneesUrl: String? = null

  @SerialName("branches_url")
  var branchesUrl: String? = null

  @SerialName("tags_url")
  var tagsUrl: String? = null

  @SerialName("blobs_url")
  var blobsUrl: String? = null

  @SerialName("git_tags_url")
  var gitTagsUrl: String? = null

  @SerialName("git_refs_url")
  var gitRefsUrl: String? = null

  @SerialName("trees_url")
  var treesUrl: String? = null

  @SerialName("statuses_url")
  var statusesUrl: String? = null

  @SerialName("languages_url")
  var languagesUrl: String? = null

  @SerialName("stargazers_url")
  var stargazersUrl: String? = null

  @SerialName("contributors_url")
  var contributorsUrl: String? = null

  @SerialName("subscribers_url")
  var subscribersUrl: String? = null

  @SerialName("subscription_url")
  var subscriptionUrl: String? = null

  @SerialName("commits_url")
  var commitsUrl: String? = null

  @SerialName("git_commits_url")
  var git_commitsUrl: String? = null

  @SerialName("comments_url")
  var commentsUrl: String? = null

  @SerialName("issue_comment_url")
  var issueCommentUrl: String? = null

  @SerialName("contents_url")
  var contentsUrl: String? = null

  @SerialName("compare_url")
  var compareUrl: String? = null

  @SerialName("merges_url")
  var mergesUrl: String? = null

  @SerialName("archive_url")
  var archiveUrl: String? = null

  @SerialName("downloads_url")
  var downloadsUrl: String? = null

  @SerialName("issues_url")
  var issuesUrl: String? = null

  @SerialName("pulls_url")
  var pullsUrl: String? = null

  @SerialName("milestones_url")
  var milestonesUrl: String? = null

  @SerialName("notifications_url")
  var notificationsUrl: String? = null

  @SerialName("labels_url")
  var labelsUrl: String? = null

  @SerialName("releases_url")
  var releasesUrl: String? = null

  @SerialName("deployments_url")
  var deploymentsUrl: String? = null

  @SerialName("created_at")
  var createdAt: String? = null

  @SerialName("updated_at")
  var updatedAt: String? = null

  @SerialName("pushed_at")
  var pushedAt: String? = null

  @SerialName("git_url")
  var gitUrl: String? = null

  @SerialName("ssh_url")
  var sshUrl: String? = null

  @SerialName("clone_url")
  var cloneUrl: String? = null

  @SerialName("svn_url")
  var svnUrl: String? = null

  @SerialName("homepage")
  var homepage: String? = null

  @SerialName("size")
  var size: Int? = null

  @SerialName("stargazers_count")
  var stargazersCount: Int? = null

  @SerialName("watchers_count")
  var watchersCount: Int? = null

  @SerialName("language")
  var language: String? = null

  @SerialName("has_issues")
  var hasIssues: Boolean? = null

  @SerialName("has_projects")
  var hasProjects: Boolean? = null

  @SerialName("has_downloads")
  var hasDownloads: Boolean? = null

  @SerialName("has_wiki")
  var hasWiki: Boolean? = null

  @SerialName("has_pages")
  var hasPages: Boolean? = null

  @SerialName("forks_count")
  var forksCount: Int? = null

  @SerialName("mirror_url")
  var mirrorUrl: String? = null

  @SerialName("archived")
  var archived: String? = null

  @SerialName("open_issues_count")
  var openIssuesCount: Int? = null

  @SerialName("license")
  var license: LicenseResponse? = null

  @SerialName("forks")
  var forks: Int? = null

  @SerialName("open_issues")
  var openIssues: Int? = null

  @SerialName("watchers")
  var watchers: Int? = null

  @SerialName("default_branch")
  var defaultBranch: String? = null

  @SerialName("score")
  var score: Double? = null

  fun toModel() = Repo(
    id = id ?: throw IllegalArgumentException("id not found"),
    owner = owner?.toModel() ?: throw IllegalArgumentException("owner not found"),
    name = name ?: "",
    description = description ?: "",
    createdAt = LocalDateTime.from(DateFormatters.ofApiResult().parse(createdAt)),
    updatedAt = LocalDateTime.from(DateFormatters.ofApiResult().parse(updatedAt)),
    starsCount = stargazersCount ?: 0,
    watchersCount = watchersCount ?: 0,
    forksCount = forksCount ?: 0,
    language = language ?: ""
  )
}
