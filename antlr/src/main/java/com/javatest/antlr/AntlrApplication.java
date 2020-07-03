package com.javatest.antlr;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class AntlrApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntlrApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {
			String logLines = "hostname: neq(8) and (role: 2,3,4 or region: 'a','b')";
			ElementQueryLexer lexer = new ElementQueryLexer(CharStreams.fromString(logLines));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ElementQueryParser parser = new ElementQueryParser(tokens);
			ParseTreeWalker walker = new ParseTreeWalker();
			TopoElementQueryListener listener = new TopoElementQueryListener();
			walker.walk(listener, parser.query());

			log.error("result: {}", listener.getResult());

		};
	}

}
