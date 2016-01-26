package es.manning.test;

import es.manning.schema.*;

public class Data {
	public static Pedigree makePedigree(int timeSecs) {
		return new Pedigree(timeSecs, Source.SELF, OrigSystem.page_view(new PageViewSystem()));

	}

	public static es.manning.schema.Data makePageview(int userid, String url, int timeSecs) {
		return new es.manning.schema.Data(makePedigree(timeSecs),
				DataUnit.page_view(new PageViewEdge(PersonID.user_id(userid), PageID.url(url), 1)));
	}

	public static es.manning.schema.Data makeEquiv(int user1, int user2) {
		return new es.manning.schema.Data(makePedigree(1000),
				DataUnit.equiv(new EquivEdge(PersonID.user_id(user1), PersonID.user_id(user2))));
	}

	public static es.manning.schema.Data makePersonProperty (int userid, String full_name, GenderType gender, String city, String state, String country){
		PersonPropertyValue personPropertyValue = new PersonPropertyValue ();
		Location location = new Location();
		location.setCity(city);
		location.setState(state);
		location.setCountry(country);
		personPropertyValue.setFull_name(full_name);
		personPropertyValue.setGender(gender);
		personPropertyValue.setLocation(location);
		return new 
			es.manning.schema.Data(
				makePedigree(1000), 
				DataUnit.person_property(
					new PersonProperty(
						PersonID.user_id(userid), 
						personPropertyValue
						))
			);
	}
}
