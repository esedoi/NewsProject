package com.example.newsproject.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.data.model.Article
import com.example.newsproject.databinding.ItemArticleBinding


class NewsAdapter :
    ListAdapter<Article, NewsAdapter.ArticleViewHolder>(
        ArticleCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ArticleViewHolder(private var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {

            setTitle(item)
            setCoverImg(item)
        }

        private fun setTitle(item: Article) {

            binding.tvTitle.text = item.title
        }

        private fun setCoverImg(item: Article) {
            Glide.with(itemView)
                .load(item.urlToImage)
                .override(binding.ivArticle.width, binding.ivArticle.height)
                .into(binding.ivArticle)
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val view =
                    ItemArticleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return ArticleViewHolder(view)
            }
        }
    }
}

class ArticleCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem === newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

