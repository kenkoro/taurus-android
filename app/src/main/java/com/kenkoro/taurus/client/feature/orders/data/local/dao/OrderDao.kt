package com.kenkoro.taurus.client.feature.orders.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kenkoro.taurus.client.feature.orders.data.local.OrderEntity

@Dao
interface OrderDao {
  @Upsert
  suspend fun upsert(orderEntity: OrderEntity)

  @Upsert
  suspend fun upsertAll(orderEntities: List<OrderEntity>)

  @Delete
  suspend fun delete(orderEntity: OrderEntity)

  @Query("delete from order_entities")
  suspend fun deleteAll()

  @Query("select * from order_entities")
  fun pagingSource(): PagingSource<Int, OrderEntity>
}