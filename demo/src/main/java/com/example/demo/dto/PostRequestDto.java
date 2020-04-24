package com.example.demo.dto;

import java.util.Set;

public class PostRequestDto {

	private String title;

	private String description;

	private Set<TagsDetails> tags;

	public PostRequestDto() {
		super();
	}

	public PostRequestDto(String title, String description, Set<TagsDetails> tags) {
		super();
		this.title = title;
		this.description = description;
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TagsDetails> getTags() {
		return tags;
	}

	public void setTags(Set<TagsDetails> tags) {
		this.tags = tags;
	}

	public static class TagsDetails {
		private Long id;

		private String name;

		public TagsDetails() {
			super();
		}

		public TagsDetails(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
