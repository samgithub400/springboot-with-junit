package com.ios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts {

	private long id;
	private long user_id;
	private String title;
	private String body;

}
