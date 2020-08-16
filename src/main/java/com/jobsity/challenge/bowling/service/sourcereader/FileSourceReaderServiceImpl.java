package com.jobsity.challenge.bowling.service.sourcereader;

import com.jobsity.challenge.bowling.exception.InvalidSourceReaderException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileSourceReaderServiceImpl implements SourceReaderService {
    @Override
    public List<String> readSource(String source) {
        try {
            Path path = Paths.get(source);
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new InvalidSourceReaderException("Invalid Path or File", e.getCause());
        }
    }
}
