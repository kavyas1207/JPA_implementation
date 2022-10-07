package com.sample.project;
import com.sample.project.service.ServiceBuyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {
	//private static Buyer buyer;
	public  ProjectApplication(ServiceBuyer servicebuyer)
	{
		
	}
	/*public ProjectApplication(ServiceBuyer servicebuyer)
	{
		this.servicebuyer=servicebuyer;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		/*System.out.println("\n   Adding buyer ----\n");
		Buyer ob=new Buyer("abc","1234567789","ab@gmail.com","chennai","Male");
		System.out.println(servicebuyer.createBuyer(ob));*/
		
		/*System.out.println("\n Fetching specific buyer details -------\n");
		Buyer buyer_n=dao.getbyNumber("9789470466");
		System.out.println(buyer_n);*/
		
		
		
		
		/*System.out.println("\n Buyer details -------\n");
		List<Buyer> buyerlist=servicebuyer.allBuyers();
		buyerlist.forEach(System.out::println);*/
		
		
	}

}
