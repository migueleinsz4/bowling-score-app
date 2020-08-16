package com.jobsity.challenge.bowling;

import com.jobsity.challenge.bowling.service.sourcereader.FileSourceReaderServiceImpl;
import com.jobsity.challenge.bowling.service.sourcereader.SourceReaderService;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@CommonsLog
public class FileSourceReaderServiceImplTests {
    SourceReaderService sourceReaderService = new FileSourceReaderServiceImpl();

    @Test
    void readSourceValidFile() {
        List<String> expectedResult = Arrays.asList(
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10",
            "Carl   10"
        );

        List<String> actualResult = this.sourceReaderService.readSource("test.txt");

        assertEquals(expectedResult, actualResult);
    }
}