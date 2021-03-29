package com.careerit.configurations.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {

		private String coach;
		private String home;
		private String team;
		private String label;
		private String city;
		private List<Player> players;
}
