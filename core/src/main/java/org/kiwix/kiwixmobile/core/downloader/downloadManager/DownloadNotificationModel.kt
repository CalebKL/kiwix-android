/*
 * Kiwix Android
 * Copyright (c) 2024 Kiwix <android.kiwix.org>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.kiwix.kiwixmobile.core.downloader.downloadManager

data class DownloadNotificationModel(
  val downloadId: Int,
  val status: Status = Status.NONE,
  val progress: Int,
  val etaInMilliSeconds: Long,
  val title: String,
  val description: String?,
  val filePath: String?,
  val error: String
) {
  val isPaused get() = status == Status.PAUSED
  val isCompleted get() = status == Status.COMPLETED
  val isFailed get() = status == Status.FAILED
  val isQueued get() = status == Status.QUEUED
  val isDownloading get() = status == Status.DOWNLOADING
  val isCancelled get() = status == Status.CANCELLED
  val isOnGoingNotification: Boolean
    get() {
      return when (status) {
        Status.QUEUED,
        Status.DOWNLOADING -> true

        else -> false
      }
    }
}
