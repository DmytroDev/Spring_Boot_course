package com.itcompany.softwarestore.service.impl;

import com.itcompany.softwarestore.dao.repository.SoftwareRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class DownloadServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitServiceImpl.class);

    @Mock
    private SoftwareRepository softwareRepository;

    @InjectMocks
    private DownloadServiceImpl downloadService;

    @BeforeAll
    static void setup() {
        LOGGER.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        LOGGER.info("@BeforeEach - executes before each test method in this class");
    }

    @DisplayName("Should invoke SoftwareRepository")
    @Test
    void shouldInvokeSoftwareRepository() {
        downloadService.increaseDownloadNum(1L);
        Mockito.verify(softwareRepository).increaseDownloadNum(1L);
    }

    @Test
    void shouldThrowException() {
        Mockito.when(softwareRepository.findOne(any(Long.class))).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> downloadService.createZipArchive(1L));
        Mockito.verify(softwareRepository, Mockito.times(1)).findOne(1L);
    }

    @Disabled("Not implemented yet")
    @Test
    void shouldNotInvoke() {
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsWithCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Add test",
                        () -> assertEquals(2, Math.addExact(1, 1))),
                DynamicTest.dynamicTest("Multiply Test",
                        () -> assertEquals(6, Math.multiplyExact(3, 2))));
    }
}