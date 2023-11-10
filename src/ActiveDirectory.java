package bin;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.NamingEnumeration;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;
import java.util.ArrayList;
import java.util.Enumeration;
public class ActiveDirectory{



	public static void employeesEntry(User userData, String employeeId, String country) throws Exception{
		CreateUser cr = new CreateUser();
	    	String userName = "Administrator";
    		String password = "Asiva@1412";
    		Hashtable env = new Hashtable();
    		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    		env.put(Context.PROVIDER_URL, "ldap://192.168.56.103:389/DC=sample,DC=com");
    		env.put(Context.SECURITY_AUTHENTICATION, "simple");
    		env.put(Context.SECURITY_PRINCIPAL, new String("cn=Administrator,cn=Users,dc=sample,dc=com"));
    		env.put(Context.SECURITY_CREDENTIALS, password);
    		String path = "OU=ENHisecure";
		String pathOne = "OU=Employee";	
		String id = userData.getId();
    		String commonname = userData.getFirstName(); 
    		String firstname=userData.getFirstName();
    		String lastname  =userData.getLastName();
    		String mobilenumber=userData.getPhoneNumber();
    		String email=userData.getFirstName()+"."+userData.getLastName()+"@sample.com";
    		String Department=userData.getDepartment();
    		String designation=userData.getDesignation();
    		String reporting = userData.getReporting();
    		String Address=userData.getAddress();
    		String Status="544";
    		String entryDN = "CN=" + firstname + "," + "OU=" + country + ","+ pathOne + "," + path;
		String reporterName = "", finals = "";

    		Attribute sAMAccountName=new BasicAttribute("sAMAccountName", employeeId);
    		Attribute cn = new BasicAttribute("cn", commonname);
		Attribute employeeid = new BasicAttribute("displayName", employeeId);
    		Attribute givenName = new BasicAttribute("givenName", firstname);
    		Attribute sn = new BasicAttribute("sn", lastname);
    		Attribute telephoneNumber=new BasicAttribute("telephoneNumber",mobilenumber);
    		Attribute mail=new BasicAttribute("mail",email);
    		Attribute title=new BasicAttribute("title",designation);
    		Attribute department=new BasicAttribute("department",Department);
		Attribute company = new BasicAttribute("company", "ENHisecure");
    		
    		Attribute l=new BasicAttribute("l",Address);
    		Attribute userAccountControl=new BasicAttribute("userAccountControl",Status);
    		Attribute oc = new BasicAttribute("objectClass");
    		oc.add("top");
    		oc.add("person");
    		oc.add("organizationalPerson");
    		oc.add("user");
    		DirContext ctx = null;
		if(!reporting.equals("None")){
    			reporterName = cr.getReporter(reporting);
    			finals = "CN=" + reporterName + "," + pathOne + ","+ path + ",DC=sample,DC=com";
			Attribute manager = new BasicAttribute("manager", finals);
			
			try {
        			ctx = new InitialDirContext(env);
        			Attributes entry = new BasicAttributes();
				entry.put(sAMAccountName);
				entry.put(employeeid);
        			entry.put(cn);
				entry.put(givenName);
				entry.put(sn);
				entry.put(telephoneNumber);
				entry.put(mail);
				entry.put(department);
				entry.put(manager);
				entry.put(company);
				entry.put(title);
				entry.put(l);
				entry.put(userAccountControl);
        			entry.put(oc);
        			ctx.createSubcontext(entryDN, entry);
        			System.out.println("AddUser: added entry " + entryDN + ".");
	
    			} catch (NamingException e) {
        			System.err.println("AddUser: error adding entry." + e);
    			}
		
		}
		else{
    			try {
        			ctx = new InitialDirContext(env);
        			Attributes entry = new BasicAttributes();
				entry.put(sAMAccountName);
				entry.put(employeeid);
        			entry.put(cn);
				entry.put(givenName);
				entry.put(sn);
				entry.put(telephoneNumber);
				entry.put(mail);
				entry.put(department);
				entry.put(title);
				entry.put(company);
				entry.put(l);
				entry.put(userAccountControl);
        			entry.put(oc);
        			ctx.createSubcontext(entryDN, entry);
        			System.out.println("AddUser: added entry " + entryDN + ".");

	    		} catch (NamingException e) {
        			System.err.println("AddUser: error adding entry." + e);
    			}
		}		
	}


	public static void contractorsEntry(User userData, String employeeId) throws Exception{
		CreateUser cr = new CreateUser();
	    	String userName = "Administrator";
    		String password = "Asiva@1412";
    		Hashtable env = new Hashtable();
    		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    		env.put(Context.PROVIDER_URL, "ldap://192.168.56.103:389/DC=sample,DC=com");
    		env.put(Context.SECURITY_AUTHENTICATION, "simple");
    		env.put(Context.SECURITY_PRINCIPAL, new String("cn=Administrator,cn=Users,dc=sample,dc=com"));
    		env.put(Context.SECURITY_CREDENTIALS, password);
    		String path = "OU=ENHisecure";
		String pathOne = "OU=Contractor";	
    		String commonname = userData.getFirstName(); 
    		String firstname=userData.getFirstName();
    		String lastname  =userData.getLastName();
    		String mobilenumber=userData.getPhoneNumber();
    		String email=userData.getFirstName()+"."+userData.getLastName()+"@sample.com";
    		String Department=userData.getDepartment();
    		String designation=userData.getDesignation();
    		String reporting = userData.getReporting();
    		String Address=userData.getAddress();
    		String Status="544";
    		String entryDN = "CN=" + firstname + "," + pathOne + "," + path;
    		String reporterName = cr.getReporter(reporting);
		//System.out.println(reporterName);
    		String finals = "CN=" + reporterName + "," + "OU=Employee" + ","+ path + ",DC=sample,DC=com";
		System.out.println(finals);
    		Attribute sAMAccountName=new BasicAttribute("sAMAccountName", employeeId);
    		Attribute cn = new BasicAttribute("cn", commonname);
    		Attribute givenName = new BasicAttribute("givenName", firstname);
    		Attribute sn = new BasicAttribute("sn", lastname);
    		Attribute telephoneNumber=new BasicAttribute("telephoneNumber",mobilenumber);
    		Attribute mail=new BasicAttribute("mail",email);
    		Attribute title=new BasicAttribute("title",designation);
    		Attribute department=new BasicAttribute("department",Department);

    		Attribute manager = new BasicAttribute("manager", finals);
    		Attribute l=new BasicAttribute("l",Address);
    		Attribute userAccountControl=new BasicAttribute("userAccountControl",Status);
    		Attribute oc = new BasicAttribute("objectClass");
    		oc.add("top");
    		oc.add("person");
    		oc.add("organizationalPerson");
    		oc.add("user");
    		DirContext ctx = null;
    		try {
        		ctx = new InitialDirContext(env);
        		Attributes entry = new BasicAttributes();
			entry.put(sAMAccountName);
        		entry.put(cn);
			entry.put(givenName);
			entry.put(sn);
			entry.put(telephoneNumber);
			entry.put(mail);
			entry.put(department);
			entry.put(manager);
			entry.put(title);
			entry.put(l);
			entry.put(userAccountControl);
        		entry.put(oc);
        		ctx.createSubcontext(entryDN, entry);
        		System.out.println("AddUser: added entry " + entryDN + ".");

    		} catch (NamingException e) {
        		System.err.println("AddUser: error adding entry." + e);
    		}		
	}


	public static int countingEmployees(String country) throws Exception{
		CreateUser cr = new CreateUser();
		java.util.ArrayList<String> al = new java.util.ArrayList<String>();
		String userName = "Administrator";
    		String password = "Asiva@1412";
    		Hashtable env = new Hashtable();
    		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    		env.put(Context.PROVIDER_URL, "ldap://192.168.56.103:389/DC=sample,DC=com");
    		env.put(Context.SECURITY_AUTHENTICATION, "simple");
    		env.put(Context.SECURITY_PRINCIPAL, new String("cn=Administrator,cn=Users,dc=sample,dc=com"));
    		env.put(Context.SECURITY_CREDENTIALS, password);
    		String path = "OU=ENHisecure";
		String pathOne = "OU=Employee";
		String code = "OU=" + country;
		String finals = code + "," + pathOne + "," + path;
    		try {
               		DirContext ctx = new InitialDirContext(env);
               		ctx = new InitialLdapContext(env, null);

	               	SearchControls ctrl = new SearchControls();
               		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
               		String[] returnAttrs = {"displayName"};
               		ctrl.setReturningAttributes(returnAttrs);
		
               		NamingEnumeration results = ctx.search(finals,"(objectClass=user)",ctrl);
               		int count =0;
               		while( results.hasMoreElements() ) {
                 		count++;
                 		SearchResult result = (SearchResult)results.next();
                 		Attributes attrs = result.getAttributes();
                 		if(null!=attrs) {
                     			for(NamingEnumeration ae = attrs.getAll(); ae.hasMoreElements();) {
                       				Attribute atr = (Attribute)ae.next();
                       				String attrId = atr.getID();
                       				for(Enumeration vals = atr.getAll();
                        			vals.hasMoreElements();
                           			//System.out.println(attrId+": "+vals.nextElement()));
						al.add(vals.nextElement().toString()));
                     			}
                  		}
               		}
             		
          	} catch (NamingException e) {
               		//e.printStackTrace();
               		System.out.println("LDAP Notifications failure. ");
               		System.exit(0);
          	}
		return al.size();		
	}

	public static ArrayList<String> getValue(String attribute, String country) throws Exception{
		CreateUser cr = new CreateUser();
		java.util.ArrayList<String> al = new java.util.ArrayList<String>();
		String userName = "Administrator";
    		String password = "Asiva@1412";
    		Hashtable env = new Hashtable();
    		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    		env.put(Context.PROVIDER_URL, "ldap://192.168.56.103:389/DC=sample,DC=com");
    		env.put(Context.SECURITY_AUTHENTICATION, "simple");
    		env.put(Context.SECURITY_PRINCIPAL, new String("cn=Administrator,cn=Users,dc=sample,dc=com"));
    		env.put(Context.SECURITY_CREDENTIALS, password);
    		String path = "OU=ENHisecure";
		String pathOne = "OU=Employee";
		String code = "OU=" + country;
		String finals = code + "," + pathOne + "," + path;
    		try {
               		DirContext ctx = new InitialDirContext(env);
               		ctx = new InitialLdapContext(env, null);

	               	SearchControls ctrl = new SearchControls();
               		ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
               		String[] returnAttrs = {attribute};
               		ctrl.setReturningAttributes(returnAttrs);
			NamingEnumeration results = ctx.search(finals,"(objectClass=user)",ctrl);
               		int count =0;
               		while( results.hasMoreElements() ) {
                 		count++;
                 		SearchResult result = (SearchResult)results.next();
                 		Attributes attrs = result.getAttributes();
                 		if(null!=attrs) {
                     			for(NamingEnumeration ae = attrs.getAll(); ae.hasMoreElements();) {
                       				Attribute atr = (Attribute)ae.next();
                       				String attrId = atr.getID();
                       				for(Enumeration vals = atr.getAll();
                        			vals.hasMoreElements();
                           			//System.out.println(attrId+": "+vals.nextElement()));
						al.add(vals.nextElement().toString()));
                     			}
                  		}
               		}
             		
          	} catch (NamingException e) {
               		//e.printStackTrace();
               		System.out.println("LDAP Notifications failure. ");
               		System.exit(0);
          	}
		return al;		
	}

	public static void printing(){
			System.out.println();
	}
	
}