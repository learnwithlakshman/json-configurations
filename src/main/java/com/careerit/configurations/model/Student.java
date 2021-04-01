package com.careerit.configurations.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private String name;
	private String batch;
	private String completed;
	private String placed;
	private String qualification;
	private float score;
	
}
