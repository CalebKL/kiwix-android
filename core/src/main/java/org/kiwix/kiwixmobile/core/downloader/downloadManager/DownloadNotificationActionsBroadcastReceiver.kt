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

import android.content.Context
import android.content.Intent
import org.kiwix.kiwixmobile.core.base.BaseBroadcastReceiver
import org.kiwix.kiwixmobile.core.downloader.downloadManager.DownloadNotificationManager.Companion.ACTION_CANCEL
import org.kiwix.kiwixmobile.core.downloader.downloadManager.DownloadNotificationManager.Companion.ACTION_PAUSE
import org.kiwix.kiwixmobile.core.downloader.downloadManager.DownloadNotificationManager.Companion.ACTION_RESUME
import org.kiwix.kiwixmobile.core.downloader.downloadManager.DownloadNotificationManager.Companion.EXTRA_DOWNLOAD_ID
import org.kiwix.kiwixmobile.core.downloader.downloadManager.DownloadNotificationManager.Companion.NOTIFICATION_ACTION
import javax.inject.Inject

const val DOWNLOAD_NOTIFICATION_ACTION = "org.kiwix.kiwixmobile.download_notification_action"

class DownloadNotificationActionsBroadcastReceiver @Inject constructor(
  private val downloadManagerMonitor: DownloadManagerMonitor
) : BaseBroadcastReceiver() {

  override val action: String = DOWNLOAD_NOTIFICATION_ACTION
  override fun onIntentWithActionReceived(context: Context, intent: Intent) {
    val downloadId = intent.getIntExtra(EXTRA_DOWNLOAD_ID, -1)
    val notificationAction = intent.getStringExtra(NOTIFICATION_ACTION)
    if (downloadId != -1) {
      when (notificationAction) {
        ACTION_PAUSE -> downloadManagerMonitor.pauseDownload(downloadId.toLong())
        ACTION_RESUME -> downloadManagerMonitor.resumeDownload(downloadId.toLong())
        ACTION_CANCEL -> downloadManagerMonitor.cancelDownload(downloadId.toLong())
      }
    }
  }
}
