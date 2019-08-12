package com.github.szymonrudnicki.songapp.app.ui.songslist

enum class SourceType(val tag: String) {
    Local("Local"), Remote("Remote"), LocalAndRemote("Local and Remote");

    companion object {
        fun getByTag(tag: String) = when (tag) {
            Local.tag -> Local
            Remote.tag -> Remote
            LocalAndRemote.tag -> LocalAndRemote
            else -> throw IllegalStateException("No source found for this tag: $tag")
        }
    }
}