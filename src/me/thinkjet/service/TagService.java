package me.thinkjet.service;

import java.util.List;

import me.thinkjet.model.ActivityTags;
import me.thinkjet.model.Tags;

public class TagService {
	/**
	 * 根据blog的id获取对应的标签
	 * @param blog_id
	 * @return
	 */
	public List<Tags> getBlogTags(long blog_id) {
		return Tags.dao
				.findByCache(
						"tags",
						"tag_blog_" + blog_id,
						"select * from tags where id in (select tags_id from blog_tags where blog_id=?)",
						blog_id);
	}

	/**
	 * 向指定id的activity添加标签，如果标签库中存在则直接添加链接，否则新建新的标签，然后生成链接
	 * @param activity_id
	 * @param tag_name
	 */
	public void addActivityTag(long activity_id, String tag_name) {
		ActivityTags.dao.set("activity_id", activity_id)
				.set("tag_id", getTagId(tag_name)).save();
	}

	/**
	 * 向指定id的activity添加多个标签，如果标签库中存在则直接添加链接，否则新建新的标签，然后生成链接
	 * @param activity_id
	 * @param tag_names
	 */
	public void addActivityTags(long activity_id, String[] tag_names) {

	}

	/**
	 * 获取指定的id的activity的标签集
	 * @param activity_id
	 * @return
	 */
	public List<Tags> getActivityTags(long activity_id) {
		return Tags.dao
				.findByCache(
						"tags",
						"tag_activity_" + activity_id,
						"select * from tags where id in (select tags_id from activity_tags where activity_id=?)",
						activity_id);
	}

	private long getTagId(String name) {
		Tags tag = Tags.dao.findFirst("select * from tags where name=?", name);
		if (tag == null) {
			tag = new Tags();
			tag.set("name", name).save();
		}
		return tag.getLong("id");
	}
}
