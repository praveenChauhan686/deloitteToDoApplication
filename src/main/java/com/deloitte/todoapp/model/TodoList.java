package com.deloitte.todoapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table
public class TodoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String header;

	@Column
	private Date date;

	@Column
	private String description;

	@Column
	private boolean checkBox;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the checkBox
	 */
	public boolean isCheckBox() {
		return checkBox;
	}

	/**
	 * @param checkBox the checkBox to set
	 */
	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public TodoList(String header, boolean checkBox, User user, String description) {
		this.header = header;
		this.checkBox = checkBox;
		this.user = user;
		this.description = description;
	}

	public TodoList() {
	}

	@Override
	public String toString() {
		return "TodoList{" + "id=" + id + ", header='" + header + '\'' + ", date=" + date + ", description='"
				+ description + '\'' + ", checkBox=" + checkBox + ", user=" + user + '}';
	}

}