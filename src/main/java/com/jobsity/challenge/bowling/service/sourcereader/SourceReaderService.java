package com.jobsity.challenge.bowling.service.sourcereader;

import java.util.List;

public interface SourceReaderService {
    List<String> readSource(String source, boolean fromClasspath);
}