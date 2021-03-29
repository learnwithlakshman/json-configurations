package com.careerit.configurations.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter	
@NoArgsConstructor
@AllArgsConstructor
public class Player {

		private String name;
		private String role;
		private double price;
		@Override
		public String toString() {
			return String.format("Player [name=%s, role=%s, price=%s]", name, role, price);
		}
		
		 
}
