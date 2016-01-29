package testing;

import io.match.algorithm.Compare;
import io.match.datastructure.Student;
import io.match.datastructure.attributes.Attribute.Interest;
import io.match.datastructure.attributes.GeneralAttribute;
import io.match.datastructure.attributes.MultipleAttribute;

public class TestComparing {
	
	public static void main(String[] args) {
		
		TestComparing test = new TestComparing();
		try {
			System.out.println(test.testCompare());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private double testCompare() throws Exception {
		
		GeneralAttribute name1 = new GeneralAttribute("name");
		name1.setData("Minh");
		GeneralAttribute name2 = new GeneralAttribute("name");
		name2.setData("Quang");
		
		GeneralAttribute email1 = new GeneralAttribute("email");
		email1.setData("minh.hoang@tcu.edu");
		GeneralAttribute email2 = new GeneralAttribute("email");
		email2.setData("quang.nguyen@tcu.edu");
		
		MultipleAttribute major1 = new MultipleAttribute("major");
		major1.setChoice("cs");
		major1.setExpecting(true);
		major1.setInterst(Interest.VERY_IMPORTANT);
		
		MultipleAttribute major2 = new MultipleAttribute("major");
		major2.setChoice("it");
		major2.setExpecting(true);
		major2.setInterst(Interest.NOT_IMPORTANT);
		
		MultipleAttribute interest1 = new MultipleAttribute("interest");
		interest1.setChoice("very serious");
		interest1.setExpecting(true);
		interest1.setInterst(Interest.SOMEWHAT_IMPORTANT);
		
		MultipleAttribute interest2 = new MultipleAttribute("interest");
		interest2.setChoice("very serious");
		interest2.setExpecting(true);
		interest2.setInterst(Interest.VERY_IMPORTANT);
		
		Student minh = new Student();
		minh.addAttribute(name1);
		minh.addAttribute(email1);
		minh.addAttribute(major1);
		minh.addAttribute(interest1);
		
		Student quang = new Student();
		quang.addAttribute(name2);
		quang.addAttribute(email2);
		quang.addAttribute(major2);
		quang.addAttribute(interest2);
		
		
		return new Compare().getMatch(minh, quang);
	}

}
