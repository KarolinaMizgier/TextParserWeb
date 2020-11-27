package com.karolinamizgier.textparserweb.service;

import com.karolinamizgier.textparserweb.components.MySentenceSplitter;
import com.karolinamizgier.textparserweb.components.Splitter;
import com.karolinamizgier.textparserweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextParserService {

    @Autowired
    @Qualifier("sentenceSplitter")
    private Splitter splitter;

    public List<String> convertText() {

        List<String> list = FileUtil.read("src/main/resources/Text");
        List<String> splitted = splitter.split(FileUtil.readText("src/main/resources/Text"));
        return splitted;
    };
}
