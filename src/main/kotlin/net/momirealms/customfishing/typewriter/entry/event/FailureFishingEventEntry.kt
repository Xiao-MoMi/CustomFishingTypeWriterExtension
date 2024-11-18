package net.momirealms.customfishing.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.*
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customfishing.api.event.FishingResultEvent
import java.util.*

@Entry("customfishing_failed_fishing_event", "CustomFishing Failure Fishing Event", Colors.RED, "material-symbols:bigtop-updates")
class FailureFishingEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
) : EventEntry {
}

@EntryListener(FailureFishingEventEntry::class)
fun onFishingResultEvent(event: FishingResultEvent, query: Query<FailureFishingEventEntry>) {
    if (event.result != FishingResultEvent.Result.FAILURE) return
    val entries = query.find()
    entries triggerAllFor event.player
}