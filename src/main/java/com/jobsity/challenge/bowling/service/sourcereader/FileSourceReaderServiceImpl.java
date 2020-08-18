package com.jobsity.challenge.bowling.service.sourcereader;

import com.jobsity.challenge.bowling.exception.InvalidSourceReaderException;
import com.jobsity.challenge.bowling.util.Utils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSourceReaderServiceImpl implements SourceReaderService {

    @Override
    public List<String> readSource(String source, boolean fromClassPath) {
        try {
            Path path;

            if (fromClassPath) {
                ClassPathResource resource = new ClassPathResource("DataFiles/" + source);
                path = Paths.get(resource.getURI());
            } else {
                path = Paths.get(source);
            }
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new InvalidSourceReaderException("Invalid Path or File", e.getCause());
        }
    }
}
