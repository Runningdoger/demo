package competition_2020.test;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MBGTest {
	
	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("mbg.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		/*BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		String s="2020";
		String hashPass = bCryptPasswordEncoder.encode(s);
		System.out.println(hashPass);
		boolean f = bCryptPasswordEncoder.matches("2020",hashPass);
		System.out.println(f);*/
	}
	
}
