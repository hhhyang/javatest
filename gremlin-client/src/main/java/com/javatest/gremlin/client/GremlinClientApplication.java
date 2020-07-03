package com.javatest.gremlin.client;

import com.javatest.gremlin.client.domain.dao.inet.vertex.Netware;
import com.javatest.gremlin.client.repo.inet.vertex.NetwareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class GremlinClientApplication {

	@Autowired
	private NetwareRepository netwareRepo;


	public static void main(String[] args) {
		SpringApplication.run(GremlinClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {

		return (args) -> {

			//loadData(netwareRepo);

			// List<Netware> netwares = netwareRepo.findAll();
			// List<Netware> netwares = netwareRepo.findBySource("controller-yq01");
			log.error("search gremlin");
			netwareRepo.findByScript("g.withComputer().V().has('Netware', 'district', 'HB').path()");
			List<Netware> netwares = new ArrayList<>();
			log.info("size: {}", netwares.size());

			if (!CollectionUtils.isEmpty(netwares)) {
				Netware netware = netwares.get(0);
				log.info("netware: {}", netware);
				log.info("nwid: {}, {}, {}", netware.getMgmtIp(), netware.getHostname(), netware.getRole());
			}


		};

	}

	public static void loadData(NetwareRepository netwareRepo) {

		//Netware nw = new Netware();
		Netware nw = netwareRepo.findByElementId("10.31.112.34");
		nw.setId("a");
		nw.setMgmtIp("10.31.112.34");
		nw.setDistrict("HB");
		nw.setHostname("hostname_10.31.112.34");
		nw.setRole("DC");

		netwareRepo.save(nw);

		// nw = new Netware();
		nw = netwareRepo.findByElementId("10.31.112.35");
		nw.setId("b");
		nw.setMgmtIp("10.31.112.35");
		nw.setDistrict("HB");
		nw.setHostname("hostname_10.31.112.35");
		nw.setRole("DC");

		netwareRepo.save(nw);


		//nw = new Netware();
		nw = netwareRepo.findByElementId("10.31.112.36");
		nw.setId("c");
		nw.setMgmtIp("10.31.112.36");
		nw.setDistrict("HB");
		nw.setHostname("hostname_10.31.112.36");
		nw.setRole("SC");

		netwareRepo.save(nw);

	}

}
