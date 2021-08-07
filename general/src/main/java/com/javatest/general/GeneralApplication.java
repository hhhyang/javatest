package com.javatest.general;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@SpringBootApplication
public class GeneralApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralApplication.class, args);
	}

	private static void batchStoreEdges(List<String> edges) {
		if (!edges.isEmpty()) {
			if (edges.size() < 1000) {

				log.info("size: {}", edges.size());
			} else {
				List<String> group = new ArrayList<>();
				int count = 0;
				for (int i = 0; i < edges.size(); i++) {
					if (count >= 1000) {
						log.info("size: {}", group.size());
						group = new ArrayList<>();
						count = 0;
					}

					group.add(edges.get(i));
					count++;
				}

				if (!group.isEmpty()) {
					log.info("size: {}", group.size());
				}
			}
		}

	}

	@Bean
	@SuppressWarnings("unchecked")
	public CommandLineRunner runner() {

		return (args) -> {
			ThreadLocal<Boolean> a = new ThreadLocal<>();
			a.set(false);

			log.info("======={}, {}", a.get(), Objects.equals(a.get(), true));
		};

	}


}
