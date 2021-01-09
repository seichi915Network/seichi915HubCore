package net.seichi915.seichi915hubcore.util

import net.seichi915.seichi915hubcore.Seichi915HubCore
import org.bukkit.{Bukkit, Location}
import org.bukkit.entity.Entity

object Util {
  def pullEntityToLocation(e: Entity, loc: Location): Unit = {
    val entityLoc = e.getLocation
    entityLoc.setY(entityLoc.getY + 0.5)
    e.teleport(entityLoc)
    val g = -0.08
    val d = loc.distance(entityLoc)
    val t = d
    val v_x = (1.0 + 0.07 * t) * (loc.getX - entityLoc.getX) / t
    val v_y = (1.0 + 0.03 * t) * (loc.getY - entityLoc.getY) / t - 0.5 * g * t
    val v_z = (1.0 + 0.07 * t) * (loc.getZ - entityLoc.getZ) / t
    val v = e.getVelocity
    v.setX(v_x)
    v.setY(v_y)
    v.setZ(v_z)
    e.setVelocity(v)
    if (Seichi915HubCore.noFallEntities.contains(e.getEntityId))
      Bukkit.getServer.getScheduler
        .cancelTask(Seichi915HubCore.noFallEntities(e.getEntityId))
    val taskId = Seichi915HubCore.instance.getServer.getScheduler
      .scheduleSyncDelayedTask(
        Seichi915HubCore.instance,
        new Runnable() {
          override def run(): Unit = {
            if (Seichi915HubCore.noFallEntities.contains(e.getEntityId))
              Seichi915HubCore.noFallEntities.remove(e.getEntityId)
          }
        },
        100
      )
    Seichi915HubCore.noFallEntities += e.getEntityId -> taskId
  }
}
