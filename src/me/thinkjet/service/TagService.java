package me.thinkjet.service;

import java.util.List;

import me.thinkjet.model.ActivityTags;
import me.thinkjet.model.Tags;

public class TagService {
	public List<Tags> getBlogTags(long blog_id) {
		return Tags.dao
				.findByCache(
						"tags",
						"tag_blog_" + blog_id,
						"select * from tags where id in (select tags_id from blog_tags where blog_id=?)",
						blog_id);
	}

	public void addActivityTag(long activity_id, String tag_name) {
		ActivityTags.dao.set("activity_id", activity_id)
				.set("tag_id", getTagId(tag_name)).save();
	}

	public void addBlogTags(long blog_id, String[] tag_names) {

	}

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
