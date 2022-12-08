package org.chiachat.app.ui.theme

import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.std.resourcesVfs

enum class CchIcons(val file: VfsFile) {
  DARK_MODE(resourcesVfs["icons/material/dark_mode.png"]),
  LIGHT_MODE(resourcesVfs["icons/material/light_mode.png"])
}
