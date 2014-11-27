package org.chirs.study.lambda;

import java.io.IOException;

public class NonCapableExamples {

	public void appendAll(Iterable<String> values, Appendable out) throws IOException { 
	    values.forEach(s -> {
			if (s.equals("foo")) {
				return;
			}
		});
	}
}
