package dev.soul.effectivemobtest.presentation.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.soul.domain.model.rooms.RoomModel
import dev.soul.effectivemobtest.R
import dev.soul.effectivemobtest.databinding.ItemHotelBinding
import dev.soul.effectivemobtest.presentation.main.adapter.PeculiaritiesAdapter
import dev.soul.effectivemobtest.presentation.main.adapter.ViewPagerAdapter
import dev.soul.effectivemobtest.utils.autoScroll

class AdapterHotels(private val context: Context) :
    ListAdapter<RoomModel, AdapterHotels.ViewHolder>(TaskDiffCallBack())
{
    class TaskDiffCallBack : DiffUtil.ItemCallback<RoomModel>() {
        override fun areItemsTheSame(
            oldItem: RoomModel,
            newItem: RoomModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RoomModel,
            newItem: RoomModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private var itemLikeClickListener: (() -> Unit)? =
        null

    fun setItemLikeClickListener(f: () -> Unit) {
        itemLikeClickListener = f
    }

    inner class ViewHolder(private val binding: ItemHotelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapterImage by lazy {
            ViewPagerAdapter()
        }

        private val adapterPeculiarities by lazy {
            PeculiaritiesAdapter()
        }

        fun bind(data: RoomModel) {
            binding.viewPager.adapter = adapterImage
            binding.list.adapter = adapterPeculiarities

            binding.dotsIndicator.attachTo(binding.viewPager)
            binding.viewPager.autoScroll(4000)

            binding.name.text = data.name
            binding.money.text = context.getString(R.string.money, data.price.toString())
            binding.text.text = data.price_per

            binding.next.setOnClickListener {
                itemLikeClickListener?.invoke()
            }

            adapterPeculiarities.setList(data.peculiarities)
            adapterImage.submitList(data.image_urls)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}